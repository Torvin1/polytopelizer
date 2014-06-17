package Output;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Geometry.PointInteger;
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
import de.jreality.tools.ClickWheelCameraZoomTool;
import de.jreality.tools.DraggingTool;
import de.jreality.tools.RotateTool;
import de.jreality.tools.ScaleTool;
import de.jreality.toolsystem.ToolSystem;
import de.jreality.util.RenderTrigger;

@SuppressWarnings("serial")
public class ViewerFrame extends JFrame{
    
    public ViewerFrame(IndexedFaceSet ifs, PointInteger p){
        super();
        SceneGraphComponent rootNode = new SceneGraphComponent("root");
        SceneGraphComponent cameraNode = new SceneGraphComponent("camera");
        SceneGraphComponent geometryNode = new SceneGraphComponent("geometry");
        SceneGraphComponent lightNode = new SceneGraphComponent("light");
        
        rootNode.addChild(geometryNode);
        rootNode.addChild(cameraNode);
        cameraNode.addChild(lightNode);
        
        Light dl=new DirectionalLight();
        lightNode.setLight(dl);
        
        geometryNode.setGeometry(ifs);
        
        RotateTool rotateTool = new RotateTool();
        geometryNode.addTool(rotateTool);
        
        DraggingTool draggingTool = new DraggingTool();
        geometryNode.addTool(draggingTool);

        ClickWheelCameraZoomTool scaleTool = new ClickWheelCameraZoomTool();
        rootNode.addTool(scaleTool);
        
        //MatrixBuilder.euclidean().translate(50, 50, 50).assignTo(cameraNode);
        // TODO find good Ćameraplacment with maximum z Point
        MatrixBuilder.euclidean().translate(p.x().intValue(), p.y().intValue(), p.z().intValue()+35).assignTo(cameraNode);

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
        this.getContentPane().add((Component) viewer.getViewingComponent());
        this.validate();
        this.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent arg0) {
            System.exit(0);
          }
        });
        
        RenderTrigger rt = new RenderTrigger();
        rt.addSceneGraphComponent(rootNode);
        rt.addViewer(viewer);
    }
}
