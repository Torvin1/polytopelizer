package Input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Algorithm.Algorithm;
import Geometry.Point2D;

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
		JMenuItem undo = new JMenuItem("Undo");
		JMenuItem redo = new JMenuItem("Redo");
		edit.add(undo);
		edit.add(redo);

		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO FileRead
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
				if (InputFrame.aN_points.size() > 3) {
					Point2D x = InputFrame.aN_points.removeLast();
					InputFrame.actionstack.add(x);
				} else {
					System.out.println("No points available to be removed.");
				}

			}
		});

		redo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (InputFrame.actionstack.size() > 0) {
					Point2D x = InputFrame.actionstack.removeLast();
					InputFrame.aN_points.add(x);
					repaint();
				} else {
					System.out.println("No points available to be reinserted.");
				}

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
	}

	/* ############################# methods ################################## */

}
