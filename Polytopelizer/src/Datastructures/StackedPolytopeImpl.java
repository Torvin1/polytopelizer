package Datastructures;

import interfaces.StackedPolytope;
import Geometry.Point3D;

public class StackedPolytopeImpl implements StackedPolytope {

    private Point3D[] points;
    private StackedPolytope[] stackedTriangles;
    private StackedPolytope father;

    // Create a new simple Triangle.
    public StackedPolytopeImpl(Point3D p1, Point3D p2, Point3D p3) {
        this.points = new Point3D[3];
        this.points[0] = p1;
        this.points[1] = p2;
        this.points[2] = p3;
        this.stackedTriangles = null;
    }

    public void merge() {
        this.stackedTriangles = null;
    }

    public void divide(Point3D p) {
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

    public Point3D[] getPoints() {
        return this.points;
    }

    public String toString() {
        String result = "";
        if (father == null)
            result += points[1].toString() + points[2].toString() + points[0].toString();
        if (smallerPolytopes() != null) {
            result += smallerPolytopes()[0].getPoints()[0].toString();
            for (int i = 0; i < smallerPolytopes().length; i++)
                result += smallerPolytopes()[i].toString();
        }
        return result;
    }

}
