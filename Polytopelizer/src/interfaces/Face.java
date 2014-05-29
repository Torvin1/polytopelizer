package interfaces;

import Geometry.*;

public interface Face {
    // Definition: A smallest Face is a Face that can not be divided into smaller
    // Faces.
    public void merge();
    // Deletes all smaller Faces that belongs to this Face, that means this Face
    // becomes a smallest Face
    public void divide(Point2D p);
    // If not (isSmallestFace() and hasPoint(p)), nothing happens. Otherwise this Face
    // is divided into 3 smallest Subfaces with p as the common Point.
    public int size();
    // Returns the number of smallest Faces.
    public boolean isSmallestFace();
    // Returns True, iff this Face is a smallest Face.
    public Face[] smallerFaces();
    // Returns the three Subfaces, whose union equals this Face. Returns null, iff
    // (isSmallestFace()).
    public Face smallestFaceforPoint(Point2D p);
    // Returns the smallest Face, for which p belongs to. Returns null,
    // iff there is no such Face.
    public Face outerFace();
    // Returns the outer Face which belongs to this Face. Returns null, iff there is
    // no such Face.
    public Point2D[] getPoints();
    // Returns the 3 Vertex-Points of this Face. The first Point is the Point, that
    // does NOT belong to the outer Face.
    public boolean hasPoint(Point2D p);
    // Returns True, iff p belongs to the Triangle, that is spanned by his 3
    // Vertex-Points.
    public void caterpillar();
    //from the actual tree construct a caterpillar
    public void balance();
    //balance the Tree
    public int Weight(); // view the weight
    public void addSpineWeight(int dif); //add the dif to the weight from all node spine
    
}