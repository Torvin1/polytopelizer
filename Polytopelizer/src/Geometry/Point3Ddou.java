package Geometry;

public class Point3Ddou {

    private final double x, y, z;

    public Point3Ddou() {
        this(0, 0, 0);
    }

    public Point3Ddou(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double z() {
        return this.z;
    }

    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}