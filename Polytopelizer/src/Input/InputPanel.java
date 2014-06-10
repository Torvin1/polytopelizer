package Input;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Geometry.PointDecimal;

/*
 * 
 */

/*########################### imports #######################################*/

@SuppressWarnings("serial")
public class InputPanel extends JPanel implements MouseListener, MouseMotionListener , MouseWheelListener{

/*########################### attributes ####################################*/
/*###########################constructor####################################*/

    public InputPanel() {
        // creates a JFrame
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.WHITE);
        // default value
        // TODO adjustment, static size ?
        this.setPreferredSize(new java.awt.Dimension(1024, 576));
        // gives panel a border
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

/*############################ methods ######################################*/

    /*
     * event to add a new point to the network
     * clears the action stack so no old points can be insterted
     */

    @Override
    public void mouseClicked(MouseEvent e) {

        InputFrame.aN.addNode(new PointDecimal(new BigDecimal(e.getX()), new BigDecimal(e.getY())));
        InputFrame.aN_points.add(new PointDecimal(new BigDecimal(e.getX()), new BigDecimal(e.getY())));
        InputFrame.actionstack.clear();
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
