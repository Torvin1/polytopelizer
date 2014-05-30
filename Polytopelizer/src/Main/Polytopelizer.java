package Main;

import interfaces.*;
import Algorithm.*;
import Datastructures.*;

public class Polytopelizer {
    
    public static void main(String[] args){
          
    	
        ApollonianNetwork aN = new ApollNetwork();
        
        aN.addNode(5, 10);
        aN.addNode(15, 5);
        aN.addNode(2, 4);
        aN.addNode(5, 8);
        aN.addNode(20, 30);
        aN.addNode(30, 30);
        aN.addNode(35,40);
        aN.addNode(35, 38);
        
        
        System.out.println("Faces: "+aN.getFaces());
        
        StackedPolytope sP1 = Algorithm.calculateStackedPolytope1(aN);
        
        // StackedPolytope sP2 = DummyAlgorithm.calculateStackedPolytope2(aN);
        
        System.out.println("Stacked Polytope: "+sP1);
        
    }

}
