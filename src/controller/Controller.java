package controller;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * super class of all controller of the game frame: MainMenu, MapMenu, and
 * GameController
 * 
 * @author zhiyuanli
 *
 */
@SuppressWarnings("serial")
public abstract class Controller extends JFrame {

	/**
	 * enable the frame
	 * 
	 * 
	 */
	public abstract void enable();

	/**
	 * disable the frame
	 */
	public void disable() {
		setVisible(false);
		getContentPane().removeAll();
	}

	/**
	 * method to construct the frame
	 */
	public void display(String name, int weight, int height) {
		setTitle(name);
		layOutComponents();
		attachListenersToComponents();
		setPreferredSize(new Dimension(weight, height));
		setLocation();
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * set the lay out
	 */
	public abstract void layOutComponents();

	/**
	 * attach action listeners
	 */
	public abstract void attachListenersToComponents();

	/**
	 * set the location of the frame
	 */
	public void setLocation() {
//		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//		int x = (int) ((dimension.getWidth() / 2 - this.getWidth()) / 2);
//		int y = (int) ((dimension.getHeight() / 2 - this.getHeight()) / 2);
		setLocation(100, 100);
	}

}
