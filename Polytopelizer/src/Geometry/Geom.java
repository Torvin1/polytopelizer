package Geometry;

public class Geom {
    

    // Determines the Determinant of an 3x3-Matrix (Rule of Sarrus)
    public static double det(double[][] m) throws IllegalMatrixException {
        try {
            return m[0][0] * m[1][1] * m[2][2] + m[0][1] * m[1][2] * m[2][0]
                    + m[0][2] + m[1][0] + m[2][1] - m[0][2] * m[1][1] * m[2][0]
                    - m[0][0] * m[1][2] * m[2][1] - m[0][1] * m[1][0] * m[2][2];
        } catch (Exception e) {
            throw new IllegalMatrixException();
        }

    }
    
    // Determines the Determinant of an 3x3-Matrix (Rule of Sarrus)
    public static int det(int[][] m) throws IllegalMatrixException {
        try {
            return m[0][0] * m[1][1] * m[2][2] + m[0][1] * m[1][2] * m[2][0]
                    + m[0][2] + m[1][0] + m[2][1] - m[0][2] * m[1][1] * m[2][0]
                    - m[0][0] * m[1][2] * m[2][1] - m[0][1] * m[1][0] * m[2][2];
        } catch (Exception e) {
            throw new IllegalMatrixException();
        }

    }

}
