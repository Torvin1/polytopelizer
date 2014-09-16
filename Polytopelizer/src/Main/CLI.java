package Main;

import interfaces.StackedPolytope;

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
                BigInteger maxX = BigInteger.ZERO;
                BigInteger maxY = BigInteger.ZERO;
                BigInteger maxZ = BigInteger.ZERO;
                for (int i = 0; i < counts; i++) {
                    sP = Algorithm.calculateStackedPolytope1(Files
                            .createRandomNetwork(n));
                    maxX = maxX.add(sP.getMaxX());
                    maxY = maxY.add(sP.getMaxY());
                    maxZ = maxZ.add(sP.getMaxZ());
                }
                System.out.println(maxX);
                System.out.println(maxY);
                System.out.println(maxZ);
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
        } else if (arguments.length == 3 && arguments[0].equals("rate")) {
        	try {
                if (new Integer(arguments[1]) < 3
                        || new Integer(arguments[2]) < 1)
                    throw new Exception();
                int n = new Integer(arguments[1]);
                int counts = new Integer(arguments[2]);
                StackedPolytope sP;
                BigInteger maxX = BigInteger.ZERO;
                BigInteger maxY = BigInteger.ZERO;
                BigInteger maxZ = BigInteger.ZERO;
                for (int i = 3; i<= n; i++){
                	for (int j = 0; j < counts; j++) {
                		sP = Algorithm.calculateStackedPolytope1(Files
                            .createRandomNetwork(i));
                		maxX = maxX.add(sP.getMaxX());
                		maxY = maxY.add(sP.getMaxY());
                		maxZ = maxZ.add(sP.getMaxZ());
                	}
                System.out.println(i+" "+maxX+" "+maxY+" "+maxZ );
                
                }
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
