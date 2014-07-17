package schnyder;

import java.math.BigDecimal;

import output.Visualization;
import Algorithm.Algorithm;
import Datastructures.ApollNetwork;
import Geometry.PointDecimal;
import interfaces.ApollonianNetwork;

public class SchnyderAlgorithm {
    public ApollonianNetwork impl(TernaryTreeImpl t){
        if(t.getRootTriangle().getSmallerTriangle() != null){
            ApollonianNetwork aN;
            aN = new ApollNetwork(new PointDecimal(new BigDecimal("" + t.getRootTriangle().getNrOfTriangles()), BigDecimal.ZERO),
                    new PointDecimal(BigDecimal.ZERO, new BigDecimal("" + t.getRootTriangle().getNrOfTriangles())),  new PointDecimal());
            help(t.getRootTriangle(), aN);
            return aN;
        }else{
            return new ApollNetwork(new PointDecimal(), new PointDecimal(BigDecimal.ZERO, new BigDecimal("1")),
                    new PointDecimal(new BigDecimal("1"), BigDecimal.ZERO));
        }
    }
    
    private void help(Triangle t, ApollonianNetwork aN){
        if(t.smallerTriangle != null){
            int x = calculateX(t);
            int y = calculateY(t);
            aN.addNode(new PointDecimal(new BigDecimal("" + x), new BigDecimal("" + y)));
            if(t.smallerTriangle[0].nrOfTriangle > 1){
                help(t.smallerTriangle[0], aN);
            }
            if(x > 1){
                help(t.smallerTriangle[1], aN);
            }
            if(y > 1){
                help(t.smallerTriangle[2], aN);
            }
        }
    }
    
    private int calculateX(Triangle t){
        int x = t.getSmallerTriangle()[1].nrOfTriangle;
        if(t.parent != null && cmpTriangle(t.parent.getSmallerTriangle()[1], t)){
            t = t.parent;
        }
        while(t.parent != null){
            x += t.parent.getSmallerTriangle()[1].nrOfTriangle;
            t = t.parent;
        }
        return x;
    }
    
    private int calculateY(Triangle t){
        int y = t.getSmallerTriangle()[2].nrOfTriangle;
        if(t.parent != null && cmpTriangle(t.parent.getSmallerTriangle()[2], t)){
            t = t.parent;
        }
        while(t.parent != null){
            y += t.parent.getSmallerTriangle()[2].nrOfTriangle;
            t = t.parent;
        }
        return y;
    }
    
    private boolean cmpTriangle(Triangle t1, Triangle t2){
        for(int i = 0; i < t1.points.length; i++){
            if(t1.points[i] != t2.points[i]){
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args){
        TernaryTreeImpl t = new TernaryTreeImpl();
        t.addNode(t.rootTriangle);
        t.addNode(t.rootTriangle.getSmallerTriangle()[0]);
        t.addNode(t.rootTriangle.getSmallerTriangle()[1]);
        t.addNode(t.rootTriangle.getSmallerTriangle()[2]);
        t.addNode(t.rootTriangle.getSmallerTriangle()[0].getSmallerTriangle()[0]);
        t.addNode(t.rootTriangle.getSmallerTriangle()[0].getSmallerTriangle()[0].getSmallerTriangle()[0]);
        t.addNode(t.rootTriangle.getSmallerTriangle()[0].getSmallerTriangle()[0].getSmallerTriangle()[1]);
        t.addNode(t.rootTriangle.getSmallerTriangle()[0].getSmallerTriangle()[0].getSmallerTriangle()[2]);
        
        SchnyderAlgorithm sa = new SchnyderAlgorithm();
        ApollonianNetwork aN = sa.impl(t);

        Algorithm.calculateStackedPolytope1(aN);
        Visualization.showPolytope(Algorithm.showPolytopeInt());
        for(int i = 0; i < aN.getPoints().size(); i++){
            System.out.println(aN.getPoints().get(i));
        }
    }
}
