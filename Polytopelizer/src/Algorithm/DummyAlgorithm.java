package Algorithm;

import Datastructures.*;
import Geometry.*;
import interfaces.*;

public class DummyAlgorithm {

    public static StackedPolytope calculateStackedPolytope1(ApollonianNetwork aN) {

        // Get the Faces of the Apollonian Network
        Face f = aN.getFaces();

        // Calculate the first 3 Points of the StackedPolytope.
        Point2D[] points = f.getPoints();
        Point3D p1 = new Point3D(Math.round(points[0].x()) * 2,
                Math.round(points[0].y()) * 2, Math.round(points[0].x())
                        + Math.round(points[0].y()));
        Point3D p2 = new Point3D(Math.round(points[1].x()) * 2,
                Math.round(points[1].y()) * 2, Math.round(points[1].x())
                        + Math.round(points[1].y()));
        Point3D p3 = new Point3D(Math.round(points[2].x()) * 2,
                Math.round(points[2].y()) * 2, Math.round(points[2].x())
                        + Math.round(points[2].y()));

        // Create the StackedPolytope. (It has just one simple Triangle.)
        StackedPolytope sP = new StackedPolytopeImpl(p1, p2, p3);

        // Now compute the StackedPolytope on top of sP.
        face2stackedPolytope(f, sP);

        return sP;
    }

    public static StackedPolytope calculateStackedPolytope2(ApollonianNetwork aN) {
        return null;
    }

    private static void face2stackedPolytope(Face f, StackedPolytope sP) {

        // If the Face is not a smallest Face, we compute the rest recursively.
        if (!f.isSmallestFace()) {
            Face[] f_neu = f.smallerFaces();
            Point2D p = f_neu[0].getPoints()[0];
            Point3D p3D = new Point3D(Math.round(p.x()) * 10,
                    Math.round(p.y()) * 10, Math.round(p.x())
                            + Math.round(p.y()));
            sP.divide(p3D);
            StackedPolytope[] sP_neu = sP.smallerPolytopes();
            face2stackedPolytope(f_neu[0], sP_neu[0]);
            face2stackedPolytope(f_neu[1], sP_neu[1]);
            face2stackedPolytope(f_neu[2], sP_neu[2]);
        }
        return;
    }
}