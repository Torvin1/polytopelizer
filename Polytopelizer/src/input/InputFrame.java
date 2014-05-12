package input;


/*#################### imports ##############################################*/

import java.util.jar.JarInputStream;

import javax.swing.*;

// TODO add menubar , save (load), undo last edit , 
// TODO create a drawing surface
// 


public class InputFrame{
/**
*provides the basic frame for general input 
*
*/

/*#################### attributes ###########################################*/


/*###########################constructor ####################################*/

/*############################# methods ####################################*/	

// TODO Drawing frame
// TODO Event listener
// TODO Input management
public static void CreateAndShowGUI(String name) 
{
    JFrame inputframe = new JFrame(name);
    inputframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel label = new JLabel("Hello World");
    inputframe.setJMenuBar(new InputFrameMenuBar());
    inputframe.getContentPane().add(label);
    inputframe.pack();
    inputframe.setVisible(true);
}

/*#######################main to test it ####################################*/

public static void main(String[] args) 
	{// show the frame 
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
	
			CreateAndShowGUI("Polytopelizer");
		}
	});
}
}