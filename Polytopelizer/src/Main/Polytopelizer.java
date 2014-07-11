package Main;

import java.util.LinkedList;

import Geometry.PointDecimal;
import Input.*;
import interfaces.*;

public class Polytopelizer {

    public static ApollonianNetwork aN;
    public static LinkedList<PointDecimal> actionstack;
    public static final String INITIALNETWORKPATH = "initialNetwork.aN";
    public static InputFrame superFrame;

    public static void main(String[] args) {

        aN = Files.fileToApollonianNetwork(INITIALNETWORKPATH);
        
        actionstack = new LinkedList<PointDecimal>();
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                superFrame = new InputFrame("Polytopelizer");
            }
        });

        // TODO argument handling
        // try {
        // temp = new File(System.getProperty("user.dir"),"temp.log~");
        // temp.createNewFile();
        // if (temp.isFile() || temp.isDirectory()) {
        //
        // // System.out.println("Log Datei wurde angelegt.");
        // }else {
        // // System.out.println("Log Datei nicht gefunden.");
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // temp.setReadable(true,true);
        // temp.setWritable(true,true);
        //
        // Scanner in_secure = new Scanner(temp);
        // Writer out_secure = new FileWriter(temp);
        // File file;
        // Scanner in = new Scanner(file);
        // Writer out = new FileWriter(file);

        // in_secure.nextLine();
        // new thread for the frame
        
    }
    
    public static void refresh(){
        actionstack.clear();
        superFrame.getInputPanel().revalidate();
        superFrame.getInputPanel().repaint();
    }
}
