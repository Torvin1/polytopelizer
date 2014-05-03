package Datastructures;

import interfaces.*;
import Geometry.*;

public class GraphFace implements Face {

    private Point[] points;
    private Face[] innerFaces;
    private Face father;

    public GraphFace(Point p1, Point p2, Point p3, Face father) {
        this.father = father;
        this.points = new Point[3];
        this.points[0] = p1;
        this.points[1] = p2;
        this.points[2] = p3;
        this.innerFaces = null;
    }

    public GraphFace(Point p1, Point p2, Point p3) {
        this(p1, p2, p3, null);
    }

    public void merge() {
        this.innerFaces = null;
    }

    public void divide(Point p) {
        if (!isSmallestFace() || !hasPoint(p))
            return;
        Face f1 = new GraphFace(p, points[0], points[1], this);
        Face f2 = new GraphFace(p, points[1], points[2], this);
        Face f3 = new GraphFace(p, points[2], points[0], this);
        this.innerFaces = new Face[3];
        this.innerFaces[0] = f1;
        this.innerFaces[1] = f2;
        this.innerFaces[2] = f3;
    }

    public int size() {
        if (isSmallestFace())
            return 1;
        else
            return this.innerFaces[0].size() + this.innerFaces[1].size()
                    + this.innerFaces[2].size();
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

    public Face smallestFaceforPoint(Point p) {
        if (isSmallestFace())
            return this;
        if (innerFaces[0].hasPoint(p))
            return innerFaces[0].smallestFaceforPoint(p);
        if (innerFaces[1].hasPoint(p))
            return innerFaces[1].smallestFaceforPoint(p);
        if (innerFaces[2].hasPoint(p))
            return innerFaces[2].smallestFaceforPoint(p);
        return null;
    }

    public Face outerFace() {
        return this.father;
    }

    public Point[] getPoints() {
        return this.points;
    }

    // This test was described by Prof. Rote.
    // If we need this test somewhere else, we should implement this Test in the
    // Geom-Class
    public boolean hasPoint(Point p) {
        double[] v0 = new double[] { 1, p.x(), p.y() };
        double[] v1 = new double[] { 1, points[0].x(), points[0].y() };
        double[] v2 = new double[] { 1, points[1].x(), points[1].y() };
        double[] v3 = new double[] { 1, points[2].x(), points[2].y() };
        double faceDet;
        try {
            faceDet = Geom.det(new double[][] { v1, v2, v3 });
            boolean onSameSide = faceDet
                    * Geom.det(new double[][] { v0, v2, v3 }) >= 0;
            onSameSide = onSameSide
                    && faceDet * Geom.det(new double[][] { v1, v0, v3 }) >= 0;
            onSameSide = onSameSide
                    && faceDet * Geom.det(new double[][] { v1, v2, v0 }) >= 0;
            return onSameSide;
        } catch (IllegalMatrixException e) {
            e.printStackTrace();
            return false;
        }
    }

}
