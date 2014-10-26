package output;

import interfaces.*;

public class Visualization {

    public static void showPolytope(StackedPolytope sP) {
        new ViewerFrame(sP);
    }
}