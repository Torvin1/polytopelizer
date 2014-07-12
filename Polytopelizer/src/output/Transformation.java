package output;

import interfaces.StackedPolytope;

import java.util.ArrayList;

public class Transformation {
    public static void iteratePolytope(StackedPolytope sP, boolean first) {
        if (first) {
            System.out.println("" + sP.getPoints()[0].x() + " "
                    + sP.getPoints()[0].y() + " " + sP.getPoints()[0].z());
            System.out.println("" + sP.getPoints()[1].x() + " "
                    + sP.getPoints()[1].y() + " " + sP.getPoints()[1].z());
            System.out.println("" + sP.getPoints()[2].x() + " "
                    + sP.getPoints()[2].y() + " " + sP.getPoints()[2].z());
            System.out.println();
        }
        if (sP.isBoundary()) {
            System.out.println("" + sP.getPoints()[0].x() + " "
                    + sP.getPoints()[0].y() + " " + sP.getPoints()[0].z());
            System.out.println("" + sP.getPoints()[1].x() + " "
                    + sP.getPoints()[1].y() + " " + sP.getPoints()[1].z());
            System.out.println("" + sP.getPoints()[2].x() + " "
                    + sP.getPoints()[2].y() + " " + sP.getPoints()[2].z());
            System.out.println();
        } else {
            for (int i = 0; i < sP.smallerPolytopes().length; i++) {
                iteratePolytope(sP.smallerPolytopes()[i], false);
            }
        }
    }

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

    public static int[] arrayListToArray(ArrayList<Integer> al, boolean a) {
        int[] ret = new int[al.size()];
        for (int i = 0; i < al.size(); i++) {
            ret[i] = al.get(i);
        }
        return ret;
    }
}