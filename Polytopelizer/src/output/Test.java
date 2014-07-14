package output;

import interfaces.*;
import Algorithm.*;
import Datastructures.*;
import Geometry.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import de.jreality.geometry.IndexedFaceSetFactory;
import de.jreality.scene.SceneGraphComponent;

public class Test {
    public static void main(String[] args) {
        ApollonianNetwork aN = new ApollNetwork(new PointDecimal(
                BigDecimal.ZERO, BigDecimal.ZERO), new PointDecimal(
                BigDecimal.TEN, BigDecimal.ZERO), new PointDecimal(
                BigDecimal.ZERO, BigDecimal.TEN));
        aN.addNode(new PointDecimal(new BigDecimal("3"), new BigDecimal("3")));
        aN.addNode(new PointDecimal(new BigDecimal("2"), new BigDecimal("3")));
//        aN.addNode(new PointDecimal(new BigDecimal("3"), new BigDecimal("2")));
//        aN.addNode(new PointDecimal(new BigDecimal("4"), new BigDecimal("4")));
//        aN.addNode(new PointDecimal(new BigDecimal("5"), new BigDecimal("5")));
//        aN.addNode(new PointDecimal(new BigDecimal("1"), new BigDecimal("3")));
        StackedPolytope sP = Algorithm.calculateStackedPolytope1(aN);

        //  StackedPolytope sP = new StackedPolytopeImpl(
        //          new PointInteger(new BigInteger("0"), new BigInteger("0"), new BigInteger("0")), 
        //          new PointInteger(new BigInteger("2"), new BigInteger("0"), new BigInteger("0")), 
        //          new PointInteger(new BigInteger("1"), new BigInteger("4"), new BigInteger("0")));
        //  sP.divide(new PointInteger(new BigInteger("1"), new BigInteger("2"), new BigInteger("1")));
        
        showPolytope(sP);
    }
    
    public static void showPolytope(StackedPolytope sP){
        Transformation.iteratePolytope(sP, true);
        
        ArrayList<ArrayList<Double>> vertices = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Integer>> faceIndices = new ArrayList<ArrayList<Integer>>();
        Transformation.iteratePolytope(sP, vertices, faceIndices);
        
        ArrayList<double[]> vertices1 = new ArrayList<double[]>();
        for (int i = 0; i < vertices.size(); i++) {
            vertices1.add(Transformation.arrayListToArray(vertices.get(i)));
        }
        ArrayList<int[]> faceIndices1 = new ArrayList<int[]>();
        for (int i = 0; i < faceIndices.size(); i++) {
            faceIndices1.add(Transformation.arrayListToArray(faceIndices.get(i), true));
        }
       
        double[][] vertices2 = vertices1.toArray(new double[vertices1.size()][]);
        int[][] faceIndices2 = faceIndices1.toArray(new int[faceIndices1.size()][]);
        
        IndexedFaceSetFactory ifsf = new IndexedFaceSetFactory();
        
        ifsf.setVertexCount(vertices2.length);
        ifsf.setVertexCoordinates(vertices2);
        ifsf.setFaceCount(faceIndices2.length);
        ifsf.setFaceIndices(faceIndices2);
        
        ifsf.setGenerateEdgesFromFaces(true);
        ifsf.setGenerateFaceNormals(true);
       
        ifsf.update();
        
        SceneGraphComponent geometryNode = new SceneGraphComponent("geometry");
        geometryNode.setGeometry(ifsf.getIndexedFaceSet());
//        Viewer v = JRViewer.display(geometryNode);
//        CameraUtility.encompass(v);
        ViewerFrame vf = new ViewerFrame(ifsf.getIndexedFaceSet(), sP.smallerPolytopes()[0].getPoints()[0]);
    }
}
