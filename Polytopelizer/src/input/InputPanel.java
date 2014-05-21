package input;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import Geometry.Point2D;

/*
 * 
 */

/*########################### imports #######################################*/

@SuppressWarnings("serial")
public class InputPanel extends JPanel implements MouseListener {

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
        // TODO remove border if no other panel is set
        this.setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(this);
    }

    /*
     * ############################ methods
     * ######################################
     */

    public static ArrayList<Point2D> getStorage() {

        return storage;
    }

    /*
     * check p is a valid point
     */
    // private boolean isValid(Point2D p) {
    // // TODO add isInValidFace
    // if (isIn(p)) {
    // return false; // not a valid point
    // }
    // return true; // point is valid
    // }
    //
    //
    //
    // /*
    // * check if Point has already been in clicked
    // * (is already in storage)
    // * equals cannot be used because p is always a different object
    // */
    // private boolean isIn( Point2D p) {
    //
    // for (int i = 0;i < storage.size(); i++) {
    // // comparing the actual values
    // if (storage.get(i).x() == p.x() && storage.get(i).y() == p.y()) {
    // return true;
    // }
    // }
    // return false;
    // }

    /*
     * adds a point to the storage as long as point is not already in storage
     */

    private void storageFill(double x, double y) {
        // check if p is allowed in the appolonian network
        // if (isValid(p)) {
        InputFrame.aN.addNode(x, y);
        System.out.println(storage); // control print
        // }else {
        // // TODO case matching to specify what went wrong
        // System.err.printf("Not a valid point./n");
        // }

    }

    /*
     * event to add a new point to the network
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO insert point in aN
        storageFill(e.getX(), e.getY());
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

}
