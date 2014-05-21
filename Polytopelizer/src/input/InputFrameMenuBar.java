package input;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



@SuppressWarnings("serial")
public class InputFrameMenuBar extends JMenuBar{

/*#################### attributes ###########################################*/

    
/*------------Menues---------------------------------------------------------*/
    
/*###########################constructor ####################################*/
    public InputFrameMenuBar() {
        super ();
        JMenu file = new JMenu("File");
//        file.setMnemonic(KeyEvent.VK_A);
//        file.getAccessibleContext().setAccessibleDescription(
//                "The only menu in this program that has menu items");
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        JMenuItem beenden = new JMenuItem("Beenden");
        file.add(beenden);
        this.add(file);
        JMenu edit = new JMenu("Edit");
        this.add(edit);
        JMenuItem undo = new JMenuItem("Undo");
        edit.add(undo);
        
    }
/*############################# methods ####################################*/
}
