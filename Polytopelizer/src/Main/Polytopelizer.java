package Main;

import java.math.BigDecimal;

import interfaces.*;
import Algorithm.*;
import Datastructures.*;
import Geometry.PointDecimal;

public class Polytopelizer {

    public static void main(String[] args) {

        ApollonianNetwork aN = new ApollNetwork();

        aN.addNode(new PointDecimal(new BigDecimal("5"), new BigDecimal("10")));
        aN.addNode(new PointDecimal(new BigDecimal("15"), new BigDecimal("5")));
        aN.addNode(new PointDecimal(new BigDecimal("2"), new BigDecimal("4")));
        aN.addNode(new PointDecimal(new BigDecimal("5"), new BigDecimal("8")));
        aN.addNode(new PointDecimal(new BigDecimal("20"), new BigDecimal("30")));
        aN.addNode(new PointDecimal(new BigDecimal("30"), new BigDecimal("30")));
        aN.addNode(new PointDecimal(new BigDecimal("35"), new BigDecimal("40")));
        aN.addNode(new PointDecimal(new BigDecimal("35"), new BigDecimal("38")));

        System.out.println("Faces: " + aN.getFaces());

        StackedPolytope sP1 = Algorithm.calculateStackedPolytope1(aN);

        // StackedPolytope sP2 = DummyAlgorithm.calculateStackedPolytope2(aN);

        System.out.println("Stacked Polytope: " + sP1);

    }

}
