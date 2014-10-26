package Input;

/*#################### imports ##############################################*/

import interfaces.StackedPolytope;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

import Algorithm.Algorithm;
import Geometry.PointDecimal;
import Main.Files;
import Main.Polytopelizer;
import output.Visualization;

// TODO grobal field for faces, points ?
// TODO Event listener (menubar, point drawing)
// TODO Input management

@SuppressWarnings("serial")
public class InputFrame extends JFrame {

    private InputPanel inputpanel;
    public static boolean saved;

    public InputFrame(String name) {
        super(name);

        // Close Button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(710, 710));
        setResizable(false);

        // set title
        // adding elements to the frame
        // top menubar
        setJMenuBar(new InputFrameMenuBar());

        // drawing area gets added
        inputpanel = new InputPanel();
        inputpanel.setPreferredSize(new Dimension(800, 600));

        // adding a layer to draw on
        LayerUI<JComponent> layerUI = new DrawLayerUI();
        // combining interactive panel with a drawlayer
        JLayer<JComponent> jlayer = new JLayer<JComponent>(inputpanel, layerUI);
        // setLayout(new BorderLayout(0, 10));
        // add( new JLabel("Punkte"), BorderLayout.LINE_END);

        add(jlayer, BorderLayout.PAGE_START);
        setButtons();
        this.setSize(this.getWidth(), this.getHeight());
        setLocation(this.getX() + this.getWidth(), 8);
        setVisible(true);
        validate();
        // frame elements get merged
        pack();
        // frame is now visible
        setVisible(true);
    }

    public InputPanel getInputPanel() {
        return inputpanel;
    }

    public void setButtons() {

        // final Icon icon1 = new ImageIcon(
        // JButton.class.getResource("/images/user-trash-full.png"));
        // final Icon icon2 = new ImageIcon(
        // JButton.class.getResource("/images/user-trash.png"));

        JPanel buttons = new JPanel();
        add(buttons);
        // buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setLayout(null);

        final JButton calc = new JButton("Calculate Polytope...");
        final JButton redo = new JButton("Redo");
        final JButton undo = new JButton("Undo");
        final JButton random = new JButton("Random network...");
        random.setBounds(10, 15, 180, 30);
        undo.setBounds(250, 15, 100, 30);
        redo.setBounds(350, 15, 100, 30);
        calc.setBounds(515, 15, 180, 30);
        buttons.add(random);
        buttons.add(redo);
        buttons.add(undo);
        buttons.add(calc);

        calc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StackedPolytope sp = Algorithm
                        .calculateStackedPolytope1(Polytopelizer.aN);
                Visualization.showPolytope(sp);
            }
        });

        redo.addActionListener(new ActionListener() {
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

        undo.addActionListener(new ActionListener() {
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

        random.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO save me ?
                if (!InputFrame.saved) {
                    // file speichern
                }

                String n = JOptionPane.showInputDialog(null, "How many nodes (at least 3)?");
                
                try {
                    int number = new Integer(n);
                    if (number>=3){
                        Polytopelizer.aN = Files.createRandomNetwork(number);
                        Polytopelizer.refresh();
                        InputFrame.saved = true;
                    }
                } catch (Exception exc) {
                }
            }
        });

    }
}
