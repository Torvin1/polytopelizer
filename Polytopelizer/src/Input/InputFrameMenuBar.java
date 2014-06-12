package Input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.math.BigDecimal;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Algorithm.Algorithm;
import Geometry.PointDecimal;

@SuppressWarnings("serial")
public class InputFrameMenuBar extends JMenuBar {

	/*
	 * #################### attributes
	 * ###########################################
	 */

	/*------------Menues---------------------------------------------------------*/

	/*
	 * ###########################constructor
	 * ####################################
	 */
	public InputFrameMenuBar() {
		super();
		JMenu file = new JMenu("File");
		// file.setMnemonic(KeyEvent.VK_A);
		// file.getAccessibleContext().setAccessibleDescription(
		// "The only menu in this program that has menu items");

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

		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// file browser ?
			    // open file
			}
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
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
//						System.out.println(" Es wird der Punkt "+x+" aus aN_points entfernt.");
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

				Algorithm.calculateStackedPolytope2(InputFrame.aN);
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
