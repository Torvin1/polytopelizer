package Input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Random;

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

    public static File apollonianNetworkToFile(ApollonianNetwork aN, String path) {

        File file = new File(path);

        try {

            FileWriter writer = new FileWriter(file, false);
            LinkedList<PointDecimal> points = aN.getPoints();

            for (PointDecimal p : points)
                writer.write(p.x().toString() + " " + p.y().toString() + "\n");

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;

    }

    public static ApollonianNetwork createRandomNetwork(int size) {
        BigDecimal kathete = new BigDecimal(String.valueOf(size / Math.sqrt(3))).setScale(0,BigDecimal.ROUND_DOWN);
        PointDecimal p1 = new PointDecimal(kathete, new BigDecimal(0));
        PointDecimal p2 = new PointDecimal(new BigDecimal(0), new BigDecimal(
                size));
        PointDecimal p3 = new PointDecimal(kathete.multiply(new BigDecimal(2)),
                new BigDecimal(size));
        ApollonianNetwork aN = new ApollNetwork(p1, p2, p3);
        Random r = new Random();
        for (int i = 0; i < size-3; i++) {
            int y = r.nextInt(size);
            int x = (int) (((2*r.nextDouble()-1)*y+size)/Math.sqrt(3));
            aN.addNode(new PointDecimal(new BigDecimal(x), new BigDecimal(y)));
        }
        return aN;

    }
}
