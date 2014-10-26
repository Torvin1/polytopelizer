package schnyder;

public class Triangle {
    int[] points;
    Triangle parent;
    Triangle[] smallerTriangle;
    int nrOfTriangle;
    
    public Triangle(Triangle parent, int p1, int p2, int p3){
        int counter = 0;
        this.parent = parent;
        for(int i = 0; parent != null && i < parent.getPoints().length; i++){
            if(parent.getPoints()[i] == p1 || 
                    parent.getPoints()[i] == p2 ||
                    parent.getPoints()[i] == p3){
                counter++;
            }
        }
        if(counter == 2 || parent == null){
            this.smallerTriangle = null;
            points = new int[3];
            points[0] = p1;
            points[1] = p2;
            points[2] = p3;
            this.nrOfTriangle = 1;
        }else{
            this.nrOfTriangle = 0;
        }
    }
    
    public int[] getPoints(){
        return this.points;
    }
    
    public boolean splitTriangle(int p){
        if(this.smallerTriangle == null){
            this.smallerTriangle = new Triangle[3];
            this.smallerTriangle[0] = new Triangle(this, this.points[0], this.points[1], p);
            this.smallerTriangle[1] = new Triangle(this, p, this.points[1], this.points[2]);
            this.smallerTriangle[2] = new Triangle(this, this.points[0], p, this.points[2]);
            this.updateNrOfTriangles();
            return true;
        }else{
            return false;
        }
    }
    
    public Triangle[] getSmallerTriangle(){
        if(this.smallerTriangle == null){
            return null;            
        }else{
            return this.smallerTriangle;
        }
    }
    
    public int getNrOfTriangles(){
        return this.nrOfTriangle;
    }
    
    private void updateNrOfTriangles(){
        this.nrOfTriangle += 2;
        if(this.parent != null){
            this.parent.updateNrOfTriangles();
        }
    }
}
