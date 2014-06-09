package Output;

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
        // ApollonianNetwork aN = new ApollNetwork();
        // aN.addNode(3, 3);
        // aN.addNode(1, 1);
        StackedPolytope sP1 = DummyAlgorithm.calculateStackedPolytope1(aN);

        ArrayList<ArrayList<Double>> vertices = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Integer>> faceIndices = new ArrayList<ArrayList<Integer>>();
        Transformation.iteratePolytope(sP1, vertices, faceIndices);
        
        iteratePolytope(sP1, vertices, faceIndices);

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

        double[][] vertices2 = vertices1
                .toArray(new double[vertices1.size()][]);
        int[][] faceIndices2 = faceIndices1
                .toArray(new int[faceIndices1.size()][]);

        System.out.println(vertices2.length);
        System.out.println(arrayToString(vertices2));
        System.out.println(arrayToString(faceIndices2));

        IndexedFaceSetFactory ifsf = new IndexedFaceSetFactory();

        double[][] vertices5 = new double[][] { { 0, 0, 0 }, { 2, 0, 0 },
                { 2, 2, 0 }, { 0, 2, 0 }, { 1, 1, 1 } };

        int[][] faceIndices5 = new int[][] { { 0, 1, 2, 3 }, { 0, 1, 4 },
                { 1, 2, 4 }, { 2, 3, 4 }, { 0, 3, 4 } };

        // ifsf.setVertexCount( vertices5.length );
        // ifsf.setVertexCoordinates( vertices5 );
        // ifsf.setFaceCount( faceIndices5.length);
        // ifsf.setFaceIndices( faceIndices5 );

        ifsf.setVertexCount(vertices2.length);
        ifsf.setVertexCoordinates(vertices2);
        ifsf.setFaceCount(faceIndices2.length);
        ifsf.setFaceIndices(faceIndices2);

        ifsf.setGenerateEdgesFromFaces(true);
        ifsf.setGenerateFaceNormals(true);

        ifsf.update();

        viewer(ifsf.getIndexedFaceSet());
    }

    private static void iteratePolytope(StackedPolytope sP,
            ArrayList<ArrayList<Double>> al, ArrayList<ArrayList<Integer>> al2) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < sP.getPoints().length; i++) {
            ArrayList<Double> a = new ArrayList<Double>();
            a.add(((Long) sP.getPoints()[i].x()).doubleValue());
            a.add(((Long) sP.getPoints()[i].y()).doubleValue());
            a.add(((Long) sP.getPoints()[i].z()).doubleValue());
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
                a.add(((Long) sP.getPoints()[i].x()).doubleValue());
                a.add(((Long) sP.getPoints()[i].y()).doubleValue());
                a.add(((Long) sP.getPoints()[i].z()).doubleValue());
                if (!al.contains(a)) {
                    al.add(a);
                }
                indices.add(al.indexOf(a));
            }
            al2.add(indices);
        }
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

    private static void viewer(IndexedFaceSet ifs) {
        SceneGraphComponent rootNode = new SceneGraphComponent("root");
        SceneGraphComponent cameraNode = new SceneGraphComponent("camera");
        SceneGraphComponent geometryNode = new SceneGraphComponent("geometry");
        SceneGraphComponent lightNode = new SceneGraphComponent("light");

        rootNode.addChild(geometryNode);
        rootNode.addChild(cameraNode);
        cameraNode.addChild(lightNode);

        Light dl = new DirectionalLight();
        lightNode.setLight(dl);

        geometryNode.setGeometry(ifs);

        RotateTool rotateTool = new RotateTool();
        geometryNode.addTool(rotateTool);

        DraggingTool draggingTool = new DraggingTool();
        geometryNode.addTool(draggingTool);

        ScaleTool scaleTool = new ScaleTool();
        geometryNode.addTool(scaleTool);

        MatrixBuilder.euclidean().translate(0, 0, 3).assignTo(cameraNode);

        Appearance rootApp = new Appearance();
        rootApp.setAttribute(CommonAttributes.BACKGROUND_COLOR, new Color(0f,
                .1f, .1f));
        rootApp.setAttribute(CommonAttributes.DIFFUSE_COLOR, new Color(1f, 0f,
                0f));
        rootNode.setAppearance(rootApp);

        Camera camera = new Camera();
        cameraNode.setCamera(camera);
        SceneGraphPath camPath = new SceneGraphPath(rootNode, cameraNode);
        camPath.push(camera);

        Viewer viewer = new Viewer();
        viewer.setSceneRoot(rootNode);
        viewer.setCameraPath(camPath);
        ToolSystem toolSystem = ToolSystem.toolSystemForViewer(viewer);
        toolSystem.initializeSceneTools();

        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(640, 480);
        frame.getContentPane().add((Component) viewer.getViewingComponent());
        frame.validate();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                System.exit(0);
            }
        });

        RenderTrigger rt = new RenderTrigger();
        rt.addSceneGraphComponent(rootNode);
        rt.addViewer(viewer);
    }
}
