package output;

import interfaces.*;
import Algorithm.*;
import Datastructures.*;
import Geometry.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import de.jreality.geometry.IndexedFaceSetFactory;
import de.jreality.plugin.JRViewer;
import de.jreality.plugin.JRViewer.ContentType;
import de.jreality.jogl.Viewer;
import de.jreality.math.MatrixBuilder;
import de.jreality.scene.Appearance;
import de.jreality.scene.Camera;
import de.jreality.scene.DirectionalLight;
import de.jreality.scene.IndexedFaceSet;
import de.jreality.scene.Light;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.SceneGraphPath;
import de.jreality.shader.CommonAttributes;
import de.jreality.tools.DraggingTool;
import de.jreality.tools.RotateTool;
import de.jreality.tools.ScaleTool;
import de.jreality.toolsystem.ToolSystem;
import de.jreality.util.RenderTrigger;

public class Test {
    static int z = 0;
    public static void main(String[] args) {
        ApollonianNetwork aN = new ApollNetwork(new PointDecimal(
                BigDecimal.ZERO, BigDecimal.ZERO), new PointDecimal(
                BigDecimal.TEN, BigDecimal.ZERO), new PointDecimal(
                BigDecimal.ZERO, BigDecimal.TEN));
        aN.addNode(new PointDecimal(new BigDecimal("2"), new BigDecimal("2")));
        StackedPolytope sP1 = DummyAlgorithm.calculateStackedPolytope1(aN);

        ArrayList<ArrayList<Double>> vertices = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Integer>> faceIndices = new ArrayList<ArrayList<Integer>>();
        Transformation.iteratePolytope(sP1, vertices, faceIndices);

        ArrayList<double[]> vertices1 = new ArrayList<double[]>();
        for (int i = 0; i < vertices.size(); i++) {
            vertices1.add(arrayListToArray(vertices.get(i)));
        }
        ArrayList<int[]> faceIndices1 = new ArrayList<int[]>();
        for (int i = 0; i < faceIndices.size(); i++) {
            faceIndices1.add(arrayListToArray(faceIndices.get(i), true));
        }
        
        double[][] vertices2 = vertices1.toArray(new double[vertices1.size()][]);
        int[][] faceIndices2 = faceIndices1.toArray(new int[faceIndices1.size()][]);
        
        IndexedFaceSetFactory ifsf = new IndexedFaceSetFactory();
        
//        double [][] vertices5  = new double[][] {
//                {0, 0, 0}, {2, 0, 0}, {2, 2, 0}, {0, 2, 0}, {1, 1, 1}
//              };
//              
//        int [][] faceIndices5 = new int [][] {
//                {0, 1, 2, 3}, {0, 1, 4}, {1, 2, 4}, {2, 3, 4}, {0, 3, 4} 
//              };
              
//        ifsf.setVertexCount( vertices5.length );
//        ifsf.setVertexCoordinates( vertices5 );
//        ifsf.setFaceCount( faceIndices5.length);
//        ifsf.setFaceIndices( faceIndices5 );
        
        ifsf.setVertexCount( vertices2.length );
        ifsf.setVertexCoordinates( vertices2 );
        ifsf.setFaceCount( faceIndices2.length );
        ifsf.setFaceIndices( faceIndices2 );
        
        ifsf.setGenerateEdgesFromFaces( true );
        ifsf.setGenerateFaceNormals( true );
     
        ifsf.update();
        
        new ViewerFrame(ifsf.getIndexedFaceSet());
    }

    private static String arrayToString(int[][] a) {
        String blakeks = "";
        for (int i = 0; i < a.length; i++) {
            String l = "";
            for (int j = 0; j < a[i].length; j++) {
                if (j == 0) {
                    l += "" + a[i][j];
                } else {
                    l += ", " + a[i][j];
                }
            }
            l = "{" + l;
            l += "}";
            if (i == 0) {
                blakeks += l;
            } else {
                blakeks += ", " + l;
            }
        }
        blakeks = "{" + blakeks;
        blakeks += "}";
        return blakeks;
    }

    private static String arrayToString(double[][] a) {
        String blakeks = "";
        for (int i = 0; i < a.length; i++) {
            String l = "";
            for (int j = 0; j < a[i].length; j++) {
                if (j == 0) {
                    l += "" + a[i][j];
                } else {
                    l += ", " + a[i][j];
                }
            }
            l = "{" + l;
            l += "}";
            if (i == 0) {
                blakeks += l;
            } else {
                blakeks += ", " + l;
            }
        }
        blakeks = "{" + blakeks;
        blakeks += "}";
        return blakeks;
    }

    private static double[] arrayListToArray(ArrayList<Double> al) {
        double[] ret = new double[al.size()];
        for (int i = 0; i < al.size(); i++) {
            ret[i] = al.get(i);
        }
        return ret;
    }

    private static int[] arrayListToArray(ArrayList<Integer> al, boolean a) {
        int[] ret = new int[al.size()];
        for (int i = 0; i < al.size(); i++) {
            ret[i] = al.get(i);
        }
        return ret;
    }
}
