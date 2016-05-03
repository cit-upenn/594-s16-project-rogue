package controller;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * This abstract class inherits from JFrame, and represents the super class of
 * all controller of the game: MainMenu, DifficultyMenu, and GameController.
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 *
 */
@SuppressWarnings("serial")
public abstract class Controller extends JFrame {

	/**
	 * GUI constants
	 */
	public static final Border WHITE_BORDER = new LineBorder(Color.WHITE, 3);
	public static final Color BLACK = new Color(0, 0, 0);
	public static final Color MEDIUM_ORCHID = new Color(186, 85, 211);
	public static final Color CRIMSON = new Color(220, 20, 60);
	public static final Color SIENNA = new Color(160, 82, 45);
	public static final Color TEAL = new Color(0, 128, 128);
	public static final Color INDIGO = new Color(75, 0, 130);

	/**
	 * enable this frame
	 */
	public abstract void enable();

	/**
	 * disable this frame
	 */
	public void disable() {
		setVisible(false);
		getContentPane().removeAll();
	}

	/**
	 * method to display the frame
	 */
	public void display(String name, int weight, int height) {
		setTitle(name);
		layOutComponents();
		attachListenersToComponents();
		setPreferredSize(new Dimension(weight, height));
		setResizable(false);
		setLocation();
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * set the lay out
	 */
	public abstract void layOutComponents();

	/**
	 * set button attributes
	 * 
	 * @param button
	 *            the button to be set
	 */
	public abstract void setButton(JButton button);

	/**
	 * attach action listeners
	 */
	public abstract void attachListenersToComponents();

	/**
	 * set the location of the frame
	 */
	public void setLocation() {
		setLocation(100, 100);
	}

}
