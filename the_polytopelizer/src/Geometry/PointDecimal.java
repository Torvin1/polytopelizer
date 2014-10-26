package Geometry;

import java.math.BigDecimal;

public class PointDecimal {

    private final BigDecimal x, y, z;

    public PointDecimal() {
        this(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
    }

    /*
     * public PointDecimal(double x, double y) { this(x, y, 0); }
     * 
     * 
     * public PointDecimal(double x, double y, double z) { this(new
     * BigDecimal(x), new BigDecimal(y), new BigDecimal(z)); }
     */

    public PointDecimal(BigDecimal x, BigDecimal y) {
        this(x, y, BigDecimal.ZERO);
    }

    public PointDecimal(BigDecimal x, BigDecimal y, BigDecimal z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BigDecimal x() {
        return this.x;
    }

    public BigDecimal y() {
        return this.y;
    }

    public BigDecimal z() {
        return this.z;
    }

    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
    
    public boolean equals(PointDecimal p){
        return x.compareTo(p.x()) == 0 && y.compareTo(p.y()) == 0 && z.compareTo(p.z()) == 0;
    }
}