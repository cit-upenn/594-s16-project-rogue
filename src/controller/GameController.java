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
import java.util.Timer;
import java.util.TimerTask;

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
	private String mapFile;
	public static ArrayList<String> mapFiles;

	/**
	 * GUI variables
	 */
	private JPanel top, left, right, bottom, center;
	private JButton back;
	private Timer t;
	private TimerTask tt;

	/**
	 * constructor
	 * 
	 * @param switcher
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

		display("Game", 1000, 1000);
		addKeyListener(this);
		setFocusable(true);
		setVisible(true);
		// add the map view
		// view.repaint();

	}

	/**
	 * load the map of the game
	 */
	public void setGameMap(String mapName) {
		this.mapFile = mapName;
		game = new Game();
		game.setMap(mapName);
		view = new GameView(game);
		game.addObserver(view);
	}

	public void switchMap(String mapName) {
		game.setMap(mapName);
		view.setGame(game);
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

	/**
	 * do next step
	 * 
	 * @param next
	 */
	public void nextStep(Site next) {
		game.setRogueSite(next);
		if (game.removePowerUpSiteMap(next)) {
			game.getRogue().powerup();
		}
		if (!game.isTunnel()) {
			game.setMonsterSite(game.getMonster().move());
			if (game.isCatchUp()) {
				game.getRogue().takeDamage(game.getMonster().getDamage());
				if (game.getRogue().isDead()) {
					System.out.println("game over");
					removeKeyListener(this);
					JOptionPane.showMessageDialog(getParent(), "Game Over!");
				}
			}
		} else {
			int i = mapFiles.indexOf(mapFile);
			if (i < mapFiles.size() - 1) {
				mapFile = mapFiles.get(i + 1);
				switchMap(mapFile);

			} else {
				removeKeyListener(this);
				JOptionPane.showMessageDialog(getParent(), "You win!");
			}

			System.out.println("level up");

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (tt != null) {
			return;
		}
		tt = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
			}
		};
		t.scheduleAtFixedRate(tt, 0, 500);

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("up key pressed");
			Site current = game.getRogueSite();
			Site next = new Site(current.getX() - 1, current.getY());
			System.out.println("up: " + next.getX() + "," + next.getY());
			if (game.getDungeon().isLegalMove(current, next)) {
				nextStep(next);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("down key pressed");
			Site current = game.getRogueSite();
			Site next = new Site(current.getX() + 1, current.getY());
			System.out.println("down: " + next.getX() + "," + next.getY());
			if (game.getDungeon().isLegalMove(current, next)) {
				nextStep(next);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("right key pressed");
			Site current = game.getRogueSite();
			Site next = new Site(current.getX(), current.getY() + 1);
			System.out.println("right: " + next.getX() + "," + next.getY());
			if (game.getDungeon().isLegalMove(current, next)) {
				nextStep(next);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("left key pressed");
			Site current = game.getRogueSite();
			Site next = new Site(current.getX(), current.getY() - 1);
			System.out.println("left: " + next.getX() + "," + next.getY());
			if (game.getDungeon().isLegalMove(current, next)) {
				nextStep(next);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (tt != null) {
			tt.cancel();
			tt = null;
		}
	}
	
}
