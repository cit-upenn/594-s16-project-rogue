package controller;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JButton back;
	
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
		// set buttom panel layout
		bottom.setLayout(new GridLayout(1, 4));
		bottom.setBackground(BLACK);

		// add 3 option buttons
		back = new JButton("Back");
		setButton(back);
		bottom.add(back);

	}

	@Override
	public void attachListenersToComponents() {
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showMainMenu();
			}

		});		

	}

}
