package Datastructures;

import Geometry.*;
import interfaces.*;

public class ApollNetwork implements ApollonianNetwork {

    private Face faces;
    private long nPoints;//number of points

    // Create a new Apollonian Network with 3 Vertices and 3 Edges.
    public ApollNetwork(Point2D p1, Point2D p2, Point2D p3) {
        this.faces = new GraphFace(p1, p2, p3);
        this.nPoints = 3;
    }
    
    public ApollNetwork(){
        this(new Point2D(),new Point2D(0,100),new Point2D(100,0));
    }

    public boolean addNode(double x, double y) {
        Point2D p = new Point2D(x, y);
        Face smallestFace = faces.smallestFaceforPoint(p);
        if (smallestFace != null){
            smallestFace.divide(p);
            nPoints = nPoints + 1;
        }
        return smallestFace != null;

    }

    public boolean removeNode(double x, double y) {
        Point2D p = new Point2D(x,y);
        Face smallestFace = faces.smallestFaceforPoint(p);
        if (smallestFace!=null && smallestFace!= faces){
            smallestFace.outerFace().merge();
            nPoints = nPoints - 1;
        }
        return smallestFace!=null && smallestFace!= faces;
    }

    public long getNPoints(){
    	return nPoints;
    }
    
    public Face getFaces() {
        return faces;
    }

}
