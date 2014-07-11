package Input;

/*#################### imports ##############################################*/

import java.io.File;
import java.util.LinkedList;

import Geometry.PointDecimal;
import interfaces.ApollonianNetwork;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

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
    public static LinkedList<PointDecimal> actionstack;
    public static File aN_File;
    public static File temp;
    public static boolean saved;

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
        
        aN = Files
                .fileToApollonianNetwork("E:\\Eigene Dateien\\Dropbox\\Informatik\\Softwareprojekt Polytope\\polytopelizer\\Polytopelizer\\src\\apollnetwork.an");

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
//        try {
//            temp = new File(System.getProperty("user.dir"),"temp.log~");
//            temp.createNewFile();
//            if (temp.isFile() || temp.isDirectory()) {
//                
////                System.out.println("Log Datei wurde angelegt.");
//            }else {
////                System.out.println("Log Datei nicht gefunden.");
//            }
//        } catch (Exception e) {
//             e.printStackTrace();
//        }
//        temp.setReadable(true,true);
//        temp.setWritable(true,true);
//        
//        Scanner in_secure = new Scanner(temp);
//        Writer out_secure = new FileWriter(temp);
//        File file;
//        Scanner in = new Scanner(file);
//        Writer out = new FileWriter(file);
        
//        in_secure.nextLine();
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
