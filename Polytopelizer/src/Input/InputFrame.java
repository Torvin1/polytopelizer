package Input;

/*#################### imports ##############################################*/

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

import Main.Polytopelizer;
import output.PointPanel;

// TODO grobal field for faces, points ?
// TODO Event listener (menubar, point drawing)
// TODO Input management

@SuppressWarnings("serial")
public class InputFrame extends JFrame {

    
    private InputPanel inputpanel;
    public static PointPanel pointpanel;
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
      //  add( new JLabel("Punkte"), BorderLayout.LINE_END);
        add(jlayer, BorderLayout.LINE_START );
        String[] columnNames = {"Nr", "x","y"};
        Double[][] vertices2 = new Double[Polytopelizer.aN.getPoints().size()][3];
        for(int i = 0; i < Polytopelizer.aN.getPoints().size(); i++){
            vertices2[i][0] = i + 1.0d ;
            vertices2[i][1] = Polytopelizer.aN.getPoints().get(i).x().doubleValue();
            vertices2[i][2] = Polytopelizer.aN.getPoints().get(i).y().doubleValue();
            }
        for (int i = 0 ; i < Polytopelizer.aN.getPoints().size() ; i++ ) {
            System.out.printf(" Punkt %d : X = %f | ;Y = %f \n",i,  Polytopelizer.aN.getPoints().get(i).x().doubleValue(),Polytopelizer.aN.getPoints().get(i).y().doubleValue());
        }
        System.out.println("aN und vertices");
        System.out.println(vertices2);
        pointpanel = new PointPanel(vertices2,columnNames);
        add(pointpanel);
        this.setSize(this.getWidth(), this.getHeight());
        setLocation(this.getX() + this.getWidth(), 8);
        setVisible(true);
        validate();
        // frame elements get merged
        pack();
        // frame is now visible
        setVisible(true);
    }
    
    public InputPanel getInputPanel(){
        return inputpanel;
    }

}
