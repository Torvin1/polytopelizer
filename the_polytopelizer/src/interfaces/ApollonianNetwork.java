package interfaces;
import java.util.LinkedList;

import Geometry.PointDecimal;

public interface ApollonianNetwork {
	
    // The Constructor returns the Complete Graph with 3 Nodes.
    
    public boolean addNode(PointDecimal p);
    // Adds a new Node and the three corresponding Edges to the Network and
    // returns true, iff (x,y) is within the outer face f0 (-> Paper).
    public boolean removeNode(PointDecimal p);
    // If (x,y) is not within the outer face f0, nothing happens and it returns
    // False. Otherwise the node with degree 3, that is next to (x,y) is deleted
    // and True is returned.
    public Face getFaces();
    // Returns the Set of all (Sub-)Faces.
    public LinkedList<PointDecimal> getPoints();
 // Returns the points in the Network. The list has to represent the order of adding the nodes.
    public int getNPoints();
    // Returns the Number of points in the Network
    
}