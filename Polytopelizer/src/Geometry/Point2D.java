package Geometry;

public class Point2D {
    private final double x, y;

    public Point2D() {
        this(0, 0);
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

}