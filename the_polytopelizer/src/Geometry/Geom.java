package Geometry;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Geom {

    // Determines the Determinant of an 3x3-Matrix (Rule of Sarrus)
    public static BigDecimal det(BigDecimal[][] m)
            throws IllegalMatrixException {
        try {
            return (m[0][0].multiply(m[1][1]).multiply(m[2][2]))
                    .add(m[0][1].multiply(m[1][2]).multiply(m[2][0]))
                    .add(m[0][2].multiply(m[1][0]).multiply(m[2][1]))
                    .subtract((m[0][2].multiply(m[1][1]).multiply(m[2][0])))
                    .subtract(m[0][0].multiply(m[1][2]).multiply(m[2][1]))
                    .subtract(m[0][1].multiply(m[1][0]).multiply(m[2][2]));
        } catch (Exception e) {
            throw new IllegalMatrixException();
        }

    }

    // Determines the Determinant of an 3x3-Matrix (Rule of Sarrus)
    public static BigInteger det(BigInteger[][] m)
            throws IllegalMatrixException {
        try {
            return (m[0][0].multiply(m[1][1]).multiply(m[2][2]))
                    .add(m[0][1].multiply(m[1][2]).multiply(m[2][0]))
                    .add(m[0][2].multiply(m[1][0]).multiply(m[2][1]))
                    .subtract((m[0][2].multiply(m[1][1]).multiply(m[2][0])))
                    .subtract(m[0][0].multiply(m[1][2]).multiply(m[2][1]))
                    .subtract(m[0][1].multiply(m[1][0]).multiply(m[2][2]));
        } catch (Exception e) {
            throw new IllegalMatrixException();
        }

    }

    // Determines the Determinant of an 4x4-Matrix
    // http://matheguru.com/lineare-algebra/207-determinante.html
    public static BigDecimal det4(BigDecimal[][] m)
            throws IllegalMatrixException {
        try {
            BigDecimal[][] m1 = { { m[1][1], m[1][2], m[1][3] },
                    { m[2][1], m[2][2], m[2][3] },
                    { m[3][1], m[3][2], m[3][3] } };
            BigDecimal[][] m2 = { { m[1][0], m[1][2], m[1][3] },
                    { m[2][0], m[2][2], m[2][3] },
                    { m[3][0], m[3][2], m[3][3] } };
            BigDecimal[][] m3 = { { m[1][0], m[1][1], m[1][3] },
                    { m[2][0], m[2][1], m[2][3] },
                    { m[3][0], m[3][1], m[3][3] } };
            BigDecimal[][] m4 = { { m[1][0], m[1][1], m[1][2] },
                    { m[2][0], m[2][1], m[2][2] },
                    { m[3][0], m[3][1], m[3][2] } };

            BigDecimal res = (m[0][0].multiply(det(m1)))
                    .subtract(m[0][1].multiply(det(m2)))
                    .add(m[0][2].multiply(det(m3)))
                    .subtract(m[0][3].multiply(det(m4)));
            return res;
        } catch (Exception e) {
            throw new IllegalMatrixException();
        }

    }

    // determines the line plane intersection
    // http://mathworld.wolfram.com/Line-PlaneIntersection.html
    public static PointDecimal linePlane(PointDecimal p1, PointDecimal p2,
            PointDecimal p3, PointDecimal p4, PointDecimal p5) {
        BigDecimal x = BigDecimal.ZERO;
        BigDecimal x1 = p1.x();
        BigDecimal x2 = p2.x();
        BigDecimal x3 = p3.x();
        BigDecimal x4 = p4.x();
        BigDecimal x5 = p5.x();
        BigDecimal y = BigDecimal.ZERO;
        BigDecimal y1 = p1.y();
        BigDecimal y2 = p2.y();
        BigDecimal y3 = p3.y();
        BigDecimal y4 = p4.y();
        BigDecimal y5 = p5.y();
        BigDecimal z = BigDecimal.ZERO;
        BigDecimal z1 = p1.z();
        BigDecimal z2 = p2.z();
        BigDecimal z3 = p3.z();
        BigDecimal z4 = p4.z();
        BigDecimal z5 = p5.z();
        BigDecimal t = BigDecimal.ZERO;

        BigDecimal[][] m1 = {
                { BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE,
                        BigDecimal.ONE }, { x1, x2, x3, x4 },
                { y1, y2, y3, y4 }, { z1, z2, z3, z4 } };
        BigDecimal[][] m2 = {
                { BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE,
                        BigDecimal.ZERO }, { x1, x2, x3, x5.add(x4.negate()) },
                { y1, y2, y3, y5.add(y4.negate()) },
                { z1, z2, z3, z5.add(z4.negate()) } };
        try {
            t = (det4(m1).negate()).divide(det4(m2), 50, BigDecimal.ROUND_DOWN);
        } catch (IllegalMatrixException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);
            System.out.println(p4);
            System.out.println(p5);
        }

        x = x4.add((x5.subtract(x4)).multiply(t));
        y = y4.add((y5.subtract(y4)).multiply(t));
        z = z4.add((z5.subtract(z4)).multiply(t));

        return new PointDecimal(x, y, z);

    }
}
