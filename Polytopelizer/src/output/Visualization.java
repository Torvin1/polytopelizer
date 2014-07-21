package output;

import interfaces.*;

public class Visualization {

    public static void showPolytope(StackedPolytope sP) {

        System.out.println(sP.getMaxX());
        System.out.println(sP.getMaxY());
        System.out.println(sP.getMaxZ());

        new ViewerFrame(sP);

    }
}