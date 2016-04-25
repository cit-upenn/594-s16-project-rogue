package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Game;
import model.Site;
import view.GameView;

/**
 * This class is the controller of the game for player to play
 * 
 * @author zhiyuanli
 *
 */
@SuppressWarnings("serial")
public class GameController extends Controller implements KeyListener {
	private WindowSwitcher switcher;
	private Game game;
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
		addKeyListener(this);
		setFocusable(true);
		setVisible(true);
		// add the map view
		view.repaint();

	}

	/**
	 * load the map of the game
	 */
	public void setGameMap(String Mapname) {
		game = new Game(Mapname);
		view = new GameView(game);
		game.addObserver(view);
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
	public void setButton(JButton button) {
		button.setForeground(Color.white);
		button.setBackground(INDIGO);
		button.setOpaque(true);
		button.setBorderPainted(true);
		button.setBorder(WHITE_BORDER);
		button.setFont(new Font("Arial", Font.PLAIN, 25));
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == 'w') {
			System.out.println("key pressed");
			Site current = game.getRogueSite();
			Site next = new Site(current.getX() - 1, current.getY());
			if (game.getDungeon().isLegalMove(current, next)) {
				game.nextStep(next);
			}
		} else if (e.getKeyChar() == 's') {
			Site current = game.getRogueSite();
			Site next = new Site(current.getX() + 1, current.getY());
			if (game.getDungeon().isLegalMove(current, next)) {
				game.nextStep(next);
			}
		} else if (e.getKeyChar() == 'd') {
			Site current = game.getRogueSite();
			Site next = new Site(current.getX(), current.getY() + 1);
			if (game.getDungeon().isLegalMove(current, next)) {
				game.nextStep(next);
			}
		} else if (e.getKeyChar() == 'a') {
			Site current = game.getRogueSite();
			Site next = new Site(current.getX(), current.getY() - 1);
			if (game.getDungeon().isLegalMove(current, next)) {
				game.nextStep(next);
			}
		}

		if (game.isEnd()) {
			removeKeyListener(this);
			JOptionPane.showMessageDialog(getParent(), "HaHa, ni ge sb");
		}
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
