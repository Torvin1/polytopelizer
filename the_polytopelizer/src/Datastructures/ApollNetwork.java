package Datastructures;

import java.math.BigDecimal;
import java.util.LinkedList;

import Geometry.*;
import interfaces.*;

public class ApollNetwork implements ApollonianNetwork {

    private Face faces;
    LinkedList<PointDecimal> points;

    // Create a new Apollonian Network with 3 Vertices and 3 Edges.
    public ApollNetwork(PointDecimal p1, PointDecimal p2, PointDecimal p3) {
        this.faces = new GraphFace(p1, p2, p3);
        points = new LinkedList<PointDecimal>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
    }

    public ApollNetwork() {
        this(new PointDecimal(), new PointDecimal(BigDecimal.ZERO,
                new BigDecimal("100")), new PointDecimal(new BigDecimal("100"),
                BigDecimal.ZERO));
    }

    public boolean addNode(PointDecimal p) {
        if (contains(p))
            return false;
        Face smallestFace = faces.smallestFaceforPoint(p);
        if (smallestFace != null) {
            smallestFace.divide(p);
            points.add(p);
        }
        return smallestFace != null;

    }

    public boolean removeNode(PointDecimal p) {
        Face smallestFace = faces.smallestFaceforPoint(p);
        if (smallestFace != null && smallestFace != faces) {
            points.remove(smallestFace.getPoints()[0]);
            smallestFace.outerFace().merge();

        }
        return smallestFace != null && smallestFace != faces;
    }

    public LinkedList<PointDecimal> getPoints() {
        return points;
    }

    public int getNPoints() {
        return points.size();
    }

    public Face getFaces() {
        return faces;
    }
    
    private boolean contains(PointDecimal p){
        for (PointDecimal q : points){
            if (p.x().equals(q.x()) && p.y().equals(q.y()))
                return true;
        }
        return false;
    }

}
