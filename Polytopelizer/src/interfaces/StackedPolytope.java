package interfaces;
import java.math.BigInteger;

import Geometry.PointInteger;

public interface StackedPolytope {
    
    // Definition: A Boundary is a Polytope that has no stacked Points above it.
    public void merge();
    // This Triangle becomes a Boundary, that means alle Polytopes stacked ontop of this,
    // are deleted.
    public void divide(PointInteger p);
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
    public PointInteger[] getPoints();
    // Returns the 3 Vertex-Points of this Triangle. The first Point ist the Point that was
    // stacked on top of his base Triangle.
    public void mPoints(PointInteger p1, PointInteger p2, PointInteger p3);
    //modifies the points
    
    public BigInteger vShift();//view the shift
	public void mShift(BigInteger shift);//modifies the shift
}
