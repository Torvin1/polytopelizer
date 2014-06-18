package Input;

import interfaces.ApollonianNetwork;
import interfaces.Face;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.math.BigDecimal;



import java.math.BigDecimal;
import java.util.LinkedList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Algorithm.Algorithm;
import Datastructures.ApollNetwork;
import Geometry.PointDecimal;

@SuppressWarnings("serial")
public class InputFrameMenuBar extends JMenuBar {

/*#################### attributes ########################################### */

/*###########################constructor ####################################*/
    public InputFrameMenuBar() {
        super();
        JMenu file = new JMenu("File");

        JMenuItem neu = new JMenuItem("new"); 
        JMenuItem save = new JMenuItem("save");
        JMenuItem load = new JMenuItem("load");
        file.add(neu);
        file.add(save);
        file.add(load);
        JMenuItem end = new JMenuItem("end");
        file.add(end);
        JMenuItem calculate = new JMenuItem("calculate");
        file.add(calculate);
        this.add(file);
        JMenu edit = new JMenu("Edit");
        this.add(edit);
        JMenuItem undo = new JMenuItem("undo");
        JMenuItem redo = new JMenuItem("redo");
        JMenuItem refresh = new JMenuItem("refresh");
        edit.add(undo);
        edit.add(redo);
        edit.add(refresh);
        
        
        neu.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // file saved ? / closed
                if (!InputFrame.saved) {
                    //file save here
                }
                // creates new aN
                InputFrame.aN = new ApollNetwork(new PointDecimal(new BigDecimal(0).add(InputFrame.offsetX), new BigDecimal(500).add(InputFrame.offsetY)),
                        new PointDecimal(new BigDecimal(500).add(InputFrame.offsetX), new BigDecimal(500).add(InputFrame.offsetY)),
                        new PointDecimal(new BigDecimal(250).add(InputFrame.offsetX), new BigDecimal(67).add(InputFrame.offsetY)));
                // new pointarräy for aN
                InputFrame.aN_points = new LinkedList<PointDecimal>();
                InputFrame.aN_points.add(new PointDecimal(new BigDecimal(0).add(InputFrame.offsetX), new BigDecimal(500).add(InputFrame.offsetY)));
                InputFrame.aN_points.add(new PointDecimal(new BigDecimal(500).add(InputFrame.offsetX), new BigDecimal(500).add(InputFrame.offsetY)));
                InputFrame.aN_points.add(new PointDecimal(new BigDecimal(250).add(InputFrame.offsetX), new BigDecimal(67).add(InputFrame.offsetY)));
                // reset action stack 
                InputFrame.actionstack.clear();
                // paint new aN
                InputFrame.inputpanel.revalidate();
                InputFrame.inputpanel.repaint();
            }
            
        });
        
        load.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // file open 
                // saved ?
                if (!InputFrame.saved) {
                    
                }
                // yes continue
                // no ask for override 
                // file browser ?
                // open file
                InputFrame.saved = true;
            }
        });

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // file open? 
                // yes ... save to file
                // no .... give path to file + name
                // TODO FileWrite
                InputFrame.saved = true;
            }
        });

        undo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InputFrame.saved = false;
                PointDecimal x = null;
                if (InputFrame.aN_points.size() > 3) {
                    try {
                        x = InputFrame.aN_points.removeLast();
                        //System.out.println(" Es wird der Punkt "+x+" aus aN_points entfernt.");
                    } catch (Exception e2) {
                        System.out.println("No points available to be removed.");
                        return;
                    }
                    InputFrame.actionstack.add(x);
                    if (!InputFrame.aN.removeNode(x)){
                        System.out.println("Node "+x+" wurde nicht entfernt.");
                    };
                    InputFrame.inputpanel.repaint();
                    InputFrame.inputpanel.revalidate();
                }else{
                    return;
                }
            }
        });

        redo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                InputFrame.saved = false;
                PointDecimal x = null;
                try {
                    x = InputFrame.actionstack.removeLast();
                } catch (Exception e2) {

                    System.out.println("No points available to be reinserted.");
                    return;
                }
                InputFrame.aN_points.add(x);
                InputFrame.aN.addNode(x);
                InputFrame.inputpanel.repaint();
                InputFrame.inputpanel.revalidate();
            }
        });

        end.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO save me ?
                if (!InputFrame.saved) {
                   // file speichern 
//                   InputFrame.aN_File
                }
                setVisible(false);
                System.exit(0);

            }
        });

        calculate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

//                output.Test.(InputFrame.aN);
                // TODO Output here
            }
        });

        refresh.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });

    }

    /* ############################# methods ################################## */

}
