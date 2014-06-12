package Input;

import java.awt.Graphics2D;
import java.math.BigDecimal;

import Geometry.PointDecimal;

public class drawLine {

    private static BigDecimal THICK = new BigDecimal(1);
    
    
    public static void DrawLine(PointDecimal p1, PointDecimal p2, Graphics2D g ) {
        BigDecimal x = p2.x().subtract(p1.x());
        BigDecimal y = p2.y().subtract(p1.y());
        BigDecimal s = y.multiply(new BigDecimal(4)).add(x.multiply(new BigDecimal(2)).add(THICK));
        BigDecimal ne = (x.add(y)).multiply(new BigDecimal(4));
        BigDecimal e = y.multiply(new BigDecimal(4));
        BigDecimal j = new BigDecimal(0);
        for(BigDecimal i = new BigDecimal(0); i.compareTo(x.multiply(new BigDecimal(-1))) == -1 ; i.add(THICK)){
            g.fillRect(p1.x().subtract(i).intValue(), p1.y().add(j).intValue(), THICK.intValue(), THICK.intValue());
            if(s.compareTo(new BigDecimal(0)) == 1){
                j.add(THICK);
                s.add(ne);
            }else{
                s.add(e);
            }
        }
        
        
        
    }
    
    
    public void mk1(PointDecimal p1, PointDecimal p2, Graphics2D g){
        BigDecimal x = p2.x().subtract(p1.x());
        BigDecimal y = p2.y().subtract(p1.y());
        BigDecimal s = y.multiply(new BigDecimal(4)).add(x.multiply(new BigDecimal(2))).add(THICK);
        BigDecimal ne = new BigDecimal(4).multiply((x.add(y)));
        BigDecimal e = y.multiply(new BigDecimal(4));
        BigDecimal j = new BigDecimal(0);
/*vergl*/        for(BigDecimal i = new BigDecimal(0); i.compareTo(x.multiply(new BigDecimal(-1))) == 1; i.add(THICK)){
            g.fillRect(p1.x().subtract(i).intValue(), p1.y().add(j).intValue(), THICK.intValue(), THICK.intValue());
            if(s.compareTo(new BigDecimal(0))== -1){
                j.add(THICK);
                s.add(ne);
            }else{
                s.add(e);
            }
        }
    }

    public void mg1(PointDecimal p1, PointDecimal p2, Graphics2D g){
        BigDecimal x = p2.x().subtract(p1.x());
        BigDecimal y = p2.y().subtract(p1.y());
        BigDecimal s = x.multiply(new BigDecimal(4)).add(y.multiply(new BigDecimal(2)).add(THICK));
        BigDecimal ne = x.add(y).multiply(new BigDecimal(4));
        BigDecimal e = x.multiply(new BigDecimal(4));
        BigDecimal i = new BigDecimal(0);
        for(BigDecimal j = new BigDecimal(0); j.compareTo(y.multiply(new BigDecimal(-1))) == -1; j.add(THICK)){
            g.fillRect(p1.x().subtract(i).intValue(), p1.y().add(j).intValue(), THICK.intValue(), THICK.intValue());
            if(s.compareTo(new BigDecimal(0)) == 1){
                i.add(THICK);
                s.add(ne);
            }else{
                s.add(e);
            }
        }
    }
    /*
    public void mkm1(PointDecimal p1, PointDecimal p2, Graphics2D g){
        BigDecimal x = p2.x().add(p1.x());
        BigDecimal y = p2.y().subtract(p1.y());
        BigDecimal s =  x.multiply(new BigDecimal(4)).subtract(y.multiply(new BigDecimal(2)).add(THICK));
        BigDecimal ne = (x.subtract(y)).multiply(new BigDecimal(4));
        BigDecimal e = x.multiply(new BigDecimal(4));
        BigDecimal i = new BigDecimal(0);
        for(BigDecimal j = 0; j < y; j+=THICK){
            g.fillRect(p1.x + i, p1.y + j, THICK, THICK);
            if(s > 0){
                i += THICK;
                s += ne;
            }else{
                s += e;
            }
        }
    }
    
    public void mgm1(PointDecimal p1, PointDecimal p2, Graphics2D g){
        BigDecimal x = p2.x().subtract(p1.x());
        BigDecimal y = p2.y().subtract(p1.y());
        BigDecimal s = y.multiply(new BigDecimal(4)).subtract(x.multiply(new BigDecimal(2)).add(THICK));
        BigDecimal ne = 4*(y-x);
        BigDecimal e = 4*y;
        BigDecimal j = 0;
        for(BigDecimal i = 0; i < x; i+=THICK){
            g.fillRect(p1.x() + i, p1.y()+j, THICK, THICK);
            if(s > 0){
                j += THICK;
                s += ne;
            }else{
                s += e;
            }
        }
    }    

public void mypaint(PointDecimal p1, PointDecimal p2, Graphics2D g) {
    double m;
    
    if (p1.x() != p2.x()) {
        m = (double)(p2.y() - p1.y()) / (double)(p2.x() - p1.x());
    }else {
        if(p1.y() > p2.y()){
            g.fillRect(p1.x(), p2.y(), 1, p1.y() - p2.y());
            //g.fillRect(p1.x, p2.y, 4, p1.y - p2.y);
        }else{
            g.fillRect(p1.x(), p1.y(), 1, p2.y() - p1.y());
            //g.fillRect(p1.x, p2.y, 4, p1.y - p2.y);
        }
        return;
    }
    m = -m;

    // rechts vonscheidung nach der Steigung
    if (m > 0 && m <= 1) {
        if (p1.x() < p2.x()) {
            // p1 links von p2
            a.mk1(p2, p1, g);
        } else {
            // p1 rechts von p2
            a.mk1(p1, p2, g);
        }
    } else if (m >= 1) {
        if (p1.x() < p2.x()) {
            // p1 links von p2
            a.mg1(p1, p2, g);
        } else {
            // p1 rechts von p2
            a.mg1(p2, p1, g);
        }
    } else if (m <= -1) {
        if (p1.x() < p2.x()) {
            // p1 links von p2
            a.mkm1(p1, p2, g);
        } else {
            // p1 rechts von p2
            a.mkm1(p2, p1, g);
        }
    } else if (m > -1) {
        if (p1.x() < p2.x()) {
            // p1 links von p2
            a.mgm1(p1, p2, g);
        } else {
            // p1 rechts von p2
            a.mgm1(p2, p1, g);
        }
    }
}*/
}