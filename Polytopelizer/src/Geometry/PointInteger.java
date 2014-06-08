package Geometry;

import java.math.BigInteger;

public class PointInteger {
    private final BigInteger x, y, z;

    public PointInteger() {
        this(BigInteger.ZERO,BigInteger.ZERO,BigInteger.ZERO);
    }

    public PointInteger(BigInteger x, BigInteger y, BigInteger z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BigInteger x() {
        return this.x;
    }

    public BigInteger y() {
        return this.y;
    }

    public BigInteger z() {
        return this.z;
    }

    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}