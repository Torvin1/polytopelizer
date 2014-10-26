package Datastructures;

import java.math.BigInteger;

import interfaces.StackedPolytopeDec;
import Geometry.PointDecimal;

public class StackedPolytopeDecImpl implements StackedPolytopeDec {

    private PointDecimal[] points;
    private StackedPolytopeDec[] stackedTriangles;
    private StackedPolytopeDec father;
    private BigInteger shift;
    
    // Create a new simple Triangle.
    public StackedPolytopeDecImpl(PointDecimal p1, PointDecimal p2, PointDecimal p3) {
        this.points = new PointDecimal[3];
        this.points[0] = p1;
        this.points[1] = p2;
        this.points[2] = p3;
        this.stackedTriangles = null;
        this.shift = BigInteger.ZERO;
    }

    public void merge() {
        this.stackedTriangles = null;
    }

    public void divide(PointDecimal p) {
        if (!isBoundary())
            return;
        StackedPolytopeDecImpl f1 = new StackedPolytopeDecImpl(p, points[0],
                points[1]);
        f1.father = this;
        StackedPolytopeDecImpl f2 = new StackedPolytopeDecImpl(p, points[1],
                points[2]);
        f2.father = this;
        StackedPolytopeDecImpl f3 = new StackedPolytopeDecImpl(p, points[2],
                points[0]);
        f3.father = this;
        this.stackedTriangles = new StackedPolytopeDec[3];
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

    public StackedPolytopeDec[] smallerPolytopes() {
        if (isBoundary())
            return null;
        else
            return this.stackedTriangles;
    }

    public StackedPolytopeDec baseTriangle() {
        return this.father;
    }

    public PointDecimal[] getPoints() {
        return this.points;
    }

    public void mPoints(PointDecimal p1, PointDecimal p2, PointDecimal p3) {
    	this.points[0] = p1;
        this.points[1] = p2;
        this.points[2] = p3;
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
	
    public BigInteger vShift(){
		return this.shift;
	}
	
	public void mShift(BigInteger shift){
		this.shift = shift;
	}
    
}
