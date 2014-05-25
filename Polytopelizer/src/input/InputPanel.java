package Input;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Geometry.Point2D;

/*
 * 
 */

/*########################### imports #######################################*/

@SuppressWarnings("serial")
public class InputPanel extends JPanel implements MouseListener, MouseMotionListener , MouseWheelListener{

    /*
     * ########################### attributes
     * ####################################
     */
    // TODO replace storage with appollian network
    private static ArrayList<Point2D> storage;

    /*
     * ###########################constructor
     * ####################################
     */

    public InputPanel() {
        // creates a JFrame
        super();
        InputPanel.storage = new ArrayList<Point2D>();
        InputPanel.storage.add(new Point2D(7, 550));
        InputPanel.storage.add(new Point2D(1000, 7));
        // propreties of the JFrame
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.WHITE);
        // default value
        // TODO adjustment, static size ?
        this.setPreferredSize(new java.awt.Dimension(1024, 576));
        // gives panel a border
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseMotionListener(this);
    }

    /*
     * ############################ methods
     * ######################################
     */

    public static ArrayList<Point2D> getStorage() {

        return storage;
    }

    /*
     * event to add a new point to the network
     */

    @Override
    public void mouseClicked(MouseEvent e) {

        InputFrame.aN.addNode(e.getX(), e.getY());
        // System.out.printf("clicked @ %d , %d \n",e.getX(),e.getY());
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // TODO Auto-generated method stub
        
    }

}
