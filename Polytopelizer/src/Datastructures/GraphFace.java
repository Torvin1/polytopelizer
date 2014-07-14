package Datastructures;

import java.math.BigDecimal;
import java.math.BigInteger;

import interfaces.*;
import Geometry.*;

public class GraphFace implements Face {

    private PointDecimal[] points;
    private GraphFace[] innerFaces;
    private GraphFace father;
    private BigInteger weight;
    private BigInteger spineInd; // gives the index from the child that is a
                                 // spine node
    private BigInteger shift;

    // Create a new Outer Face f0 without any smaller Faces.
    public GraphFace(PointDecimal p1, PointDecimal p2, PointDecimal p3) {
        this.points = new PointDecimal[3];
        this.points[0] = p1;
        this.points[1] = p2;
        this.points[2] = p3;
        this.innerFaces = null;
        this.father = null;
        this.weight = BigInteger.ONE;
    }

    public void merge() {
        this.innerFaces = null;
    }

    public void divide(PointDecimal p) {
        if (!isSmallestFace() || !hasPoint(p))
            return;
        GraphFace f1 = new GraphFace(p, points[0], points[1]);
        f1.father = this;
        GraphFace f2 = new GraphFace(p, points[1], points[2]);
        f2.father = this;
        GraphFace f3 = new GraphFace(p, points[2], points[0]);
        f3.father = this;
        this.innerFaces = new GraphFace[3];
        this.innerFaces[0] = f1;
        this.innerFaces[1] = f2;
        this.innerFaces[2] = f3;
    }

    public BigInteger size() {
        if (isSmallestFace())
            return BigInteger.ONE;
        else
            return this.innerFaces[0].size().add(this.innerFaces[1].size())
                    .add(this.innerFaces[2].size());
    }

    public boolean isSmallestFace() {
        return this.innerFaces == null;
    }

    public Face[] smallerFaces() {
        if (isSmallestFace())
            return null;
        else
            return this.innerFaces;
    }

    public Face smallestFaceforPoint(PointDecimal p) {
        if (isSmallestFace() || !hasPoint(p)) {
            return hasPoint(p) ? this : null;
        }
        if (innerFaces[0].hasPoint(p))
            return innerFaces[0].smallestFaceforPoint(p);
        else if (innerFaces[1].hasPoint(p))
            return innerFaces[1].smallestFaceforPoint(p);
        else
            return innerFaces[2].smallestFaceforPoint(p);
    }

    public Face outerFace() {
        return this.father;
    }

    public PointDecimal[] getPoints() {
        return this.points;
    }

    // This test was described by Prof. Rote.
    // If we need this test somewhere else, we should implement this Test in the
    // Geom-Class. Tell me, iff we have to.
    public boolean hasPoint(PointDecimal p) {
        BigDecimal[] v0 = new BigDecimal[] { BigDecimal.ONE, p.x(), p.y() };
        BigDecimal[] v1 = new BigDecimal[] { BigDecimal.ONE, points[0].x(),
                points[0].y() };
        BigDecimal[] v2 = new BigDecimal[] { BigDecimal.ONE, points[1].x(),
                points[1].y() };
        BigDecimal[] v3 = new BigDecimal[] { BigDecimal.ONE, points[2].x(),
                points[2].y() };
        try {
            BigDecimal faceDet = Geom.det(new BigDecimal[][] { v1, v2, v3 });
            BigDecimal det1 = Geom.det(new BigDecimal[][] { v0, v2, v3 });
            BigDecimal det2 = Geom.det(new BigDecimal[][] { v1, v0, v3 });
            BigDecimal det3 = Geom.det(new BigDecimal[][] { v1, v2, v0 });
            boolean onSameSide = faceDet.multiply(det1).signum() >= 0;
            onSameSide = onSameSide && (faceDet.multiply(det2).signum() >= 0);
            onSameSide = onSameSide && (faceDet.multiply(det3).signum() >= 0);
            return onSameSide;
        } catch (IllegalMatrixException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String toString() {
        String result = "";
        if (father == null)
            result += points[1].toString() + points[2].toString()
                    + points[0].toString();
        if (smallerFaces() != null) {
            result += smallerFaces()[0].getPoints()[0].toString();
            for (int i = 0; i < smallerFaces().length; i++)
                result += smallerFaces()[i].toString();
        }
        return result;
    }

    public void caterpillar() {
        // the child with the greatest size is the spine node
        BigInteger[] v = new BigInteger[3];
        if (isSmallestFace())
            return;
        else {
            for (int i = 0; i < 3; i++) {
                v[i] = this.innerFaces[i].size();
            }
            BigInteger max = v[0];
            BigInteger maxi = BigInteger.ZERO;
            for (int i = 1; i < 3; i++) {
                if ((v[i].subtract(max)).signum() > 0) {
                    max = v[i];
                    maxi = new BigInteger(String.valueOf(i));
                }
            }
            this.spineInd = maxi;
            for (int i = 0; i < 3; i++) {
                this.innerFaces[i].caterpillar();
            }
        }
    }

    public BigInteger Weight() {
        return this.weight;
    }

    public void addSpineWeight(BigInteger dif) {
        this.weight = this.weight.add(dif);
        if (!isSmallestFace()) {
            this.innerFaces[this.spineInd.intValue()].addSpineWeight(dif);
        }
    }

    public void balance() {
        if (isSmallestFace()) {
            return;
        } else {
            BigInteger x = this.spineInd;
            BigInteger y = (x.add(BigInteger.ONE)).mod(new BigInteger("3"));
            BigInteger z = (y.add(BigInteger.ONE)).mod(new BigInteger("3"));
            this.innerFaces[x.intValue()].balance();
            this.innerFaces[y.intValue()].balance();
            this.innerFaces[z.intValue()].balance();
            if (this.innerFaces[y.intValue()].Weight()
                    .subtract(this.innerFaces[z.intValue()].Weight()).signum() > 0) {
                BigInteger temp = y;
                y = z;
                z = temp;
            }
            BigInteger dif = this.innerFaces[z.intValue()].Weight().subtract(
                    this.innerFaces[y.intValue()].Weight());
            if (dif.signum() != 0)
                this.innerFaces[y.intValue()].addSpineWeight(dif);

            this.weight = this.innerFaces[x.intValue()].Weight()
                    .add(this.innerFaces[y.intValue()].Weight())
                    .add(this.innerFaces[y.intValue()].Weight());
        }
    }

    public BigInteger vShift() {
        return this.shift;
    }

    public void mShift(BigInteger shift) {
        this.shift = shift;
    }

    public BigInteger vSpine() {
        return this.spineInd;
    }
}
