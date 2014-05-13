package input;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


/*########################### imports #######################################*/

@SuppressWarnings("serial")
public class InputPanel extends JPanel {

/*########################### attributes ####################################*/

/*###########################constructor ####################################*/

    public InputPanel() {
        // creates a JFrame
        super();
        
//      propreties of the JFrame
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBackground(Color.WHITE);
//      default value
//      TODO adjustment, static size ?
        this.setPreferredSize(new java.awt.Dimension( 1024 ,576));
//      gives panel a border
//      TODO remove border if no other panel is set
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

}
