package input;


/*#################### imports ##############################################*/

import java.util.LinkedList;

import Geometry.Point2D;
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
 */
public class InputFrame{

/*########################### attributes ####################################*/

    public static ApollonianNetwork aN;
    public static LinkedList<Point2D> aN_points;
    public final static int offsetX = 250;
    public final static int offsetY = 50;
    
/*###########################constructor ####################################*/

/*############################# methods #####################################*/	

/**
 * Method to create the input GUI
 * 
 */
    public static void CreateAndShowGUI(String name) 
{
    aN = new ApollNetwork(new Point2D(0+offsetX,500+offsetY), new Point2D(500+offsetX,500+offsetY), new Point2D(250+offsetX,67+offsetY));
    aN_points = new LinkedList<Point2D>();
    aN_points.add(new Point2D(0+offsetX,500+offsetY));
    aN_points.add(new Point2D(500+offsetX,500+offsetY));
    aN_points.add(new Point2D(250+offsetX,67+offsetY));
    
//  Frame to work in
    JFrame inputframe = new JFrame(name);
//  Close Button 
    inputframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
//  set title 


//  adding elements to the frame
//  top menubar
    inputframe.setJMenuBar(new InputFrameMenuBar());
//  drawing area gets added
    InputPanel inputpanel = new InputPanel();

    //inputframe.add(inputpanel); // simple mouse eventpanel
    
    //adding a layer to draw on
    LayerUI<JComponent> layerUI = new DrawLayerUI();
    //combining interactive panel with a drawlayer
    JLayer<JComponent> jlayer = new JLayer<JComponent>(inputpanel, layerUI);
    inputframe.add(jlayer);
    
//  frame elemets get merged 
    inputframe.pack();
//  frame is now visible
    inputframe.setVisible(true);
}

/*#######################main to test it ####################################*/

public static void main(String[] args) 
	{
    // new thread for the frame
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		// start the frame set
	    public void run() {
	        
			CreateAndShowGUI("Polytopelizer");
		}
	});
}
}