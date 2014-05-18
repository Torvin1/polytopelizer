package Input;

import interfaces.ApollonianNetwork;
import interfaces.Face;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Geometry.Point2D;

import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;

import Datastructures.ApollNetwork;

@SuppressWarnings("serial")
public class DrawLayerUI extends LayerUI<JComponent> {
    
    private final static int offsetX = 250;
    private final static int offsetY = 50;
    @Override
    public void paint(Graphics g, JComponent c) {
//      draws the components
        super.paint(g, c);
//    creates an new graphic in the drawing layer
      Graphics2D g2 = (Graphics2D) g.create();
      
      ApollonianNetwork dummy = new ApollNetwork(new Point2D(0+offsetX,500+offsetY), new Point2D(500+offsetX,500+offsetY), new Point2D(250+offsetX,67+offsetY));

      dummy.addNode(300+offsetX, 250+offsetY);  // will be inserted
      dummy.addNode(200+offsetX,400+offsetY);    // will be inserted
      dummy.addNode(30+offsetX, 400+offsetY);   // won't be inserted
      
      
//      paints the network into the graphic

      paintApollonianNetwork(g2, dummy);
//      frees the resources after drawing
      g2.dispose();
    }
    /*
     * TODO fills the drawing layer with the current apollnetwork
     */
private void paintApollonianNetwork(Graphics2D g, ApollonianNetwork aN) {
    ArrayList<Face> smallestfaces = new ArrayList<Face>();
    Face faces = aN.getFaces();
//  helpfunction to access the outterface
    smallestfaces.add(faces);
    if (!faces.isSmallestFace()) 
        getSmallestFaces(faces, smallestfaces);
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
        }
    }else {
        smallestfaces.add(f);
    }
}

}

