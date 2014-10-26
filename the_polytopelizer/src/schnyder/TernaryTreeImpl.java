package schnyder;

public class TernaryTreeImpl {
    
    Triangle rootTriangle;
    
    Integer counter;
    
    public TernaryTreeImpl(){
        counter = 3;
        
        rootTriangle = new Triangle(null, 1, 2, 3);
    }
    
    public boolean addNode(Triangle t) {
        if(t.splitTriangle(counter+1)){
            this.counter++;
            return true;
        }
        return false;
    }

    public Triangle getRootTriangle() {
        return this.rootTriangle;
    }

//    public Triangle[] findTriangle(Integer n1, Integer n2) {
//        return this.findTriangleHelp(n1, n2, this.rootTriangle);
//    }
//    
//    private Triangle[] findTriangleHelp(Integer n1, Integer n2, Triangle t){
//        Triangle[] result = new Triangle[2];
//        result[0] = null;
//        result[1] = null;
//        int counter = 0;
//        if(t.getSmallerTriangle() != null){
//            for(int i = 0; i < t.getSmallerTriangle().length; i++){
//                    if(t.getSmallerTriangle()[i].getPoints()[0] == n1 && t.getSmallerTriangle()[i].getPoints()[1] == n2 ||
//                       t.getSmallerTriangle()[i].getPoints()[0] == n1 && t.getSmallerTriangle()[i].getPoints()[2] == n2 ||
//                       t.getSmallerTriangle()[i].getPoints()[1] == n1 && t.getSmallerTriangle()[i].getPoints()[0] == n2 ||
//                       t.getSmallerTriangle()[i].getPoints()[1] == n1 && t.getSmallerTriangle()[i].getPoints()[2] == n2 ||
//                       t.getSmallerTriangle()[i].getPoints()[2] == n1 && t.getSmallerTriangle()[i].getPoints()[0] == n2 ||
//                       t.getSmallerTriangle()[i].getPoints()[2] == n1 && t.getSmallerTriangle()[i].getPoints()[1] == n2 ){
//                        if(counter < 2){
//                            result[counter] = t.getSmallerTriangle()[i];
//                            counter++;
//                        }else
//                            return null;
//                    }
//            }
//            if(result[0] == null){
//                for(int i = 0; i < t.getSmallerTriangle().length; i++){
//                    Triangle[] resultRec = this.findTriangleHelp(n1, n2, t.getSmallerTriangle()[i]);
//                    if(resultRec == null){
//                        return null;
//                    }else if(resultRec[0] != null){
//                        return resultRec;
//                    }
//                }
//            }
//        }
//        return null;
//    }

}
