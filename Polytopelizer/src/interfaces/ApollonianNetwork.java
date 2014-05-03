package interfaces;

public interface ApollonianNetwork {
	
    // Constructor returns the Complete Graph with 3 Nodes
    public boolean addNode(double x, double y);
    // adds a new Node and the three corresponding Edges to the Network and
    // returns true, iff (x,y) is within the outer face f0 (-> Paper)
    public boolean removeNode(double x, double y);
    // if (x,y) is not within the outer face f0, nothing happens and it returns
    // False. otherwise the node with degree 3, that is next to (x,y) is deleted
    // and True is returned
    public Face getFaces();
    // returns the Set of all (Sub-)Faces
    
}