package Algorithm;

import Datastructures.*;
import Geometry.*;
import interfaces.*;

public class Algorithm {

    private static double pert;
    private static double pert_z;
	public static StackedPolytope calculateStackedPolytope1(ApollonianNetwork aN){

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
        
        //round 
       
        pert = 240*Math.pow(n,1.5);
               
      
        // Calculate the first 3 Points of the StackedPolytope.
        Point2D[] points = f.getPoints();
        for (i=0; i <3; i++){
        	x = (long) Math.floor(points[i].x()*pert);
            y = (long) Math.floor(points[i].y()*pert);
            z = 0;
            p[i] = new Point3D(x,y,z);
		}
                
        // Create the StackedPolytope. (It has just one simple Triangle.)
        StackedPolytope sP = new StackedPolytopeImpl(p[0], p[1], p[2]);
        sP.mShift(f.vShift());
        
        // Compute the StackedPolytope on top of sP.
        //first scale the x and y
        createAndScalePolytopXY(f, sP);
        
        //lift
        pert_z =3*n;
        lift(sP);
        
        
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
    private static void createAndScalePolytopXY(Face f, StackedPolytope sP) {

        // If the Face is not a smallest Face, we compute the rest recursively.
        if (!f.isSmallestFace()) {
            Face[] f_neu = f.smallerFaces();
            Point2D p = f_neu[0].getPoints()[0];
            
            long x,y,z;
            x = (long) Math.floor(p.x()*pert);
            y = (long) Math.floor(p.y()*pert);
            z = 0;
            
            Point3D p3D = new Point3D(x,y,z);
                       
            sP.divide(p3D);
   
            StackedPolytope[] sP_neu = sP.smallerPolytopes();
            sP_neu[0].mShift(f_neu[0].vShift());
            sP_neu[1].mShift(f_neu[1].vShift());
            sP_neu[2].mShift(f_neu[2].vShift());
            
            createAndScalePolytopXY(f_neu[0], sP_neu[0]);
            createAndScalePolytopXY(f_neu[1], sP_neu[1]);
            createAndScalePolytopXY(f_neu[2], sP_neu[2]);
        }
        return;
    }
    
    
    private static void lift(StackedPolytope sP){
    
    	
    	if(!sP.isBoundary()){
    		StackedPolytope[] sP_neu = sP.smallerPolytopes();
    		Point3D p = sP_neu[0].getPoints()[0];
    		//lift
    		    		
    		Point3D p0 = new Point3D(p.x(),p.y(),(long) Math.floor(sP.vShift()*pert_z + p.z())); 
    		
    		changePoint(sP_neu,p0);
    		
    	     //update the heights of the subPolytops
    		updateZ(sP_neu[0]);
    		updateZ(sP_neu[1]);
    		updateZ(sP_neu[2]);
    		
    		lift(sP_neu[0]);
    		lift(sP_neu[1]);
    		lift(sP_neu[2]);
      
   
    	}
    	return;
    }
    


    private static void changePoint(StackedPolytope[] sP, Point3D p0){
	
    	for(int i=0;i<3;i++){
    		Point3D p1 = sP[i].getPoints()[1];
    		Point3D p2 = sP[i].getPoints()[2];
    		sP[i].mPoints(p0, p1, p2);
    	}
   }	
    
    private static void updateZ(StackedPolytope sP){
	//find the new heights of the subpolytops with the intersection of the subface with the line containing the point stacked.
	
    	if(!sP.isBoundary()){
    		
    		Point3D[] points = sP.getPoints();
    		double x,y,z;
    		Point3Ddou[] pointsDou = new Point3Ddou[5];
    		for (int i=0; i<3; i++){
    			x = points[i].x();
    			y = points[i].y();
    			z = points[i].z();
    			
    			pointsDou[i] = new Point3Ddou(x,y,z);
    		}
    		
    		    		
    		StackedPolytope[] sP_neu = sP.smallerPolytopes();
    		Point3D p = sP_neu[0].getPoints()[0];
    		
    		x = p.x();
    		y =p.y();
    		
    		pointsDou[3] = new Point3Ddou(x,y,0);
    		pointsDou[4] = new Point3Ddou(x,y,1);
    		
    		Point3Ddou pointDou = Geom.linePlane(pointsDou[0], pointsDou[1],pointsDou[2], pointsDou[3], pointsDou[4]);
    		
    		z = pointDou.z();
    		
    		Point3D p0 = new Point3D(p.x(),p.y(),Math.round(z));    		
    		
    		changePoint(sP_neu,p0);
    		
    	     //update the heights of the subPolytops
    		updateZ(sP_neu[0]);
    		updateZ(sP_neu[1]);
    		updateZ(sP_neu[2]);
    		     
   
    	}
    	return;
    	

    	    
    }	
}
