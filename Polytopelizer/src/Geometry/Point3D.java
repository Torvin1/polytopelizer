package Geometry;

public class Point3D {
    private final long x, y, z;

    public Point3D() {
        this(0, 0, 0);
    }

    public Point3D(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long x() {
        return this.x;
    }

    public long y() {
        return this.y;
    }

    public long z() {
        return this.z;
    }

    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}