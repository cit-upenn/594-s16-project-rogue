package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import model.Game;
import model.Monster;
import model.Site;
import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
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
	private int level;

	/**
	 * store the total map files
	 */
	public static ArrayList<String> mapFiles;

	/**
	 * GUI variables
	 */
	private JPanel top, left, right, bottom;
	private JTextArea text;
	private JButton back;
	private JLabel rogueImage;

	private static final int WIDTH = 720;
	private static final int HEIGHT = 580;
	private static final String hurtAudio = "sound/hurt.wav";
	private static final String pickUpSwordAudio = "sound/pickup_sword.wav";
	private static final String killMonsterAudio = "sound/kill_monster.wav";
	private static final String powerUpAudio = "sound/power_up.wav";
	private static final String levelUpAudio = "sound/level_up.wav";
	private static final String bgmAudio = "sound/bgm.wav";
	private static final String deadAudio = "sound/dead.wav";
	private static final String winAudio = "sound/win.wav";

	/**
	 * to control the keyboard action
	 */
	private Timer t;
	private TimerTask tt;

	/**
	 * audio play
	 */
	private AudioPlayer palyer;
	private AudioStream sound;
	private AudioData audioData;
	private Clip clip;

	/**
	 * constructor
	 * 
	 * @param switcher
	 *            window switcher
	 */
	public GameController(WindowSwitcher switcher) {
		this.switcher = switcher;
		this.t = new Timer();
		palyer = AudioPlayer.player;
		mapFiles = new ArrayList<String>();
		mapFiles.add("dungeon/1.txt");
		mapFiles.add("dungeon/2.txt");
		mapFiles.add("dungeon/3.txt");
		mapFiles.add("dungeon/4.txt");
		mapFiles.add("dungeon/5.txt");
		addBackgroundImage();
	}

	/**
	 * helper method to background image to the whole frame
	 */
	private void addBackgroundImage() {
		// adjust image size and add to view
		ImageIcon image = new ImageIcon("pic/background.jpg");
		Image img = image.getImage();
		Image newImg = img.getScaledInstance(WIDTH, HEIGHT, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(newImg);
		setContentPane(new JLabel(newImage));
	}

	@Override
	public void enable() {
		display("Game", WIDTH, HEIGHT);
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
		level = 1;
		game = new Game();
		game.setLevelMap(mapFile);
		view = new GameView(game);
		// add observer to game
		game.addObserver(view);
		try {
			playBGM(bgmAudio);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		updateText();
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
		right.setOpaque(false);
		add(right, BorderLayout.EAST);
		add(bottom, BorderLayout.SOUTH);

	}

	/**
	 * helper method to add sub-panels within the bottom panel
	 */
	private void addSubPanels() {
		// set right panel layout
		right.setLayout(new GridLayout(2, 1));
		text = new JTextArea();
		right.add(text);
		text.setForeground(Color.WHITE);
		text.setOpaque(false);
		updateText();
		addRogueImage();
		right.add(rogueImage);

		// set buttom panel layout
		bottom.setLayout(new GridLayout(1, 4));
		bottom.setBackground(BLACK);

		// add back button
		back = createButton("Back");
		setButton(back);
		bottom.add(back);

	}
	
	/**
	 * create button add background
	 * @param name
	 * @return
	 */
	private JButton createButton(String name) {
		ImageIcon image = new ImageIcon("pic/backbutton.jpg");
		Image img = image.getImage();
		Image newImg = img.getScaledInstance(720, 30, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(newImg);
		JButton b = new JButton(name, newImage);
		b.setVerticalTextPosition(SwingConstants.CENTER);
		b.setHorizontalTextPosition(SwingConstants.CENTER);
		return b;
	}

	/**
	 * helper method to rogue image to the lower right corner
	 */
	private void addRogueImage() {
		// adjust image size and add to view
		ImageIcon image = new ImageIcon("pic/undead_rogue.jpg");
		Image img = image.getImage();
		Image newImg = img.getScaledInstance(161, 257, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(newImg);
		rogueImage = new JLabel(newImage);
	}

	/**
	 * helper method to show rogue status
	 */
	private void updateText() {
		text.setText(
				"\nLEVEL " + level + "\t\n\n\n" + "HP :               " + game.getRogue().getHp() + "\t\n\n"
				+ "Sword :          " + game.getRogue().getNumberSword() + "\t");
	}

	@Override
	public void setButton(JButton button) {
		button.setForeground(Color.white);
		button.setOpaque(true);
		button.setBorderPainted(true);
		button.setBorder(WHITE_BORDER);
		button.setFont(new Font("Book Antiqua", Font.BOLD, 20));
	}

	@Override
	public void attachListenersToComponents() {

		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switcher.showMainMenu();
				clip.close();
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
			playAudio(powerUpAudio);
			game.getRogue().powerup();
		}
		if (game.isSwordSite()) {
			playAudio(pickUpSwordAudio);
			game.getRogue().addSword();
			game.setSwordSite();
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
				if (game.getRogue().hasSword()) {
					// remove monster
					playAudio(killMonsterAudio);
					game.removeMonster(game.caughtBy());
					game.getRogue().removeSword();
				} else {
					playAudio(hurtAudio);
					game.getRogue().takeDamage(game.caughtBy().getDamage());
				}

				// determine whether the rogue is dead
				if (game.getRogue().isDead()) {
					playAudio(deadAudio);
					clip.close();
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
				level = i + 1 + 1;
				playAudio(levelUpAudio);
				switchMap(mapFile);
			} else {
				// if the map is the final map, the rogue wins
				playAudio(winAudio);
				removeKeyListener(this);
				JOptionPane.showMessageDialog(getParent(), "You win!");
			}
		}
		updateText();
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

	/**
	 * helper method to play audio
	 * 
	 * @param audioFile
	 */
	private void playAudio(String audioFile) {

		AudioDataStream stream = null;
		try {
			sound = new AudioStream(new FileInputStream(audioFile));
			audioData = sound.getData();
			stream = new AudioDataStream(audioData);

		} catch (IOException e) {
			e.printStackTrace();
		}
		palyer.start(stream);
	}

	/**
	 * helper method to play audio
	 * 
	 * @param audioFile
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 * @throws InterruptedException
	 */
	private void playBGM(String bgmFile)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {

		AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(bgmFile));
		clip = AudioSystem.getClip();
		clip.open(inputStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

}
