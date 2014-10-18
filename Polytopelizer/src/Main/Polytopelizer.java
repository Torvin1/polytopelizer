package Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

import Algorithm.Algorithm;
import Geometry.PointDecimal;
import Input.*;
import interfaces.*;

public class Polytopelizer {

    public static ApollonianNetwork aN;
    public static LinkedList<PointDecimal> actionstack;
    public static final String INITIALNETWORKPATH = "resources/initialNetwork.aN";
    public static InputFrame superFrame;

    public static void main(String[] args) {
        CLI c = new CLI(args);
        try {
            if(c.gui()){
                aN = Files.fileToApollonianNetwork(new BufferedReader(new InputStreamReader(
                        Polytopelizer.class.getClassLoader().getResourceAsStream(INITIALNETWORKPATH), "UTF-8")));
            }else{
                aN = Files.fileToApollonianNetwork(c.getInputPath());
            }
        } catch (Exception e) {
            System.err.println("ERROR - System could not find the file");
            return;
        }

        if (c.gui()) {
            actionstack = new LinkedList<PointDecimal>();

            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    superFrame = new InputFrame("Polytopelizer");
                }
            });
        } else {
            Files.polytopeToFile(Algorithm.calculateStackedPolytope1(aN),
                    c.getOutputPath());
        }

    }

    public static void refresh() {
        actionstack.clear();
        superFrame.getInputPanel().revalidate();
        superFrame.getInputPanel().repaint();
    }
}
