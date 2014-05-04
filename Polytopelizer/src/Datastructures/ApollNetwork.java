package Datastructures;

import Geometry.*;
import interfaces.*;

public class ApollNetwork implements ApollonianNetwork {

    private Face faces;

    // Create a new Apollonian Network with 3 Vertices and 3 Edges.
    public ApollNetwork(Point2D p1, Point2D p2, Point2D p3) {
        this.faces = new GraphFace(p1, p2, p3);
    }
    
    public ApollNetwork(){
        this(new Point2D(),new Point2D(0,100),new Point2D(50,75));
    }

    public boolean addNode(double x, double y) {
        Point2D p = new Point2D(x, y);
        Face smallestFace = faces.smallestFaceforPoint(p);
        if (smallestFace != null)
            smallestFace.divide(p);
        return smallestFace != null;

    }

    public boolean removeNode(double x, double y) {
        Point2D p = new Point2D(x,y);
        Face smallestFace = faces.smallestFaceforPoint(p);
        if (smallestFace!=null && smallestFace!= faces)
            smallestFace.outerFace().merge();
        return smallestFace!=null && smallestFace!= faces;
    }

    public Face getFaces() {
        return faces;
    }

}
