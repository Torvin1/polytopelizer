package input;

import interfaces.ApollonianNetwork;
import interfaces.Face;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import Geometry.Point2D;

import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;

import Datastructures.ApollNetwork;

@SuppressWarnings("serial")
public class DrawLayerUI extends LayerUI<JComponent> {
    
    
    @Override
    public void paint(Graphics g, JComponent c) {
//      draws the components
        super.paint(g, c);
//    creates an new graphic in the drawing layer
      Graphics2D g2 = (Graphics2D) g.create();
      
//      ApollonianNetwork dummy = new ApollNetwork(new Point2D(0+offsetX,500+offsetY), new Point2D(500+offsetX,500+offsetY), new Point2D(250+offsetX,67+offsetY));

//      InputFrame.aN.addNode(300+InputFrame.offsetX, 250+InputFrame.offsetY);  // will be inserted
//      InputFrame.aN.addNode(200+InputFrame.offsetX, 400+InputFrame.offsetY);    // will be inserted
//      InputFrame.aN.addNode( 30+InputFrame.offsetX, 400+InputFrame.offsetY);   // won't be inserted
//      InputFrame.aN.addNode(290+InputFrame.offsetX, 240+InputFrame.offsetY);
//      
      
//    paints the network into the graphic

      paintApollonianNetwork(g2, InputFrame.aN);
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
        g.draw(new Line2D.Double(f.getPoints()[i].x(),f.getPoints()[i].y(),f.getPoints()[(i+1)%3].x(), f.getPoints()[(i+1)%3].y()));
//        g.drawLine((int)(f.getPoints()[i].x()), (int)(f.getPoints()[i].y()), (int)(f.getPoints()[(i+1)%3].x()), ((int)(f.getPoints()[(i+1)%3].y())));
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

