package Main;

import java.math.BigDecimal;

import output.Test;
import interfaces.*;
import Algorithm.*;
import Datastructures.*;
import Geometry.PointDecimal;

public class Polytopelizer {

    public static void main(String[] args) {

        ApollonianNetwork aN = new ApollNetwork(new PointDecimal(
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                new PointDecimal(BigDecimal.ZERO, new BigDecimal("2"),
                        BigDecimal.ZERO), new PointDecimal(new BigDecimal("2"),
                        BigDecimal.ONE, BigDecimal.ZERO));

        aN.addNode(new PointDecimal(new BigDecimal("0.5"), new BigDecimal("0.5")));
        aN.addNode(new PointDecimal(new BigDecimal("1"), new BigDecimal("1")));
        /*
         * aN.addNode(new PointDecimal(new BigDecimal("2"), new
         * BigDecimal("4"))); aN.addNode(new PointDecimal(new BigDecimal("5"),
         * new BigDecimal("8"))); aN.addNode(new PointDecimal(new
         * BigDecimal("20"), new BigDecimal("30"))); aN.addNode(new
         * PointDecimal(new BigDecimal("30"), new BigDecimal("30")));
         * aN.addNode(new PointDecimal(new BigDecimal("35"), new
         * BigDecimal("40"))); aN.addNode(new PointDecimal(new BigDecimal("35"),
         * new BigDecimal("38")));
         */

        System.out.println("Faces: " + aN.getFaces());
        
      

        StackedPolytope sP1 = Algorithm.calculateStackedPolytope1(aN);
        System.out.println("Stacked Polytope: " + sP1);
        
        StackedPolytopeDec  sP2 = Algorithm.showPolytopeDec(); 
        System.out.println("Stacked Polytope: " + sP2);

        
        StackedPolytope  sP3 = Algorithm.showPolytopeInt(); 
        System.out.println("Stacked Polytope: " + sP3);
        
        // StackedPolytope sP2 = DummyAlgorithm.calculateStackedPolytope2(aN);

        //Test.showPolytope(sP1);

       

    }
}
