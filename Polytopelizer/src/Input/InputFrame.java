package Input;

/*#################### imports ##############################################*/

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

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

        // set title
        // adding elements to the frame
        // top menubar
        setJMenuBar(new InputFrameMenuBar());

        // drawing area gets added
        inputpanel = new InputPanel();
        

        // inputframe.add(inputpanel); // simple mouse eventpanel

        // adding a layer to draw on
        LayerUI<JComponent> layerUI = new DrawLayerUI();
        // combining interactive panel with a drawlayer
        JLayer<JComponent> jlayer = new JLayer<JComponent>(inputpanel, layerUI);
        setLayout(new BorderLayout(100,0));
        add( new JLabel("Punkte"), BorderLayout.LINE_END);
        add(jlayer, BorderLayout.LINE_START );

        // frame elemets get merged
        pack();
        // frame is now visible
        setVisible(true);
    }
    
    public InputPanel getInputPanel(){
        return inputpanel;
    }

}
