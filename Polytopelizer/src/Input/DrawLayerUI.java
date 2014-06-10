package Input;

import interfaces.ApollonianNetwork;
import interfaces.Face;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
//import java.awt.geom.Line2D;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;

@SuppressWarnings("serial")
public class DrawLayerUI extends LayerUI<JComponent> {

    @Override
    public void paint(Graphics g, JComponent c) {
        // draws the components
        super.paint(g, c);
        // creates an new graphic in the drawing layer
//        System.out.println("ich werde aufgerufen.");
        
        Graphics2D g2 = (Graphics2D) g.create();

        // paints the network into the graphic
        // TODO add Draw list
        paintApollonianNetwork(g2, InputFrame.aN);
        // frees the resources after drawing
        g2.dispose();
    }

    /*
     * TODO fills the drawing layer with the current apollnetwork
     */
    private void paintApollonianNetwork(Graphics2D g, ApollonianNetwork aN) {
        LinkedList<Face> smallestfaces = new LinkedList<Face>();
        Face faces = aN.getFaces();
        // helpfunction to access the outterface
        smallestfaces.add(faces);
        if (!faces.isSmallestFace())
            getSmallestFaces(faces, smallestfaces);
        for (int i = 0; i < smallestfaces.size(); i++) {
            paintFace(g, smallestfaces.get(i));
        }      
    }
    
//    draws the edges of the face on the drawing layer
    private void paintFace(Graphics2D g, Face f) {
        for (int i = 0; i < f.getPoints().length; i++) {
            g.draw(new Line2D.Double(f.getPoints()[i].x().doubleValue(),
                    f.getPoints()[i].y().doubleValue(), f.getPoints()[(i + 1) % 3].x().doubleValue(), f
                            .getPoints()[(i + 1) % 3].y().doubleValue()));        
        }
    }

    /*
     * TODO error in creating a list with just inner faces
     */
    synchronized private void getSmallestFaces(Face f,
            LinkedList<Face> smallestfaces) {
        if (!f.isSmallestFace()) {
            for (int i = 0; i < f.smallerFaces().length; i++) {
                getSmallestFaces(f.smallerFaces()[i], smallestfaces);
            }
        } else {
            smallestfaces.add(f);
        }
    }

}
