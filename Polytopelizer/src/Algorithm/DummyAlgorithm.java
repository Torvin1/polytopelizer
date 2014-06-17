package Algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;

import Datastructures.*;
import Geometry.*;
import interfaces.*;

public class DummyAlgorithm {

    public static StackedPolytope calculateStackedPolytope1(ApollonianNetwork aN) {

        // Get the Faces of the Apollonian Network
        Face f = aN.getFaces();

        // Calculate the first 3 Points of the StackedPolytope.
        PointDecimal[] points = f.getPoints();
        PointInteger p1 = new PointInteger(points[0].x().multiply(new BigDecimal("2"))
                .toBigInteger(), points[0].y().multiply(new BigDecimal("2"))
                .toBigInteger(), points[0].x().add(points[0].y()).toBigInteger());
        PointInteger p2 = new PointInteger(points[1].x().multiply(new BigDecimal("2"))
                .toBigInteger(), points[1].y().multiply(new BigDecimal("2"))
                .toBigInteger(), points[1].x().add(points[1].y()).toBigInteger());
        PointInteger p3 = new PointInteger(points[2].x().multiply(new BigDecimal("2"))
                .toBigInteger(), points[2].y().multiply(new BigDecimal("2"))
                .toBigInteger(), points[2].x().add(points[2].y()).toBigInteger());

        // Create the StackedPolytope. (It has just one simple Triangle.)
        StackedPolytope sP = new StackedPolytopeImpl(p1, p2, p3);

        // Now compute the StackedPolytope on top of sP.
        face2stackedPolytope(f, sP);

        return sP;
    }

    private static void face2stackedPolytope(Face f, StackedPolytope sP) {

        // If the Face is not a smallest Face, we compute the rest recursively.
        if (!f.isSmallestFace()) {
            Face[] f_neu = f.smallerFaces();
            PointDecimal p = f_neu[0].getPoints()[0];
            PointInteger p3D = new PointInteger(p.x()
                    .multiply(new BigDecimal("2")).toBigInteger(), p.y()
                    .multiply(new BigDecimal("2")).toBigInteger(), p.x()
                    .add(p.y()).toBigInteger());
            sP.divide(p3D);
            StackedPolytope[] sP_neu = sP.smallerPolytopes();
            face2stackedPolytope(f_neu[0], sP_neu[0]);
            face2stackedPolytope(f_neu[1], sP_neu[1]);
            face2stackedPolytope(f_neu[2], sP_neu[2]);
        }
        return;
    }
}
