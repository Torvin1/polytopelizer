package Main;

import interfaces.StackedPolytope;

import java.io.FileWriter;
import java.math.BigInteger;

import Algorithm.Algorithm;

public class CLI {

    private String inputPath = "";
    private String outputPath = "";
    private boolean gui;

    public CLI(String[] arguments) {
        gui = arguments.length == 0;
        if (arguments.length == 3 && arguments[0].equals("random")) {
            try {
                if (new Integer(arguments[1]) < 3
                        || new Integer(arguments[2]) < 1)
                    throw new Exception();
                int n = new Integer(arguments[1]);
                int counts = new Integer(arguments[2]);
                StackedPolytope sP;
                FileWriter writerX = new FileWriter("coordinateX", false);
                FileWriter writerY = new FileWriter("coordinateY", false);
                FileWriter writerZ = new FileWriter("coordinateZ", false);
                for (int j = 3; j <= n; j++) {
                    BigInteger maxX = BigInteger.ZERO;
                    BigInteger maxY = BigInteger.ZERO;
                    BigInteger maxZ = BigInteger.ZERO;
                    for (int i = 0; i < counts; i++) {
                        sP = Algorithm.calculateStackedPolytope1(Files
                                .createRandomNetwork(j));
                        maxX = maxX.add(sP.getMaxX());
                        maxY = maxY.add(sP.getMaxY());
                        maxZ = maxZ.add(sP.getMaxZ());
                    }
                    writerX.write(""
                            + maxX.divide(new BigInteger(String.valueOf(counts)))+ "\n");
                    writerY.write(""
                            + maxY.divide(new BigInteger(String.valueOf(counts)))+ "\n");
                    writerZ.write(""
                            + maxZ.divide(new BigInteger(String.valueOf(counts)))+ "\n");
                    writerX.flush();
                    writerY.flush();
                    writerZ.flush();
                }
                writerX.close();
                writerY.close();
                writerZ.close();
                System.out.println("DONE");
            } catch (Exception exc) {
                System.out.println("Help for using the polytopelizer:");
                System.out.println("no arguments - starts the GUI");
                System.out
                        .println("<input file> <output file> - uses the given input file, calculates a stacked polytope and saves the result in the output file");
                System.out
                        .println("random <n> <m> - this will compute m random stacked polytopes with n vertices, calculate the maximum coordinates of the three axis and returns the average over all m polytopes");
                System.out.println("? - shows the help menu");
            } finally {
                System.exit(0);
            }
        } else if (arguments.length == 2 && arguments[0].length() >= 1
                && arguments[1].length() >= 1) {
            inputPath = arguments[0];
            outputPath = arguments[1];
        } else if (arguments.length > 0) {
            System.out.println("Help for using the polytopelizer:");
            System.out.println("no arguments - starts the GUI");
            System.out
                    .println("random <n> <m> - this will compute m random stacked polytopes with n vertices, calculate the maximum coordinates of the three axis and returns the average over all m polytopes");
            System.out
                    .println("<input file> <output file> - uses the given input file, calculates a stacked polytope and saves the result in the output file");
            System.out.println("? - shows the help menu");
            System.exit(0);
        }

    }

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public boolean gui() {
        return gui;
    }

}
