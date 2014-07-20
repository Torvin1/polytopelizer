package Main;

public class CLI {

    private String inputPath = "";
    private String outputPath = "";
    private boolean gui;

    public CLI(String[] arguments) {
        gui = arguments.length == 0;
        if (arguments.length == 2 && arguments[0].length() >= 1
                && arguments[1].length() >= 1) {
            inputPath = arguments[0];
            outputPath = arguments[1];
        } else if (arguments.length > 0) {
            System.out.println("Help for using the polytopelizer:");
            System.out.println("no arguments - starts the GUI");
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
