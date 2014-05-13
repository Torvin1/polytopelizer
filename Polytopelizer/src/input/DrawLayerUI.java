package input;

import interfaces.Face;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Geometry.Point2D;

import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;
import javax.swing.text.StyleContext.SmallAttributeSet;

import Datastructures.ApollNetwork;

@SuppressWarnings("serial")
public class DrawLayerUI extends LayerUI<JComponent> {
    @Override
    public void paint(Graphics g, JComponent c) {
//      draws the components
        super.paint(g, c);
//    creates an new graphic in the drawing layer
      Graphics2D g2 = (Graphics2D) g.create();
      
      ApollNetwork dummy = new ApollNetwork(new Point2D(7,7), new Point2D(1000,250), new Point2D(7,550));
//      TODO doesn't seem to add the subfaces 
      dummy.addNode(60, 250);
      
      
//      paints the network into the graphic
      paintApollNetwork(g2, dummy);
//      frees the resources after drawing 
      g2.dispose();
    }
    /*
     * TODO fills the drawing layer with the current apollnetwork
     */
private void paintApollNetwork(Graphics2D g, ApollNetwork aN) {
    ArrayList<Face> smallestfaces = new ArrayList<Face>();
//  helpfunction to access the outterface
    System.out.printf("Before getting the faces : %s",smallestfaces);
    if (!aN.getFaces().isSmallestFace()) {
        smallestfaces.add(aN.getFaces());
        getSmallestFaces(aN.getFaces(), smallestfaces);
    }
    System.out.printf("After getting the faces : %s",smallestfaces);
    for (int i = 0 ; i < smallestfaces.size() ; i ++) {
        paintFace(g, smallestfaces.get(i));
    }
//   draws the subfaces of the outter face
//   TODO add recursive call !!
    
}
private void paintFace(Graphics2D g, Face f) {
    for (int i = 0; i<f.getPoints().length ; i++) {
        g.drawLine((int)(f.getPoints()[i].x()), (int)(f.getPoints()[i].y()), (int)(f.getPoints()[(i+1)%3].x()), ((int)(f.getPoints()[(i+1)%3].y())));
    }    
}
/*
 * TODO error in creating a list with just inner faces
 */
synchronized private void getSmallestFaces(Face f, ArrayList<Face> smallestfaces) {
    if (! f.isSmallestFace()) {
        for (int i = 0 ; i < f.smallerFaces().length ; i++) {
            getSmallestFaces(f.smallerFaces()[i],smallestfaces);
            System.out.printf("Face is not a smallest face : %s\n", f.toString());
        }
    }else {
        System.out.printf("Face is a smallest face : %s\n", f.toString());
        smallestfaces.add(f);
    }
}

}

