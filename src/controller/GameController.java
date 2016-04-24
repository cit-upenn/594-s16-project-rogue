package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Game;
import view.GameView;

/**
 * This class is the controller of the game for player to play
 * 
 * @author zhiyuanli
 *
 */
@SuppressWarnings("serial")
public class GameController extends Controller {
	private WindowSwitcher switcher;
	private Game model;
	private GameView view;

	/**
	 * GUI variables
	 */
	private JPanel top, left, right, bottom, center;

	/**
	 * constructor
	 * 
	 * @param switcher
	 */
	public GameController(WindowSwitcher switcher) {
		this.switcher = switcher;
	}

	@Override
	public void enable() {

		display("Game", 600, 400);
		setVisible(true);
		// add the map view
		view.repaint();

	}

	/**
	 * load the map of the game
	 */
	public void setGameMap(String Mapname) {
		model = new Game(Mapname);
		view = new GameView(model);
	}

	@Override
	public void layOutComponents() {
		setLayout(new BorderLayout());
		addPanels();
		addSubPanels();
	}

	private void addPanels() {
		top = new JPanel();
		left = new JPanel();
		right = new JPanel();
		bottom = new JPanel();

		// add them to the frame
		add(top, BorderLayout.NORTH);
		add(left, BorderLayout.WEST);
		add(view, BorderLayout.CENTER);
		add(right, BorderLayout.EAST);
		add(bottom, BorderLayout.SOUTH);

	}

	/**
	 * helper method to add sub-panels within the bottom panel
	 */
	private void addSubPanels() {


	}

	@Override
	public void attachListenersToComponents() {
		// TODO Auto-generated method stub

	}

}
