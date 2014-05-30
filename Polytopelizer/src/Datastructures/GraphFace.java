package Datastructures;

import interfaces.*;
import Geometry.*;

public class GraphFace implements Face {

    private Point2D[] points;
    private Face[] innerFaces;
    private Face father;
    private long weight;
    private int spineInd; //gives the index from the child that is a spine node
    private long shift;
    
    // Create a new Outer Face f0 without any smaller Faces.
    public GraphFace(Point2D p1, Point2D p2, Point2D p3) {
        this.points = new Point2D[3];
        this.points[0] = p1;
        this.points[1] = p2;
        this.points[2] = p3;
        this.innerFaces = null;
        this.father = null;
        this.weight = 1;
    }

    public void merge() {
        this.innerFaces = null;
    }

    public void divide(Point2D p) {
        if (!isSmallestFace() || !hasPoint(p))
            return;
        GraphFace f1 = new GraphFace(p, points[0], points[1]);
        f1.father = this;
        GraphFace f2 = new GraphFace(p, points[1], points[2]);
        f2.father = this;
        GraphFace f3 = new GraphFace(p, points[2], points[0]);
        f3.father = this;
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

    public Face smallestFaceforPoint(Point2D p) {
        if (isSmallestFace() || !hasPoint(p)){
            return hasPoint(p) ? this : null;}
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

    public Point2D[] getPoints() {
        return this.points;
    }

    // This test was described by Prof. Rote.
    // If we need this test somewhere else, we should implement this Test in the
    // Geom-Class. Tell me, iff we have to.
    public boolean hasPoint(Point2D p) {
        double[] v0 = new double[] { 1, p.x(), p.y() };
        double[] v1 = new double[] { 1, points[0].x(), points[0].y() };
        double[] v2 = new double[] { 1, points[1].x(), points[1].y() };
        double[] v3 = new double[] { 1, points[2].x(), points[2].y() };
        System.out.println(p);
        System.out.println(points[0]);
        System.out.println(points[1]);
        System.out.println(points[2]);
        try {
            double faceDet = Geom.det(new double[][] { v1, v2, v3 });
            double det1 = Geom.det(new double[][] { v0, v2, v3 });
            double det2 = Geom.det(new double[][] { v1, v0, v3 });
            double det3 = Geom.det(new double[][] { v1, v2, v0 });
            boolean onSameSide = faceDet * det1 > 0;
            onSameSide = onSameSide && (faceDet * det2 > 0);
            onSameSide = onSameSide && (faceDet * det3 > 0);
            return onSameSide;
        } catch (IllegalMatrixException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String toString() {
        String result = "";
        if (father == null)
            result += points[1].toString() + points[2].toString() + points[0].toString();
        if (smallerFaces() != null) {
            result += smallerFaces()[0].getPoints()[0].toString();
            for (int i = 0; i < smallerFaces().length; i++)
                result += smallerFaces()[i].toString();
        }
        return result;
    }
    
    
     public void caterpillar(){
    	//the child with the greatest size is the spine node
    	int[] v = new int[3];
    	int i;
    	if (isSmallestFace())
    		return;
    	else{
    		for (i=0; i <3; i++){
    			v[i] = this.innerFaces[i].size();
    		}
    		int max = v[0];
    		int maxi = 0;
    		for(i=1; i <3; i++){
    			if(v[i]>max){
    				max = v[i];
    				maxi = i;
    			}
    		}
    		this.spineInd = maxi;	
    		for (i=0; i <3; i++){
    			this.innerFaces[i].caterpillar();
    		}
    	}
    }
 
    public long Weight(){
    	return this.weight;
    }
   
    public void addSpineWeight(long dif){
    	if (isSmallestFace()){
			this.weight = this.weight + dif;
    		return;
		}
    	else{
    		this.weight = this.weight + dif;
    		this.innerFaces[this.spineInd].addSpineWeight(dif);
     	}
    }
    
	public void balance() {
		if (isSmallestFace()){
			return;
		}
		else{
			int x = this.spineInd;
			int y = (x + 1)%3;
			int z = (y + 1)%3;
			this.innerFaces[x].balance();
			this.innerFaces[y].balance();
			this.innerFaces[z].balance();
			if( this.innerFaces[y].Weight() > this.innerFaces[z].Weight()){
				int temp = y;
				y = z;
				z = temp;
			}
			long dif = this.innerFaces[z].Weight() - this.innerFaces[y].Weight();
			if (dif != 0)
				this.innerFaces[y].addSpineWeight(dif);
			
			this.weight = this.innerFaces[x].Weight() + 2*this.innerFaces[y].Weight();
		}
	}
	
	public long vShift(){
		return this.shift;
	}
	
	public void mShift(long shift){
		this.shift = shift;
	}
	
	public int vSpine(){
		return this.spineInd;
	}
}
