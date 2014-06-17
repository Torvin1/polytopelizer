package Algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;

import Datastructures.*;
import Geometry.*;
import interfaces.*;

public class Algorithm {

    private static BigDecimal pert;
    private static BigDecimal pert_z;

    public static StackedPolytope calculateStackedPolytope1(ApollonianNetwork aN) {

        BigInteger x, y, z;
        PointInteger[] p = new PointInteger[3];

        int n = aN.getNPoints();
        // Get the Faces of the Apollonian Network
        Face f = aN.getFaces();

        // produce the caterpillar Tree
        f.caterpillar();
        // balance the Tree
        f.balance();

        // algorithm 2
        // calculate the shift
        calcShift(f);

        // round

        pert = (new BigDecimal("1"))
                .multiply(new BigDecimal(String.valueOf(Math.pow(n, 1.5))));

        // Calculate the first 3 Points of the StackedPolytope.
        PointDecimal[] points = f.getPoints();
        for (int i = 0; i < 3; i++) {
            x = points[i].x().multiply(pert).toBigInteger();
            y = points[i].y().multiply(pert).toBigInteger();
            z = BigInteger.ZERO;
            p[i] = new PointInteger(x, y, z);
        }

        // Create the StackedPolytope. (It has just one simple Triangle.)
        StackedPolytope sP = new StackedPolytopeImpl(p[0], p[1], p[2]);
        sP.mShift(f.vShift());

        // Compute the StackedPolytope on top of sP.
        // first scale the x and y
        createAndScalePolytopXY(f, sP);

        // lift
        pert_z = (new BigDecimal("3")).multiply(new BigDecimal(n));
        lift(sP);

        return sP;

    }

    public static StackedPolytope calculateStackedPolytope2(ApollonianNetwork aN) {
        return null;
    }

    private static void calcShift(Face f) {
        if (f.isSmallestFace())
            return;
        else {
            Face[] f_chil = f.smallerFaces();
            BigInteger x = f.vSpine();
            BigInteger y = x.add(BigInteger.ONE).mod(new BigInteger("3"));
            BigInteger shift = f_chil[x.intValue()].Weight().multiply(
                    f_chil[y.intValue()].Weight());
            f.mShift(shift);
            for (int i = 0; i < 3; i++) {
                calcShift(f_chil[i]);
            }
        }
    }

    private static void createAndScalePolytopXY(Face f, StackedPolytope sP) {

        // If the Face is not a smallest Face, we compute the rest recursively.
        if (!f.isSmallestFace()) {
            Face[] f_neu = f.smallerFaces();
            PointDecimal p = f_neu[0].getPoints()[0];

            BigInteger x, y, z;
            x = p.x().multiply(pert).toBigInteger();
            y = p.y().multiply(pert).toBigInteger();
            z = BigInteger.ZERO;

            PointInteger p3D = new PointInteger(x, y, z);

            sP.divide(p3D);

            StackedPolytope[] sP_neu = sP.smallerPolytopes();
            sP_neu[0].mShift(f_neu[0].vShift());
            sP_neu[1].mShift(f_neu[1].vShift());
            sP_neu[2].mShift(f_neu[2].vShift());

            createAndScalePolytopXY(f_neu[0], sP_neu[0]);
            createAndScalePolytopXY(f_neu[1], sP_neu[1]);
            createAndScalePolytopXY(f_neu[2], sP_neu[2]);
        }
        return;
    }

    private static void lift(StackedPolytope sP) {

        if (!sP.isBoundary()) {
            StackedPolytope[] sP_neu = sP.smallerPolytopes();
            PointInteger p = sP_neu[0].getPoints()[0];
            // lift

            PointInteger p0 = new PointInteger(p.x(), p.y(), new BigDecimal(
                    sP.vShift()).multiply(pert_z).toBigInteger().add(p.z()));

            changePoint(sP_neu, p0);

            // update the heights of the subPolytops
            updateZ(sP_neu[0]);
            updateZ(sP_neu[1]);
            updateZ(sP_neu[2]);

            lift(sP_neu[0]);
            lift(sP_neu[1]);
            lift(sP_neu[2]);

        }
        return;
    }

    private static void changePoint(StackedPolytope[] sP, PointInteger p0) {

        for (int i = 0; i < 3; i++) {
            PointInteger p1 = sP[i].getPoints()[1];
            PointInteger p2 = sP[i].getPoints()[2];
            sP[i].mPoints(p0, p1, p2);
        }
    }

    private static void updateZ(StackedPolytope sP) {
        // find the new heights of the subpolytops with the intersection of the
        // subface with the line containing the point stacked.

        if (!sP.isBoundary()) {

            PointInteger[] points = sP.getPoints();
            BigDecimal x, y, z;
            PointDecimal[] pointsDou = new PointDecimal[5];
            for (int i = 0; i < 3; i++) {
                x = new BigDecimal(points[i].x());
                y = new BigDecimal(points[i].y());
                z = new BigDecimal(points[i].z());

                pointsDou[i] = new PointDecimal(x, y, z);
            }

            StackedPolytope[] sP_neu = sP.smallerPolytopes();
            PointInteger p = sP_neu[0].getPoints()[0];

            x = new BigDecimal(p.x());
            y = new BigDecimal(p.y());

            pointsDou[3] = new PointDecimal(x, y, BigDecimal.ZERO);
            pointsDou[4] = new PointDecimal(x, y, BigDecimal.ONE);

            PointDecimal pointDou = Geom.linePlane(pointsDou[0], pointsDou[1],
                    pointsDou[2], pointsDou[3], pointsDou[4]);

            z = pointDou.z();

            PointInteger p0 = new PointInteger(p.x(), p.y(), z.setScale(0,
                    BigDecimal.ROUND_HALF_UP).toBigInteger());

            changePoint(sP_neu, p0);

            // update the heights of the subPolytops
            updateZ(sP_neu[0]);
            updateZ(sP_neu[1]);
            updateZ(sP_neu[2]);

        }
        return;

    }
}
