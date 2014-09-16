package output;

import interfaces.StackedPolytope;

import java.util.ArrayList;

public class Transformation {

    public static void iteratePolytope(StackedPolytope sP,
            ArrayList<ArrayList<Double>> al, ArrayList<ArrayList<Integer>> al2) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < sP.getPoints().length; i++) {
            ArrayList<Double> a = new ArrayList<Double>();
            a.add(sP.getPoints()[i].x().doubleValue());
            a.add(sP.getPoints()[i].y().doubleValue());
            a.add(sP.getPoints()[i].z().doubleValue());
            if (!al.contains(a)) {
                al.add(a);
            }
            indices.add(al.indexOf(a));
        }
        al2.add(indices);

        iteratePolytopeHelp(sP, al, al2);
    }

    private static void iteratePolytopeHelp(StackedPolytope sP,
            ArrayList<ArrayList<Double>> al, ArrayList<ArrayList<Integer>> al2) {
        if (!sP.isBoundary()) {
            StackedPolytope[] smallerPolytops = sP.smallerPolytopes();
            for (int i = 0; i < smallerPolytops.length; i++) {
                iteratePolytopeHelp(smallerPolytops[i], al, al2);
            }
        } else {
            ArrayList<Integer> indices = new ArrayList<Integer>();
            for (int i = 0; i < sP.getPoints().length; i++) {
                ArrayList<Double> a = new ArrayList<Double>();
                a.add(((Long) sP.getPoints()[i].x().longValue()).doubleValue());
                a.add(((Long) sP.getPoints()[i].y().longValue()).doubleValue());
                a.add(((Long) sP.getPoints()[i].z().longValue()).doubleValue());
                if (!al.contains(a)) {
                    al.add(a);
                }
                indices.add(al.indexOf(a));
            }
            al2.add(indices);
        }
    }
    
    public static double[] arrayListToArray(ArrayList<Double> al) {
        double[] ret = new double[al.size()];
        for (int i = 0; i < al.size(); i++) {
            ret[i] = al.get(i);
        }
        return ret;
    }
    
    public static double[][] arrayListToArray2D(ArrayList<ArrayList<Double>> al) {
        ArrayList<double[]> vertices1 = new ArrayList<double[]>();
        for (int i = 0; i < al.size(); i++) {
            vertices1.add(Transformation.arrayListToArray(al.get(i)));
        }
        double[][] vertices = vertices1.toArray(new double[vertices1.size()][]);
        return vertices;
    }

    public static int[] arrayListToArrayInt(ArrayList<Integer> al) {
        int[] ret = new int[al.size()];
        for (int i = 0; i < al.size(); i++) {
            ret[i] = al.get(i);
        }
        return ret;
    }
    public static int[][] arrayListToArrayInt2D(ArrayList<ArrayList<Integer>> al) {
        ArrayList<int[]> faceIndices1 = new ArrayList<int[]>();
        for (int i = 0; i < al.size(); i++) {
            faceIndices1.add(Transformation.arrayListToArrayInt(al.get(i)));
        }
        int[][] faceIndices = faceIndices1.toArray(new int[faceIndices1.size()][]);
        return faceIndices;
    }
    
    public static double[][] fixZCoordinatesForDisplay(double[][] coordinates){
        double xMax = -1;
        double yMax = -1;
        double zMax = -1; 
        for(int i = 0; i < coordinates.length; i++){
            if(xMax < coordinates[i][0]){
                xMax = coordinates[i][0];
            }
            if(yMax < coordinates[i][1]){
                yMax = coordinates[i][1];
            }
            if(zMax < coordinates[i][2]){
                zMax = coordinates[i][2];
            }
        }
        double xyMax;
        if(xMax < yMax){
            xyMax = yMax;
        }else{
            xyMax = xMax;
        }
        double multp = ((double)(xyMax/2))/((double)zMax);
        if(zMax < (xyMax/2)){
            for(int i = 0; i < coordinates.length; i++){
                coordinates[i][2] = (int)(coordinates[i][2] * multp) + 0.0d;
            }
        }else if(zMax > (xyMax/2)){
            for(int i = 0; i < coordinates.length; i++){
                coordinates[i][2] = (int)(coordinates[i][2] * multp) + 0.0d;
            }
        }
        return coordinates;
    }
}