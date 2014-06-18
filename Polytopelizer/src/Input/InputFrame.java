package Input;

/*#################### imports ##############################################*/

import java.io.File;
import java.math.BigDecimal;
import java.util.LinkedList;

import Geometry.PointDecimal;
import interfaces.ApollonianNetwork;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

import Datastructures.ApollNetwork;

// TODO grobal field for faces, points ?
// TODO Event listener (menubar, point drawing)
// TODO Input management

/**
 * Core for the input
 * 
 */ class InputFrame {

    /*
     * ########################### attributes
     * ####################################
     */

    public static ApollonianNetwork aN;
    public static InputPanel inputpanel;
    public static LinkedList<PointDecimal> aN_points;
    public static LinkedList<PointDecimal> actionstack;
    public static File aN_File;
    public static boolean saved;
    public final static BigDecimal offsetX = new BigDecimal(250);
    public final static BigDecimal offsetY = new BigDecimal(50);

    /*
     * ###########################constructor
     * ####################################
     */

    /*
     * ############################# methods
     * #####################################
     */

    /**
     * Method to create the input GUI
     * 
     */
    public static void CreateAndShowGUI(String name) {
        // Creating the basic storage for Point
        aN = new ApollNetwork(new PointDecimal(new BigDecimal(0).add(offsetX), new BigDecimal(500).add(offsetY)),
                new PointDecimal(new BigDecimal(500).add(offsetX), new BigDecimal(500).add(offsetY)),
                new PointDecimal(new BigDecimal(250).add(offsetX), new BigDecimal(67).add(offsetY)));
        aN_points = new LinkedList<PointDecimal>();
        aN_points.add(new PointDecimal(new BigDecimal(0).add(offsetX), new BigDecimal(500).add(offsetY)));
        aN_points.add(new PointDecimal(new BigDecimal(500).add(offsetX), new BigDecimal(500).add(offsetY)));
        aN_points.add(new PointDecimal(new BigDecimal(250).add(offsetX), new BigDecimal(67).add(offsetY)));

        actionstack = new LinkedList<PointDecimal>();
        // Frame to work in
        JFrame inputframe = new JFrame(name);
        // Close Button
        inputframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set title
        // adding elements to the frame
        // top menubar
        inputframe.setJMenuBar(new InputFrameMenuBar());
        // drawing area gets added
        inputpanel = new InputPanel();

        // inputframe.add(inputpanel); // simple mouse eventpanel

        // adding a layer to draw on
        LayerUI<JComponent> layerUI = new DrawLayerUI();
        // combining interactive panel with a drawlayer
        JLayer<JComponent> jlayer = new JLayer<JComponent>(inputpanel, layerUI);
        inputframe.add(jlayer);

        // frame elemets get merged
        inputframe.pack();
        // frame is now visible
        inputframe.setVisible(true);
    }
    /*
     * #######################main to test it
     * ####################################
     */
    public static void main(String[] args) {
       
        //TODO argument handling
        
        
        // new thread for the frame
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            // start the frame set
            public void run() {

                CreateAndShowGUI("Polytopelizer");
            }
        });
    }

    /* ######################### method ######################################## */

}
