package output;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import Geometry.PointInteger;
import de.jreality.geometry.IndexedFaceSetFactory;
import de.jreality.jogl.Viewer;
import de.jreality.scene.Appearance;
import de.jreality.scene.Camera;
import de.jreality.scene.DirectionalLight;
import de.jreality.scene.IndexedFaceSet;
import de.jreality.scene.Light;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.SceneGraphPath;
import de.jreality.shader.CommonAttributes;
import de.jreality.tools.ClickWheelCameraZoomTool;
import de.jreality.tools.DraggingTool;
import de.jreality.tools.EncompassTool;
import de.jreality.tools.RotateTool;
import de.jreality.toolsystem.ToolSystem;
import de.jreality.util.RenderTrigger;

@SuppressWarnings("serial")
public class ViewerFrame extends JFrame{
    
    public ViewerFrame(double[][] vertices, int[][] faceIndices, PointInteger p){
        super();

        
        IndexedFaceSetFactory ifsf = new IndexedFaceSetFactory();

        ifsf.setVertexCount(vertices.length);
        ifsf.setVertexCoordinates(vertices);
        ifsf.setFaceCount(faceIndices.length);
        ifsf.setFaceIndices(faceIndices);

        ifsf.setGenerateEdgesFromFaces(true);
        ifsf.setGenerateFaceNormals(true);

        ifsf.update();
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SceneGraphComponent rootNode = new SceneGraphComponent("root");
        SceneGraphComponent cameraNode = new SceneGraphComponent("camera");
        SceneGraphComponent geometryNode = new SceneGraphComponent("geometry");
        SceneGraphComponent lightNode = new SceneGraphComponent("light");
        
        rootNode.addChild(geometryNode);
        rootNode.addChild(cameraNode);
        cameraNode.addChild(lightNode);
        
        Light dl=new DirectionalLight();
        lightNode.setLight(dl);
        
        geometryNode.setGeometry(ifsf.getIndexedFaceSet());
        
        RotateTool rotateTool = new RotateTool();
        geometryNode.addTool(rotateTool);
        
        DraggingTool draggingTool = new DraggingTool();
        geometryNode.addTool(draggingTool);

        ClickWheelCameraZoomTool scaleTool = new ClickWheelCameraZoomTool();
        rootNode.addTool(scaleTool);
        
        EncompassTool encompass = new EncompassTool();
        rootNode.addTool(encompass);
        
        Appearance rootApp= new Appearance();
        rootApp.setAttribute(CommonAttributes.BACKGROUND_COLOR, new Color(0f, .1f, .1f));
        rootApp.setAttribute(CommonAttributes.DIFFUSE_COLOR, new Color(1f, 0f, 0f));
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
        
        this.setVisible(true);
        this.setSize(640, 480);
        this.setLocation(0, -10);
        this.getContentPane().add((Component) viewer.getViewingComponent());
        this.validate();
        this.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent arg0) {
              setVisible(false);
          }
        });
        RenderTrigger rt = new RenderTrigger();
        rt.addSceneGraphComponent(rootNode);
        rt.addViewer(viewer);
        
        JFrame points = new JFrame();
        String[] columnNames = {"Nr", "x","y","z"};
        Double[][] vertices2 = new Double[vertices.length][vertices[0].length + 1];
        for(int i = 0; i < vertices.length; i++){
            vertices2[i][0] = i + 1.0d ;
            for(int j = 0; j < vertices[i].length; j++){
                vertices2[i][j+1] = vertices[i][j];
            }
        }
        points.add(new PointPanel(vertices2,columnNames));
        points.setSize(this.getWidth(), this.getHeight());
        points.setLocation(this.getX() + this.getWidth(), 8);
        points.setVisible(true);
        points.validate();
    }
}
