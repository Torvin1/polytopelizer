package output;

import interfaces.*;
import Algorithm.*;
import Datastructures.*;
import Geometry.*;
import Main.Files;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import de.jreality.geometry.IndexedFaceSetFactory;
import de.jreality.plugin.JRViewer;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.Viewer;
import de.jreality.util.CameraUtility;

public class Visualization {

    public static final String OUTPUTPATH = "data";

    public static void main(String[] args) {
        ApollonianNetwork aN = new ApollNetwork(new PointDecimal(
                BigDecimal.ZERO, BigDecimal.ZERO), new PointDecimal(
                BigDecimal.TEN, BigDecimal.ZERO), new PointDecimal(
                BigDecimal.ZERO, BigDecimal.TEN));
        aN.addNode(new PointDecimal(new BigDecimal("3"), new BigDecimal("3")));
        aN.addNode(new PointDecimal(new BigDecimal("2"), new BigDecimal("3")));
        aN.addNode(new PointDecimal(new BigDecimal("4"), new BigDecimal("5")));
        aN.addNode(new PointDecimal(new BigDecimal("3"), new BigDecimal("2")));
        aN.addNode(new PointDecimal(new BigDecimal("4"), new BigDecimal("4")));
        aN.addNode(new PointDecimal(new BigDecimal("5"), new BigDecimal("5")));
        aN.addNode(new PointDecimal(new BigDecimal("1"), new BigDecimal("3")));

        Algorithm.calculateStackedPolytope1(aN);
        showPolytope(Algorithm.showPolytopeInt());
    }

    public static void showPolytope(StackedPolytope sP) {
        Files.iteratePolytope(sP, true);

        Files.polytopeToFile(sP, OUTPUTPATH);

        ArrayList<ArrayList<Double>> vertices = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Integer>> faceIndices = new ArrayList<ArrayList<Integer>>();
        Transformation.iteratePolytope(sP, vertices, faceIndices);

        double[][] fixedList = Transformation.fixZCoordinates(Transformation.arrayListToArray2D(vertices));
        
//        SceneGraphComponent geometryNode = new SceneGraphComponent("geometry");
//        geometryNode.setGeometry(ifsf.getIndexedFaceSet());
//        Viewer v = JRViewer.display(geometryNode);
//        CameraUtility.encompass(v);
        ViewerFrame vf = new ViewerFrame(fixedList, 
                Transformation.arrayListToArrayInt2D(faceIndices), sP.smallerPolytopes()[0].getPoints()[0]);
    }
}
