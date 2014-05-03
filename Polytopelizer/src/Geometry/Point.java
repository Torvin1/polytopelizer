package Geometry;

public class Point {
    private final double x, y, z;
    
    public Point(){
        this(0,0,0);
    }
    
    public Point(double x, double y){   // Bjoern and Phil: you have to use this Constructor
        this(x,y,0);
    }

    public Point(double x, double y, double z){ // Henrique and Patrick: this Constructor is for you
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x(){
        return this.x;
    }

    public double y(){
        return this.y;
    }

    public double z(){
        return this.z;
    }
}