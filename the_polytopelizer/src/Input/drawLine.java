package Input;

import java.awt.Graphics2D;
import java.math.BigDecimal;

import Geometry.PointDecimal;

public class drawLine {

    private static BigDecimal THICK = new BigDecimal(1);

    public static void DrawLine(PointDecimal p1, PointDecimal p2, Graphics2D g) {
        BigDecimal x = p2.x().subtract(p1.x());
        BigDecimal y = p2.y().subtract(p1.y());
        BigDecimal s = y.multiply(new BigDecimal(4)).add(
                x.multiply(new BigDecimal(2)).add(THICK));
        BigDecimal ne = (x.add(y)).multiply(new BigDecimal(4));
        BigDecimal e = y.multiply(new BigDecimal(4));
        BigDecimal j = new BigDecimal(0);
        for (BigDecimal i = new BigDecimal(0); i.compareTo(x
                .multiply(new BigDecimal(-1))) == -1; i.add(THICK)) {
            g.fillRect(p1.x().subtract(i).intValue(), p1.y().add(j).intValue(),
                    THICK.intValue(), THICK.intValue());
            if (s.compareTo(new BigDecimal(0)) == 1) {
                j.add(THICK);
                s.add(ne);
            } else {
                s.add(e);
            }
        }
    }

    public void mk1(PointDecimal p1, PointDecimal p2, Graphics2D g) {
        BigDecimal x = p2.x().subtract(p1.x());
        BigDecimal y = p2.y().subtract(p1.y());
        BigDecimal s = y.multiply(new BigDecimal(4))
                .add(x.multiply(new BigDecimal(2))).add(THICK);
        BigDecimal ne = new BigDecimal(4).multiply((x.add(y)));
        BigDecimal e = y.multiply(new BigDecimal(4));
        BigDecimal j = new BigDecimal(0);
        /* vergl */for (BigDecimal i = new BigDecimal(0); i.compareTo(x
                .multiply(new BigDecimal(-1))) == 1; i.add(THICK)) {
            g.fillRect(p1.x().subtract(i).intValue(), p1.y().add(j).intValue(),
                    THICK.intValue(), THICK.intValue());
            if (s.compareTo(new BigDecimal(0)) == -1) {
                j.add(THICK);
                s.add(ne);
            } else {
                s.add(e);
            }
        }
    }

    public void mg1(PointDecimal p1, PointDecimal p2, Graphics2D g) {
        BigDecimal x = p2.x().subtract(p1.x());
        BigDecimal y = p2.y().subtract(p1.y());
        BigDecimal s = x.multiply(new BigDecimal(4)).add(
                y.multiply(new BigDecimal(2)).add(THICK));
        BigDecimal ne = x.add(y).multiply(new BigDecimal(4));
        BigDecimal e = x.multiply(new BigDecimal(4));
        BigDecimal i = new BigDecimal(0);
        for (BigDecimal j = new BigDecimal(0); j.compareTo(y
                .multiply(new BigDecimal(-1))) == -1; j.add(THICK)) {
            g.fillRect(p1.x().subtract(i).intValue(), p1.y().add(j).intValue(),
                    THICK.intValue(), THICK.intValue());
            if (s.compareTo(new BigDecimal(0)) == 1) {
                i.add(THICK);
                s.add(ne);
            } else {
                s.add(e);
            }
        }
    }
}