package Main;

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
import interfaces.StackedPolytope;

public class Files {

    public static File polytopeToFile(StackedPolytope sP, String path) {

        File file = new File(path);

        try {

            FileWriter writer = new FileWriter(file, false);

            iteratePolytope(sP, true, writer);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    private static void iteratePolytope(StackedPolytope sP, boolean first,
            FileWriter writer) throws IOException {
        if (first) {
            writer.write("" + sP.getPoints()[0].x() + " "
                    + sP.getPoints()[0].y() + " " + sP.getPoints()[0].z()
                    + "\n");
            writer.write("" + sP.getPoints()[1].x() + " "
                    + sP.getPoints()[1].y() + " " + sP.getPoints()[1].z()
                    + "\n");
            writer.write("" + sP.getPoints()[2].x() + " "
                    + sP.getPoints()[2].y() + " " + sP.getPoints()[2].z()
                    + "\n\n");
            writer.flush();
        }
        if (sP.isBoundary()) {
            writer.write("" + sP.getPoints()[0].x() + " "
                    + sP.getPoints()[0].y() + " " + sP.getPoints()[0].z()
                    + "\n");
            writer.write("" + sP.getPoints()[1].x() + " "
                    + sP.getPoints()[1].y() + " " + sP.getPoints()[1].z()
                    + "\n");
            writer.write("" + sP.getPoints()[2].x() + " "
                    + sP.getPoints()[2].y() + " " + sP.getPoints()[2].z()
                    + "\n\n");
            writer.flush();
        } else {
            for (int i = 0; i < sP.smallerPolytopes().length; i++) {
                iteratePolytope(sP.smallerPolytopes()[i], false, writer);
            }
        }
    }
    
    public static void iteratePolytope(StackedPolytope sP, boolean first) {
        if (first) {
            System.out.println("" + sP.getPoints()[0].x() + " "
                    + sP.getPoints()[0].y() + " " + sP.getPoints()[0].z());
            System.out.println("" + sP.getPoints()[1].x() + " "
                    + sP.getPoints()[1].y() + " " + sP.getPoints()[1].z());
            System.out.println("" + sP.getPoints()[2].x() + " "
                    + sP.getPoints()[2].y() + " " + sP.getPoints()[2].z());
            System.out.println();
        }
        if (sP.isBoundary()) {
            System.out.println("" + sP.getPoints()[0].x() + " "
                    + sP.getPoints()[0].y() + " " + sP.getPoints()[0].z());
            System.out.println("" + sP.getPoints()[1].x() + " "
                    + sP.getPoints()[1].y() + " " + sP.getPoints()[1].z());
            System.out.println("" + sP.getPoints()[2].x() + " "
                    + sP.getPoints()[2].y() + " " + sP.getPoints()[2].z());
            System.out.println();
        } else {
            for (int i = 0; i < sP.smallerPolytopes().length; i++) {
                iteratePolytope(sP.smallerPolytopes()[i], false);
            }
        }
    }

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
        BigDecimal kathete = new BigDecimal(String.valueOf(size / Math.sqrt(3)))
                .setScale(0, BigDecimal.ROUND_DOWN);
        PointDecimal p1 = new PointDecimal(kathete, new BigDecimal(0));
        PointDecimal p2 = new PointDecimal(new BigDecimal(0), new BigDecimal(
                size));
        PointDecimal p3 = new PointDecimal(kathete.multiply(new BigDecimal(2)),
                new BigDecimal(size));
        ApollonianNetwork aN = new ApollNetwork(p1, p2, p3);
        Random r = new Random();
        for (int i = 0; i < size - 3; i++) {
            int y = r.nextInt(size);
            int x = (int) (((2 * r.nextDouble() - 1) * y + size) / Math.sqrt(3));
            aN.addNode(new PointDecimal(new BigDecimal(x), new BigDecimal(y)));
        }
        return aN;

    }
}
