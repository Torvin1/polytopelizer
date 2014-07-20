package output;

import interfaces.*;
import Geometry.PointInteger;
import Main.Files;
import java.util.ArrayList;

public class Visualization {

    public static final String OUTPUTPATH = "tempData";

    public static void showPolytope(StackedPolytope sP) {

        Files.polytopeToFile(sP, OUTPUTPATH);

        ArrayList<ArrayList<Double>> vertices = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Integer>> faceIndices = new ArrayList<ArrayList<Integer>>();
        Transformation.iteratePolytope(sP, vertices, faceIndices);

        double[][] fixedList = Transformation.fixZCoordinates(Transformation
                .arrayListToArray2D(vertices));

        if (sP.smallerPolytopes() == null)
            new ViewerFrame(fixedList,
                    Transformation.arrayListToArrayInt2D(faceIndices),new PointInteger());
        else
            new ViewerFrame(fixedList,
                    Transformation.arrayListToArrayInt2D(faceIndices),
                    sP.smallerPolytopes()[0].getPoints()[0]);
    }
}