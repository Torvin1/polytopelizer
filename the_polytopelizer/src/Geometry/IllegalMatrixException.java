package Geometry;

public class IllegalMatrixException extends Exception {

    private static final long serialVersionUID = -5486237462531579963L;
    
    public IllegalMatrixException(){
        super("Matrix has illegal Dimensions.");
    }

}
