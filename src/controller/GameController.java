package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Game;
import model.Monster;
import model.Site;
import view.GameView;

/**
 * This class represents the controller of the actual game for player to play
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 *
 */
@SuppressWarnings("serial")
public class GameController extends Controller implements KeyListener {

	/**
	 * Game window switcher
	 */
	private WindowSwitcher switcher;

	/**
	 * model
	 */
	private Game game;

	/**
	 * view
	 */
	private GameView view;

	/**
	 * the current map
	 */
	private String mapFile;

	/**
	 * store the total map files
	 */
	public static ArrayList<String> mapFiles;

	/**
	 * GUI variables
	 */
	private JPanel top, left, right, bottom;
	private JButton back;

	/**
	 * to control the keyboard action
	 */
	private Timer t;
	private TimerTask tt;

	/**
	 * constructor
	 * 
	 * @param switcher
	 *            window switcher
	 */
	public GameController(WindowSwitcher switcher) {
		this.switcher = switcher;
		this.t = new Timer();
		mapFiles = new ArrayList<String>();
		mapFiles.add("dungeon/1.txt");
		mapFiles.add("dungeon/2.txt");
		mapFiles.add("dungeon/3.txt");
		mapFiles.add("dungeon/4.txt");
		mapFiles.add("dungeon/5.txt");
	}

	@Override
	public void enable() {
		display("Game", 800, 800);
		addKeyListener(this);
		setFocusable(true);
		setVisible(true);
	}

	/**
	 * load the map of the game
	 * 
	 * @param mapFile
	 *            the map file to load
	 */
	public void setGameMap(String mapFile) {
		this.mapFile = mapFile;
		game = new Game();
		game.setLevelMap(mapFile);
		view = new GameView(game);
		// add observer to game
		game.addObserver(view);
	}

	/**
	 * switch map file
	 * 
	 * @param mapFile
	 *            the map file to switch
	 */
	public void switchMap(String mapFile) {
		game.setLevelMap(mapFile);
		view.setGame(game);
	}

	@Override
	public void layOutComponents() {
		setLayout(new BorderLayout());
		addPanels();
		addSubPanels();
	}

	/**
	 * helper method to add panels
	 */
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

	/**
	 * do next step of the game after player next move
	 * 
	 * @param next
	 *            the next site the player want to move
	 */
	public void nextStep(Site next) {
		// set rogue's new position
		game.setRogueSite(next);
		if (game.removePowerUpSiteMap(next)) {
			// rogue get a power up
			game.getRogue().powerup();
		}
		if (game.isSwordSite()) {
			game.getRogue().setHasSword(true);
		}

		if (!game.isTunnelSite()) {
			// rogue do not enter the entrance of next map
			HashMap<Monster, Site> monsterSiteMap = game.getMonsterSiteMap();
			for (Monster m : monsterSiteMap.keySet()) {
				monsterSiteMap.put(m, m.move());
			}
			game.setMonsterSite(monsterSiteMap);// let monster move
			if (game.isMonsterSite()) {
				// if the monster catch the rogue, it will hurt the rogue
				if (game.getRogue().isHasSword()) {
					// remove monster
					game.removeMonster(game.caughtBy());	
				} else {
					game.getRogue().takeDamage(game.caughtBy().getDamage());
				}

				// determine whether the rogue is dead
				if (game.getRogue().isDead()) {
					removeKeyListener(this);
					JOptionPane.showMessageDialog(getParent(), "Game Over!");
				}
			}
		} else {
			// rogue enter the entrance of next map, change map
			int i = mapFiles.indexOf(mapFile);
			if (i < mapFiles.size() - 1) {
				// if the map is not the final map, "level up", set next map
				mapFile = mapFiles.get(i + 1);
				switchMap(mapFile);
			} else {
				// if the map is the final map, the rogue wins
				removeKeyListener(this);
				JOptionPane.showMessageDialog(getParent(), "You win!");
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		/* only one key press+release counts for a move of rogue */

		// if the key not released yet, do nothing
		if (tt != null) {
			return;
		}

		/*
		 * when one key pressed after release, start a timer task, rogue move
		 * one step
		 */
		tt = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
			}
		};
		t.scheduleAtFixedRate(tt, 0, 500);

		/*
		 * rogue moves one step
		 */
		Site current = game.getRogueSite(); // rogue current site
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			// move up
			Site next = new Site(current.getX() - 1, current.getY());
			if (game.getDungeon().isLegalMove(current, next)) {
				nextStep(next);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			// move down
			Site next = new Site(current.getX() + 1, current.getY());
			if (game.getDungeon().isLegalMove(current, next)) {
				nextStep(next);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// move right
			Site next = new Site(current.getX(), current.getY() + 1);
			if (game.getDungeon().isLegalMove(current, next)) {
				nextStep(next);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			// move left
			Site next = new Site(current.getX(), current.getY() - 1);
			if (game.getDungeon().isLegalMove(current, next)) {
				nextStep(next);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// when a key releases, cancel the timer task
		if (tt != null) {
			tt.cancel();
			tt = null;
		}
	}

}
