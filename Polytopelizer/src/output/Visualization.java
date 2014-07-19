package output;

import interfaces.*;
import Main.Files;
import java.util.ArrayList;

public class Visualization {

    public static final String OUTPUTPATH = "data";

    public static void showPolytope(StackedPolytope sP) {
        //Files.iteratePolytope(sP, true);

        Files.polytopeToFile(sP, OUTPUTPATH);

        ArrayList<ArrayList<Double>> vertices = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Integer>> faceIndices = new ArrayList<ArrayList<Integer>>();
        Transformation.iteratePolytope(sP, vertices, faceIndices);

        double[][] fixedList = Transformation.fixZCoordinates(Transformation.arrayListToArray2D(vertices));
        
        new ViewerFrame(fixedList, Transformation.arrayListToArrayInt2D(faceIndices), sP.smallerPolytopes()[0].getPoints()[0]);
    }
}
