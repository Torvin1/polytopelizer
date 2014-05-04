package Main;

import interfaces.*;
import Algorithm.*;
import Datastructures.*;

public class Polytopelizer {
    
    public static void main(String[] args){
        
        ApollonianNetwork aN = new ApollNetwork();
        aN.addNode(50, 10);
        aN.addNode(33, 5);
        
        System.out.println("Faces: "+aN.getFaces());
        
        StackedPolytope sP1 = DummyAlgorithm.calculateStackedPolytope1(aN);
        
        // StackedPolytope sP2 = DummyAlgorithm.calculateStackedPolytope2(aN);
        
        System.out.println("Stacked Polytope: "+sP1);
        
    }

}
