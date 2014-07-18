package Algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;

//import output.data;

import Datastructures.*;
import Geometry.*;
import interfaces.*;

public class Algorithm {

    private static BigDecimal pert;
    private static BigDecimal pert_z;
    private static int prec=18; //the precision of the number of decimals to the division of a BigDecimal  
    private static StackedPolytope spInteger;
    private static StackedPolytopeDec spDecimal;

    
    public static StackedPolytope calculateStackedPolytope1(ApollonianNetwork aN) {

        BigInteger x, y, z;
        BigDecimal xD, yD, zD;
        PointInteger[] pI = new PointInteger[3];
        PointDecimal[] p = new PointDecimal[3];

        
        int n = aN.getNPoints();
        // Get the Faces of the Apollonian Network
        Face f = aN.getFaces();
        // produce the caterpillar Tree
        f.caterpillar();
        // balance the Tree
        f.balance();

        // algorithm 2
        // calculate the shift
        calcShift(f);

    
        // round
       
        pert = (new BigDecimal("240")).multiply(new BigDecimal(String.valueOf(Math.pow(n, 1.5))));
        
        // Calculate the first 3 Points of the StackedPolytope.
        //They are Defined as 
       
       int[] px = {0,1,0};
       int[] py = {0,0,1};
       
       for(int i=0;i<3;i++){
    	   xD = ((new BigDecimal(px[i])).multiply(pert).setScale(0,BigDecimal.ROUND_FLOOR)).divide(pert,prec,BigDecimal.ROUND_HALF_UP);
    	   yD = ((new BigDecimal(py[i])).multiply(pert).setScale(0,BigDecimal.ROUND_FLOOR)).divide(pert,prec,BigDecimal.ROUND_HALF_UP);
    	   zD = BigDecimal.ZERO;
    	   p[i] = new PointDecimal(xD, yD, zD);
       }
     
        // Create the StackedPolytopeDec. (It has just one simple Triangle.)
        Algorithm.spDecimal = new StackedPolytopeDecImpl(p[0], p[1], p[2]);
        Algorithm.spDecimal.mShift(f.vShift());

        
        // Compute the StackedPolytope on top of sP.
        // in the same time as it is maked it also makes the perturbation
        createPertPolytopDec(f, Algorithm.spDecimal);
      
                
        // lift
        pert_z = (new BigDecimal("3")).multiply(new BigDecimal(n));
        lift(Algorithm.spDecimal);

        //scale and transform to integers
                
        //Calculate the first 3 Points of the StackedPolytope.
        PointDecimal[] points = Algorithm.spDecimal.getPoints();
                
        for (int i = 0; i < 3; i++) {
            x = points[i].x().multiply(pert).toBigInteger();
            y = points[i].y().multiply(pert).toBigInteger();
            z = points[i].z().multiply(pert_z).toBigInteger();
            pI[i] = new PointInteger(x, y, z);
        }
        
        Algorithm.spInteger = new StackedPolytopeImpl(pI[0], pI[1], pI[2]);
        
        createAndScalePolytopInteger(Algorithm.spDecimal, Algorithm.spInteger);
        
        //data.writeData(Algorithm.spInteger,true);
        
        return Algorithm.spInteger;

    }
    
    public static StackedPolytopeDec showPolytopeDec(){
    	return Algorithm.spDecimal;
    }
    public static StackedPolytope showPolytopeInt(){
    	return Algorithm.spInteger;
    }

    public static StackedPolytope calculateStackedPolytope2(ApollonianNetwork aN) {
        return null;
    }

    private static void calcShift(Face f) {
        if (f.isSmallestFace())
            return;
        else {
            Face[] f_chil = f.smallerFaces();
            BigInteger x = f.vSpine();
            BigInteger y = x.add(BigInteger.ONE).mod(new BigInteger("3"));
            BigInteger shift = f_chil[x.intValue()].Weight().multiply(
                    f_chil[y.intValue()].Weight());
            f.mShift(shift);
            for (int i = 0; i < 3; i++) {
                calcShift(f_chil[i]);
            }
        }
    }

    private static void createPertPolytopDec(Face f, StackedPolytopeDec sP) {

        // If the Face is not a smallest Face, we compute the rest recursively.
        if (!f.isSmallestFace()) {
            Face[] f_neu = f.smallerFaces();
            PointDecimal[] p = sP.getPoints();
          
          //we need to calculate new coordinates from the balanced tree, so that the areas from the flat polytope are equal
          // to the weights from the faces
            //from: 
            // http://mathworld.wolfram.com/BarycentricCoordinates.html
            //  http://2000clicks.com/mathhelp/GeometryTriangleBarycentricCoordinates.aspx  
            //the formula to find the new point stacked on the face is:
            //P = t1*A + t2*B + t3*C, where t1,t2,t3 are proportional to the areas in opposition to the points A,B,C
            //in our case the formula is P = p0*f1 + p1*f2 + p2*f0, where f0 = f0/s, f1 = f1/s, f2 = f2/s, s = f0 + f1 + f2 
             
            BigDecimal f0, f1, f2,s,x,y,z;
            
            f0 = new BigDecimal(f_neu[0].Weight());
            f1 = new BigDecimal(f_neu[1].Weight());
            f2 = new BigDecimal(f_neu[2].Weight());
            
            s = f0.add(f1).add(f2);
            f0 = f0.divide(s,prec,BigDecimal.ROUND_HALF_UP);
            f1 = f1.divide(s,prec,BigDecimal.ROUND_HALF_UP);
            f2 = f2.divide(s,prec,BigDecimal.ROUND_HALF_UP);

            x= p[0].x().multiply(f1).add(p[1].x().multiply(f2)).add(p[2].x().multiply(f0));
            y= p[0].y().multiply(f1).add(p[1].y().multiply(f2)).add(p[2].y().multiply(f0));
                        
            //Make the rouding so that x and y are multiples of 1/pert
            x = (x.multiply(pert).setScale(0,BigDecimal.ROUND_FLOOR)).divide(pert,prec,BigDecimal.ROUND_HALF_UP);
            y = (y.multiply(pert).setScale(0,BigDecimal.ROUND_FLOOR)).divide(pert,prec,BigDecimal.ROUND_HALF_UP);
            z = BigDecimal.ZERO;

            
            PointDecimal p3D = new PointDecimal(x, y, z);

            sP.divide(p3D);

            StackedPolytopeDec[] sP_neu = sP.smallerPolytopes();
            //copy all the shifts 
            sP_neu[0].mShift(f_neu[0].vShift());
            sP_neu[1].mShift(f_neu[1].vShift());
            sP_neu[2].mShift(f_neu[2].vShift());

            createPertPolytopDec(f_neu[0], sP_neu[0]);
            createPertPolytopDec(f_neu[1], sP_neu[1]);
            createPertPolytopDec(f_neu[2], sP_neu[2]);
        }
        return;
    }
    
    
   
    
    private static void createAndScalePolytopInteger(StackedPolytopeDec sPdec,StackedPolytope sP){
  	
        if (!sPdec.isBoundary()) {
        	StackedPolytopeDec[] sPdec_neu = sPdec.smallerPolytopes();
            PointDecimal p = sPdec_neu[0].getPoints()[0];

            BigInteger x, y, z;
            x = p.x().multiply(pert).toBigInteger();
            y = p.y().multiply(pert).toBigInteger();
            z = p.z().multiply(pert_z).toBigInteger();

            PointInteger p3D = new PointInteger(x, y, z);

            sP.divide(p3D);

            StackedPolytope[] sP_neu = sP.smallerPolytopes();
 
            createAndScalePolytopInteger(sPdec_neu[0], sP_neu[0]);
            createAndScalePolytopInteger(sPdec_neu[1], sP_neu[1]);
            createAndScalePolytopInteger(sPdec_neu[2], sP_neu[2]);
        }
        return;
    }

    

    private static void lift(StackedPolytopeDec sP) {

        if (!sP.isBoundary()) {
            StackedPolytopeDec[] sP_neu = sP.smallerPolytopes();
            PointDecimal p = sP_neu[0].getPoints()[0];
            // lift

            //lift that point shift + actual_height and make it a multiple of 1/pert_z
            PointDecimal p0 = new PointDecimal(p.x(), p.y(), new BigDecimal(sP.vShift()).add(p.z()).multiply(pert_z).setScale(0,
                    BigDecimal.ROUND_FLOOR).divide(pert_z,prec,BigDecimal.ROUND_HALF_UP));

            
            changePoint(sP_neu[0],p, p0);
            changePoint(sP_neu[1],p, p0);
            changePoint(sP_neu[2],p, p0);

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

    //change all points with value point in the polytope to p0
	private static void changePoint(StackedPolytopeDec sP,PointDecimal point, PointDecimal p0) {
		int i;
		PointDecimal[] p = sP.getPoints();
		
		for ( i = 0; i < 3; i++) {
			 if(p[i].equals(point)){
				 p[i]=p0;
				 sP.mPoints(p[0], p[1], p[2]);
				 	 

				 if (!sP.isBoundary()) {
					 StackedPolytopeDec[] sP_neu = sP.smallerPolytopes();
			         
			         changePoint(sP_neu[0],point, p0);
			         changePoint(sP_neu[1],point, p0);
			         changePoint(sP_neu[2],point, p0);
			     }
			 }
	    }
		
	}

    private static void updateZ(StackedPolytopeDec sP) {
        // find the new heights of the subpolytops with the intersection of the
        // subface with the line containing the point stacked.

        if (!sP.isBoundary()) {

            PointDecimal[] pPlane = sP.getPoints();
            

            StackedPolytopeDec[] sP_neu = sP.smallerPolytopes();
            PointDecimal p = sP_neu[0].getPoints()[0];

            PointDecimal[] pLine = new PointDecimal[2];

            pLine[0] = new PointDecimal(p.x(), p.y(), BigDecimal.ZERO);
            pLine[1] = new PointDecimal(p.x(), p.y(), BigDecimal.ONE);

            PointDecimal point = Geom.linePlane(pPlane[0], pPlane[1],
                    pPlane[2], pLine[0], pLine[1]);

            PointDecimal p0 = new PointDecimal(p.x(), p.y(), point.z());

            changePoint(sP_neu[0],p, p0);
            changePoint(sP_neu[1],p, p0);
            changePoint(sP_neu[2],p, p0);

            // update the heights of the subPolytops
            updateZ(sP_neu[0]);
            updateZ(sP_neu[1]);
            updateZ(sP_neu[2]);

        }
        return;

    }
}
