package Input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import output.Test;
import Algorithm.Algorithm;
import Geometry.PointDecimal;
import Main.Polytopelizer;
import interfaces.*;

@SuppressWarnings("serial")
public class InputFrameMenuBar extends JMenuBar {

    public InputFrameMenuBar() {
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        this.add(file);
        this.add(edit);

        JMenuItem neu = new JMenuItem("New");
        JMenuItem save = new JMenuItem("Save As...");
        JMenuItem load = new JMenuItem("Open File...");
        JMenuItem random = new JMenuItem("Create Random Apollonian Network...");
        JMenuItem calculate = new JMenuItem("Calculate Polygon...");
        JMenuItem calculateFile = new JMenuItem("Calculate Polygon from File...");
        JMenuItem end = new JMenuItem("Exit");
        file.add(neu);
        file.add(save);
        file.add(load);
        file.add(random);
        file.add(calculate);
        file.add(calculateFile);
        file.add(end);

        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem redo = new JMenuItem("Redo");
        edit.add(undo);
        edit.add(redo);

        neu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // file saved ? / closed
                if (!InputFrame.saved) {
                    // file save here
                }

                // creates new aN
                Polytopelizer.aN = Files
                        .fileToApollonianNetwork(Polytopelizer.INITIALNETWORKPATH);

                Polytopelizer.refresh();
            }

        });

        load.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO file open
                // saved ?
                if (!InputFrame.saved) {

                }
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Specify a file to open");
                fc.setFileFilter(new FileNameExtensionFilter(
                        "Apollonian Network (.aN)", "*.aN"));
                
                int state = fc.showOpenDialog(Polytopelizer.superFrame);
                
                if (state == JFileChooser.APPROVE_OPTION) {
                    Polytopelizer.aN = Files.fileToApollonianNetwork(fc
                            .getSelectedFile().getPath());
                    Polytopelizer.refresh();
                    InputFrame.saved = true;
                }

            }
        });

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Specify a file to save");
                fc.setFileFilter(new FileNameExtensionFilter(
                        "Apollonian Network (.aN)", "*.aN"));

                int state = fc.showSaveDialog(Polytopelizer.superFrame);

                if (state == JFileChooser.APPROVE_OPTION) {
                    Files.apollonianNetworkToFile(Polytopelizer.aN, fc
                            .getSelectedFile().getPath() + ".aN");
                    InputFrame.saved = true;
                }

            }
        });
        
        random.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO save me ?
                if (!InputFrame.saved) {
                    // file speichern
                }
                Polytopelizer.aN = Files.createRandomNetwork(1000);
                Polytopelizer.refresh();
                InputFrame.saved = true;
            }
        });

        undo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PointDecimal x = null;
                if (Polytopelizer.aN.getNPoints() > 3) {
                    try {
                        x = Polytopelizer.aN.getPoints().getLast();
                    } catch (Exception e2) {
                        System.out
                                .println("No points available to be removed.");
                        return;
                    }
                    Polytopelizer.actionstack.add(x);
                    Polytopelizer.aN.removeNode(x);
                    InputFrame.saved = false;
                    Polytopelizer.superFrame.getInputPanel().repaint();
                    Polytopelizer.superFrame.getInputPanel().revalidate();
                }
            }
        });

        redo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PointDecimal x = null;
                try {
                    x = Polytopelizer.actionstack.removeLast();
                } catch (Exception e2) {

                    System.out.println("No points available to be reinserted.");
                    return;
                }
                Polytopelizer.aN.addNode(x);
                InputFrame.saved = false;
                Polytopelizer.superFrame.getInputPanel().repaint();
                Polytopelizer.superFrame.getInputPanel().revalidate();
            }
        });

        calculate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StackedPolytope sp = Algorithm
                        .calculateStackedPolytope1(Polytopelizer.aN);
                Test.showPolytope(sp);
            }
        });

        end.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO save me ?
                if (!InputFrame.saved) {
                    // file speichern
                }
                setVisible(false);
                System.exit(0);
            }
        });
        
        calculateFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Specify a file to open");
                fc.setFileFilter(new FileNameExtensionFilter(
                        "Apollonian Network (.aN)", "*.aN"));
                
                int state = fc.showOpenDialog(Polytopelizer.superFrame);
                
                if (state == JFileChooser.APPROVE_OPTION) {
                    ApollonianNetwork aN = Files.fileToApollonianNetwork(fc
                            .getSelectedFile().getPath());
                    Test.showPolytope(Algorithm.calculateStackedPolytope1(aN));
                    
                }

            }
        });

    }
}
