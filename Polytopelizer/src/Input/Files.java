package Input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import Datastructures.ApollNetwork;
import Geometry.PointDecimal;

import interfaces.ApollonianNetwork;

public class Files {

    public static ApollonianNetwork fileToApollonianNetwork(String path) {

        ApollNetwork aN = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            // read the first three points
            String line = br.readLine();
            String[] point = line.split(" ");
            PointDecimal p1 = new PointDecimal(new BigDecimal(point[0]),
                    new BigDecimal(point[1]));
            line = br.readLine();
            point = line.split(" ");
            PointDecimal p2 = new PointDecimal(new BigDecimal(point[0]),
                    new BigDecimal(point[1]));
            line = br.readLine();
            point = line.split(" ");
            PointDecimal p3 = new PointDecimal(new BigDecimal(point[0]),
                    new BigDecimal(point[1]));

            // create a new Apollonian Network
            aN = new ApollNetwork(p1, p2, p3);

            // add all the other nodes
            while ((line = br.readLine()) != null) {
                point = line.split(" ");
                p1 = new PointDecimal(new BigDecimal(point[0]), new BigDecimal(
                        point[1]));
                aN.addNode(p1);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aN;
    }

}
