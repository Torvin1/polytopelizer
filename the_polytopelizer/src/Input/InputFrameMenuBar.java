package Input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import output.*;
import Algorithm.Algorithm;
import Main.Files;
import Main.Polytopelizer;
import interfaces.*;

@SuppressWarnings("serial")
public class InputFrameMenuBar extends JMenuBar {

    protected static final String Visualizsation = null;

    public InputFrameMenuBar() {
        JMenu file = new JMenu("File");
        add(file);

        JMenuItem neu = new JMenuItem("New");
        JMenuItem save = new JMenuItem("Save As...");
        JMenuItem load = new JMenuItem("Open File...");
        JMenuItem calculateFile = new JMenuItem(
                "Calculate Polygon from File...");
        JMenuItem end = new JMenuItem("Exit");
        file.add(neu);
        file.add(save);
        file.add(load);
        file.add(calculateFile);
        file.add(end);

        neu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // file saved ? / closed
                if (!InputFrame.saved) {
                    // file save here
                }

                // creates new aN
                try {
                    Polytopelizer.aN = Files
                            .fileToApollonianNetwork(Polytopelizer.INITIALNETWORKPATH);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

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
                        "Apollonian Network (.aN)", "aN"));

                int state = fc.showOpenDialog(Polytopelizer.superFrame);

                if (state == JFileChooser.APPROVE_OPTION) {
                    try {
                        Polytopelizer.aN = Files.fileToApollonianNetwork(fc
                                .getSelectedFile().getPath());
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
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
                        "Apollonian Network (.aN)", "aN"));

                int state = fc.showSaveDialog(Polytopelizer.superFrame);

                if (state == JFileChooser.APPROVE_OPTION) {
                    Files.apollonianNetworkToFile(Polytopelizer.aN, fc
                            .getSelectedFile().getPath() + ".aN");
                    InputFrame.saved = true;
                }

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
                        "Apollonian Network (.aN)", "aN"));

                int state = fc.showOpenDialog(Polytopelizer.superFrame);

                if (state == JFileChooser.APPROVE_OPTION) {
                    ApollonianNetwork aN = null;
                    try {
                        aN = Files.fileToApollonianNetwork(fc.getSelectedFile()
                                .getPath());
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    Visualization.showPolytope(Algorithm
                            .calculateStackedPolytope1(aN));

                }

            }
        });

    }
}