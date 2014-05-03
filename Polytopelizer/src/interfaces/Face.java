package interfaces;

import Geometry.Point;

public interface Face {
    // Definition: A smallest Face is a Face that can not be divided into smaller
    // Faces.
    public void merge();
    // Deletes all smaller Faces that belongs to this Face, that means this Face
    // becomes a smallest Face
    public void divide(Point p);
    // If not (isSmallestFace() and hasPoint(p)), nothing happens. Otherwise this Face
    // is divided into 3 smallest Subfaces with p as the common Point.
    public int size();
    // Returns the number of smallest Faces.
    public boolean isSmallestFace();
    // Returns True, iff this Face is a smallest Face.
    public Face[] smallerFaces();
    // Returns the three Subfaces, whose union equals this Face. Returns null, iff
    // (isSmallestFace()).
    public Face smallestFaceforPoint(Point p);
    // Returns the smallest Face, for which p belongs to. Returns null,
    // iff there is no such Face.
    public Face outerFace();
    // Returns the outer Face which belongs to this Face. Returns null, iff there is
    // no such Face.
    public Point[] getPoints();
    // Returns the 3 Vertex-Points of this Face.
    public boolean hasPoint(Point p);
    // Returns True, iff p belongs to the Triangle, that is spanned by his 3
    // Vertex-Points.
}