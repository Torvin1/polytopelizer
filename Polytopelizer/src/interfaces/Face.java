package interfaces;

import Geometry.Point;

public interface Face {
    // Definition: A smallest Face is a Face that can not be divided into smaller
    // Faces.
    public void merge();
    // deletes all inner Faces that belongs to this Face, that means this Face
    // becomes a smallest Face
    public void divide(Point p);
    // if !(isSmallestFace() and hasPoint(p)), nothing happens. otherwise this Face
    // is divided into 3 Subfaces with p as the common Point
    public int size();
    // returns the number of smallest Faces
    public boolean isSmallestFace();
    // returns True, iff this Face is a smallest Face
    public Face[] smallerFaces();
    // returns the three Faces, whose union equals this Face. returns null, iff
    // (isSmallestFace())
    public Face smallestFaceforPoint(Point p);
    // returns the smallest Face, for which p belongs to.
    // returns null, if there is no such Face.
    public Face outerFace();
    // returns the smallest outer Face for this Face. returns null, iff there is
    // no such Face
    public Point[] getPoints();
    // returns the 3 Vertex-Points of this Face
    public boolean hasPoint(Point p);
    // returns True, iff p belongs to the triangle, that is spanned by his 3
    // Vertex-Points
}