package interfaces;

public interface ApollonianNetwork {
	
    // The Constructor returns the Complete Graph with 3 Nodes.
    
    public boolean addNode(double x, double y);
    // Adds a new Node and the three corresponding Edges to the Network and
    // returns true, iff (x,y) is within the outer face f0 (-> Paper).
    public boolean removeNode(double x, double y);
    // If (x,y) is not within the outer face f0, nothing happens and it returns
    // False. Otherwise the node with degree 3, that is next to (x,y) is deleted
    // and True is returned.
    public Face getFaces();
    // Returns the Set of all (Sub-)Faces.
    
}