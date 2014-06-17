package Input;

import interfaces.StackedPolytope;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.math.BigDecimal;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Algorithm.Algorithm;
import Geometry.PointDecimal;
import Output.Test;

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
                // reset action stack 
                // create new aN
                // repaint
            }
        });
        
        load.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // file open 
                // saved ?
                // yes continue
                // no ask for override 
                // file browser ?
                // open file
            }
        });

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // file open? 
                // yes ... save to file
                // no .... give path to file + name
                // TODO FileWrite
            }
        });

        undo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
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
                setVisible(false);
                System.exit(0);

            }
        });

        calculate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                StackedPolytope sp = Algorithm.calculateStackedPolytope1(InputFrame.aN);
                // TODO Output here
                Test.showPolytope(sp);
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
