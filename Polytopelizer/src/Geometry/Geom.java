package Geometry;

public class Geom {
    

    // Determines the Determinant of an 3x3-Matrix (Rule of Sarrus)
    public static double det(double[][] m) throws IllegalMatrixException {
        try {
            return m[0][0] * m[1][1] * m[2][2] + m[1][0] * m[2][1] * m[0][2]
                    + m[2][0] * m[0][1] * m[1][2] - m[2][0] * m[1][1] * m[0][2]
                    - m[0][0] * m[2][1] * m[1][2] - m[1][0] * m[0][1] * m[2][2];
        } catch (Exception e) {
            throw new IllegalMatrixException();
        }

    }
    
    // Determines the Determinant of an 3x3-Matrix (Rule of Sarrus)
    public static int det(int[][] m) throws IllegalMatrixException {
        try {
            return m[0][0] * m[1][1] * m[2][2] + m[0][1] * m[1][2] * m[2][0]
                    + m[0][2] * m[1][0] * m[2][1] - m[0][2] * m[1][1] * m[2][0]
                    - m[0][0] * m[1][2] * m[2][1] - m[0][1] * m[1][0] * m[2][2];
        } catch (Exception e) {
            throw new IllegalMatrixException();
        }

    }

   
    // Determines the Determinant of an 4x4-Matrix 
    //http://matheguru.com/lineare-algebra/207-determinante.html
    public static double det4(double[][] m) throws IllegalMatrixException {
        try {
        	double[][] m1 = {{m[1][1],m[1][2],m[1][3]},{m[2][1],m[2][2],m[2][3]},{m[3][1],m[3][2],m[3][3]}};
        	double[][] m2 = {{m[1][0],m[1][2],m[1][3]},{m[2][0],m[2][2],m[2][3]},{m[3][0],m[3][2],m[3][3]}};
        	double[][] m3 = {{m[1][0],m[1][1],m[1][3]},{m[2][0],m[2][1],m[2][3]},{m[3][0],m[3][1],m[3][3]}};        	
        	double[][] m4 = {{m[1][0],m[1][1],m[1][2]},{m[2][0],m[2][1],m[2][2]},{m[3][0],m[3][1],m[3][2]}};
        	
        	double res = m[0][0]*det(m1) - m[0][1]*det(m2) + m[0][2]*det(m3) -m[0][3]*det(m4);
        	return res;
        } catch (Exception e) {
            throw new IllegalMatrixException();
        }

    }
  
    //determines the line plane intersection
    //http://mathworld.wolfram.com/Line-PlaneIntersection.html
    public static Point3Ddou linePlane(Point3Ddou p1, Point3Ddou p2, Point3Ddou p3, Point3Ddou p4, Point3Ddou p5) {
    	double x,x1,x2,x3,x4,x5,y,y1,y2,y3,y4,y5,z,z1,z2,z3,z4,z5,t=0;

    	x1 = p1.x(); x2 = p2.x(); x3 = p3.x(); x4 = p4.x(); x5 = p5.x();
    	y1 = p1.y(); y2 = p2.y(); y3 = p3.y(); y4 = p4.y(); y5 = p5.y();
    	z1 = p1.z(); z2 = p2.z(); z3 = p3.z(); z4 = p4.z(); z5 = p5.z();
    	
    	double[][] m1 = {{1,1,1,1},{x1,x2,x3,x4},{y1,y2,y3,y4},{z1,z2,z3,z4}};
    	double[][] m2 = {{1,1,1,0},{x1,x2,x3,x5-x4},{y1,y2,y3,y5-y4},{z1,z2,z3,z5-z4}};
    	
    	try {
			t = -det4(m1)/det4(m2);
    	} catch (IllegalMatrixException e) {//have to make a other exception for det4(m2)=0
			e.printStackTrace();
		}
			
    	x=x4 + (x5-  x4)*t;
    	y=y4 + (y5 - y4)*t;
    	z=z4 + (z5 - z4)*t;
    	
    	return new Point3Ddou(x,y,z);	
		
    	

	}
    
}
