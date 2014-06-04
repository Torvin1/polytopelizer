package interfaces;
import Geometry.Point3D;

public interface StackedPolytope {
    
    // Definition: A Boundary is a Polytope that has no stacked Points above it.
    public void merge();
    // This Triangle becomes a Boundary, that means alle Polytopes stacked ontop of this,
    // are deleted.
    public void divide(Point3D p);
    // If this Triangle is not a Boundary, nothing happens. Otherwise a Tetraeder is stacked
    // ontop of this Polytope with the new Point p.
    public int size();
    // Returns the number of Boundarys stacked ontop of this Triangle.
    public boolean isBoundary();
    // Returns True, iff there are no Polytopes stacked on top of this Triangle.
    public StackedPolytope[] smallerPolytopes();
    // Returns the three Subpolytopes, which are stacked on top of this Triangle.
    // Returns null, iff this Triangle is a Boundary.
    public StackedPolytope baseTriangle();
    // Returns the Triangle on which this Polytope is stacked. Returns null, iff there is
    // no such Polytope.
    public Point3D[] getPoints();
    // Returns the 3 Vertex-Points of this Triangle. The first Point ist the Point that was
    // stacked on top of his base Triangle.
    public void mPoints(Point3D p1, Point3D p2, Point3D p3);
    //modifies the points
    
    public long vShift();//view the shift
	public void mShift(long shift);//modifies the shift
}
