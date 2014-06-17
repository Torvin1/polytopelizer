package Output;

import interfaces.StackedPolytope;
import java.util.ArrayList;

public class Transformation {
    public static void iteratePolytope(StackedPolytope sP, ArrayList<ArrayList<Double>> al, ArrayList<ArrayList<Integer>> al2){
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for(int i = 0; i < sP.getPoints().length; i++){
            ArrayList<Double> a = new ArrayList<Double>();
            a.add(((Long)sP.getPoints()[i].x().longValue()).doubleValue());
            a.add(((Long)sP.getPoints()[i].y().longValue()).doubleValue());
            a.add(((Long)sP.getPoints()[i].z().longValue()).doubleValue());
            if(!al.contains(a)){
                al.add(a);
            }
            indices.add(al.indexOf(a));
        }
        al2.add(indices);
        
        iteratePolytopeHelp(sP, al, al2);
    }
    
    private static void iteratePolytopeHelp(StackedPolytope sP, ArrayList<ArrayList<Double>> al, ArrayList<ArrayList<Integer>> al2){
        if(!sP.isBoundary()){
            StackedPolytope[] smallerPolytops = sP.smallerPolytopes();
            for(int i = 0; i < smallerPolytops.length; i++){
                iteratePolytopeHelp(smallerPolytops[i], al, al2);
            }
        }else{
            ArrayList<Integer> indices = new ArrayList<Integer>();
            for(int i = 0; i < sP.getPoints().length; i++){
                ArrayList<Double> a = new ArrayList<Double>();
                a.add(((Long)sP.getPoints()[i].x().longValue()).doubleValue());
                a.add(((Long)sP.getPoints()[i].y().longValue()).doubleValue());
                a.add(((Long)sP.getPoints()[i].z().longValue()).doubleValue());
                if(!al.contains(a)){
                    al.add(a);
                }
                indices.add(al.indexOf(a));
            }
            al2.add(indices);
        }
    }
}