package Datastructures;

import java.math.BigDecimal;

import Geometry.*;
import interfaces.*;

public class ApollNetwork implements ApollonianNetwork {

    private Face faces;
    private int nPoints;// number of points

    // Create a new Apollonian Network with 3 Vertices and 3 Edges.
    public ApollNetwork(PointDecimal p1, PointDecimal p2, PointDecimal p3) {
        this.faces = new GraphFace(p1, p2, p3);
        this.nPoints = 3;
    }

    public ApollNetwork() {
        this(new PointDecimal(), new PointDecimal(BigDecimal.ZERO,
                new BigDecimal("100")), new PointDecimal(new BigDecimal("100"),
                BigDecimal.ZERO));
    }

    public boolean addNode(PointDecimal p) {
        Face smallestFace = faces.smallestFaceforPoint(p);
        System.out.println(smallestFace);
        if (smallestFace != null) {
            smallestFace.divide(p);
            nPoints++;
        }
        return smallestFace != null;

    }

    public boolean removeNode(PointDecimal p) {
        Face smallestFace = faces.smallestFaceforPoint(p);
        System.out.println(smallestFace);
        if (smallestFace != null && smallestFace != faces) {
            System.out.println("check");
            smallestFace.outerFace().merge();
            nPoints--;
        }
        return smallestFace != null && smallestFace != faces;
    }

    public int getNPoints() {
        return nPoints;
    }

    public Face getFaces() {
        return faces;
    }

}
