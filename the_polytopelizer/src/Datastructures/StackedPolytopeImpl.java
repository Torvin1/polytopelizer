package Datastructures;

import java.math.BigInteger;

import interfaces.StackedPolytope;
import Geometry.PointInteger;

public class StackedPolytopeImpl implements StackedPolytope {

    private PointInteger[] points;
    private StackedPolytope[] stackedTriangles;
    private StackedPolytope father;
    private BigInteger shift;

    // Create a new simple Triangle.
    public StackedPolytopeImpl(PointInteger p1, PointInteger p2, PointInteger p3) {
        this.points = new PointInteger[3];
        this.points[0] = p1;
        this.points[1] = p2;
        this.points[2] = p3;
        this.stackedTriangles = null;
        this.shift = BigInteger.ZERO;
    }

    public void merge() {
        this.stackedTriangles = null;
    }

    public void divide(PointInteger p) {
        if (!isBoundary())
            return;
        StackedPolytopeImpl f1 = new StackedPolytopeImpl(p, points[0],
                points[1]);
        f1.father = this;
        StackedPolytopeImpl f2 = new StackedPolytopeImpl(p, points[1],
                points[2]);
        f2.father = this;
        StackedPolytopeImpl f3 = new StackedPolytopeImpl(p, points[2],
                points[0]);
        f3.father = this;
        this.stackedTriangles = new StackedPolytope[3];
        this.stackedTriangles[0] = f1;
        this.stackedTriangles[1] = f2;
        this.stackedTriangles[2] = f3;
    }

    public int size() {
        if (isBoundary())
            return 1;
        else
            return this.stackedTriangles[0].size()
                    + this.stackedTriangles[1].size()
                    + this.stackedTriangles[2].size();
    }

    public boolean isBoundary() {
        return this.stackedTriangles == null;
    }

    public StackedPolytope[] smallerPolytopes() {
        if (isBoundary())
            return null;
        else
            return this.stackedTriangles;
    }

    public StackedPolytope baseTriangle() {
        return this.father;
    }

    public PointInteger[] getPoints() {
        return this.points;
    }

    public void mPoints(PointInteger p1, PointInteger p2, PointInteger p3) {
        this.points[0] = p1;
        this.points[1] = p2;
        this.points[2] = p3;
    }

    public String toString() {
        String result = "";
        if (father == null)
            result += points[1].toString() + points[2].toString()
                    + points[0].toString();
        if (smallerPolytopes() != null) {
            result += smallerPolytopes()[0].getPoints()[0].toString();
            for (int i = 0; i < smallerPolytopes().length; i++)
                result += smallerPolytopes()[i].toString();
        }
        return result;
    }

    public BigInteger vShift() {
        return this.shift;
    }

    public void mShift(BigInteger shift) {
        this.shift = shift;
    }

    public BigInteger getMaxX() {
        BigInteger max = points[0].x().max(points[1].x().max(points[2].x()));
        if (!isBoundary())
            for (StackedPolytope sP : stackedTriangles)
                max = max.max(sP.getMaxX());
        return max;
    }

    public BigInteger getMaxY() {
        BigInteger max = points[0].y().max(points[1].y().max(points[2].y()));
        if (!isBoundary())
            for (StackedPolytope sP : stackedTriangles)
                max = max.max(sP.getMaxY());
        return max;
    }

    public BigInteger getMaxZ() {
        BigInteger max = points[0].z().max(points[1].z().max(points[2].z()));
        if (!isBoundary())
            for (StackedPolytope sP : stackedTriangles)
                max = max.max(sP.getMaxZ());
        return max;
    }

}
