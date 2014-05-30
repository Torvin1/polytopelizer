package Algorithm;

import Datastructures.*;
import Geometry.*;
import interfaces.*;

public class Algorithm {

    public static StackedPolytope calculateStackedPolytope1(ApollonianNetwork aN) {

        long x,y,z;
        int i;
        Point3D[] p = new Point3D[3];

        long n = aN.getNPoints();
    	// Get the Faces of the Apollonian Network
        Face f = aN.getFaces();

        //produce the caterpillar Tree
        f.caterpillar();
        //balance the Tree
        f.balance();

        //algorithm 2
        //calculate the shift
        calcShift(f);
        
        //round and lift
       
        long pert = 240;
        
        
        // Calculate the first 3 Points of the StackedPolytope.
        Point2D[] points = f.getPoints();
        for (i=0; i <3; i++){
        	x = Math.round(points[i].x());
            y= Math.round(points[i].y());
            z = 0;
            p[i] = new Point3D(x,y,z);
		}
        
        
        // Create the StackedPolytope. (It has just one simple Triangle.)
        StackedPolytope sP = new StackedPolytopeImpl(p[0], p[1], p[2]);

        // Now compute the StackedPolytope on top of sP.
        face2stackedPolytope(f, sP);

        return sP;
   
    }

    public static StackedPolytope calculateStackedPolytope2(ApollonianNetwork aN) {
        return null;
    }

    private static void calcShift(Face f){
      	int i;
      	if (f.isSmallestFace())
    		return;
    	else{
    		Face[] f_chil = f.smallerFaces();
    		
    		int x = f.vSpine();
    		int y =	(x +1)%3;
    		long shift = f_chil[x].Weight()*f_chil[y].Weight();
    		f.mShift(shift);
    		for (i=0; i <3; i++){
    			calcShift(f_chil[i]);
    		}
    	}
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