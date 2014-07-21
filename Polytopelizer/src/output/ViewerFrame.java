package output;

import interfaces.StackedPolytope;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import Main.Files;
import Main.Polytopelizer;

import de.jreality.geometry.IndexedFaceSetFactory;
import de.jreality.plugin.JRViewer;
import de.jreality.scene.Appearance;
import de.jreality.scene.Camera;
import de.jreality.scene.DirectionalLight;
import de.jreality.scene.Light;
import de.jreality.scene.SceneGraphComponent;
import de.jreality.scene.SceneGraphPath;
import de.jreality.scene.Viewer;
import de.jreality.shader.CommonAttributes;
import de.jreality.tools.ClickWheelCameraZoomTool;
import de.jreality.tools.DraggingTool;
import de.jreality.tools.EncompassTool;
import de.jreality.tools.RotateTool;
import de.jreality.util.CameraUtility;
import de.jreality.util.RenderTrigger;

@SuppressWarnings("serial")
public class ViewerFrame extends JFrame {

    Double[][] vertices2;
    Component jre;
    StackedPolytope sP;

    public ViewerFrame(StackedPolytope sP) {
        super("Polytopelizer - the stacked polytope");
        
        this.sP = sP;
        setPreferredSize(new Dimension(685, 685));
        setResizable(false);

        ArrayList<ArrayList<Double>> vertices = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Integer>> faceIndices = new ArrayList<ArrayList<Integer>>();
        Transformation.iteratePolytope(sP, vertices, faceIndices);

        double[][] verticess = Transformation.arrayListToArray2D(vertices);

        double[][] fixedList = Transformation.fixZCoordinates(Transformation
                .arrayListToArray2D(vertices));

        IndexedFaceSetFactory ifsf = new IndexedFaceSetFactory();

        Color[] vertexColor = new Color[fixedList.length];
        for (int i = 0; i < vertexColor.length; i++) {
            vertexColor[i] = Color.YELLOW;
        }

        ifsf.setVertexCount(fixedList.length);
        ifsf.setVertexCoordinates(fixedList);
        ifsf.setVertexColors(vertexColor);
        ifsf.setFaceCount(Transformation.arrayListToArrayInt2D(faceIndices).length);
        ifsf.setFaceIndices(Transformation.arrayListToArrayInt2D(faceIndices));

        ifsf.setGenerateEdgesFromFaces(true);
        ifsf.setGenerateFaceNormals(true);

        ifsf.update();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        SceneGraphComponent rootNode = new SceneGraphComponent("root");
        SceneGraphComponent cameraNode = new SceneGraphComponent("camera");
        SceneGraphComponent geometryNode = new SceneGraphComponent("geometry");
        SceneGraphComponent lightNode = new SceneGraphComponent("light");

        rootNode.addChild(geometryNode);
        rootNode.addChild(cameraNode);
        cameraNode.addChild(lightNode);

        Light dl = new DirectionalLight();
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

        Appearance rootApp = new Appearance();
        rootApp.setAttribute(CommonAttributes.BACKGROUND_COLOR, new Color(.0f,
                .2f, .2f));
        rootApp.setAttribute(CommonAttributes.DIFFUSE_COLOR, new Color(1f, 0f,
                0f));
        rootNode.setAppearance(rootApp);

        Camera camera = new Camera();
        cameraNode.setCamera(camera);
        SceneGraphPath camPath = new SceneGraphPath(rootNode, cameraNode);
        camPath.push(camera);

        JRViewer jr = new JRViewer();
        jr.setContent(rootNode);
        jr.startupLocal();
        Viewer viewer = jr.getViewer();
        CameraUtility.encompass(viewer);

        RenderTrigger rt = new RenderTrigger();
        rt.addSceneGraphComponent(rootNode);
        rt.addViewer(viewer);

        vertices2 = new Double[verticess.length][verticess[0].length + 1];
        for (int i = 0; i < verticess.length; i++) {
            vertices2[i][0] = i + 1.0d;
            for (int j = 0; j < verticess[i].length; j++) {
                vertices2[i][j + 1] = verticess[i][j];
            }
        }
        jre = (Component) viewer.getViewingComponent();
        jre.setPreferredSize(new Dimension(685, 600));
        add(jre, BorderLayout.PAGE_START);

        setButtons();

        pack();
        this.setVisible(true);
    }

    public void setButtons() {
        JPanel buttons = new JPanel();
        add(buttons);
        buttons.setLayout(null);

        final JButton points = new JButton("Show Points");
        final JButton export = new JButton("Export as JPEG ...");
        final JButton exportRaw = new JButton("Export as raw data ...");
        points.setBounds(32, 15, 180, 30);
        export.setBounds(252, 15, 180, 30);
        exportRaw.setBounds(472, 15, 180, 30);
        buttons.add(points);
        buttons.add(export);
        buttons.add(exportRaw);

        points.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PointFrame(vertices2);
            }
        });

        export.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Specify a file to export");
                fc.setFileFilter(new FileNameExtensionFilter(
                        "JPEG-Picture (.jpg)", "jpg"));

                int state = fc.showSaveDialog(Polytopelizer.superFrame);

                if (state == JFileChooser.APPROVE_OPTION) {
                    saveComponentAsJPEG("test.png");
                }

            }
        });
        
        exportRaw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Specify a file to export");
                fc.setFileFilter(new FileNameExtensionFilter(
                        "StackedPolytope (.pol)", "pol"));

                int state = fc.showSaveDialog(Polytopelizer.superFrame);

                if (state == JFileChooser.APPROVE_OPTION) {
                    Files.polytopeToFile(sP, fc
                            .getSelectedFile().getPath() + ".pol");
                }

            }
        });
    }

    public void saveComponentAsJPEG(String filename) {
        BufferedImage bi = new BufferedImage(this.getSize().width,
                this.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        jre.paint(g);
        g.dispose();
        try {
            ImageIO.write(bi, "png", new File(filename));
        } catch (Exception e) {
        }
    }

}