package Datastructures;

import Geometry.*;
import interfaces.*;

public class ApollNetwork implements ApollonianNetwork {

    private Face faces;
    
    public ApollNetwork(){
        this(new Point(),new Point(0,100),new Point(50,60));
    }

    public ApollNetwork(Point p1, Point p2, Point p3) {
        this.faces = new GraphFace(p1, p2, p3);
    }

    public boolean addNode(double x, double y) {
        Point p = new Point(x, y);
        Face smallestFace = faces.smallestFaceforPoint(p);
        if (smallestFace != null)
            smallestFace.divide(p);
        return smallestFace != null;

    }

    public boolean removeNode(double x, double y) {
        Point p = new Point(x,y);
        Face smallestFace = faces.smallestFaceforPoint(p);
        if (smallestFace!=null && smallestFace!= faces)
            smallestFace.outerFace().merge();
        return smallestFace!=null && smallestFace!= faces;
    }

    public Face getFaces() {
        return faces;
    }

}
