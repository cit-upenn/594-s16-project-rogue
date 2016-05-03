package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Game;
import model.Monster;
import model.Site;

/**
 * This class used to draw GameView, used for play
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 *
 */
@SuppressWarnings("serial")
public class GameView extends JPanel implements Observer {

	/**
	 * Instance variables for View
	 */
	private Game model;
	private char[][] board;

	private BufferedImage rogue, r_monster, f_monster, e_monster;
	private BufferedImage[] wallList, corridorList, roomList;
	private BufferedImage tunnel, powerup, sword;
	private final static int pad = 0;
	private final static int size = 25;
	private final static BasicStroke stroke = new BasicStroke(2.0f);
	private int level;

	/**
	 * Constructor for View
	 * 
	 * @param model
	 */
	public GameView(Game model) {
		setOpaque(false);
		setGame(model);
		loadImageList();
		tunnel = loadImage("pic/tunnel.jpg");
		sword = loadImage("pic/sword.jpg");
		powerup = loadImage("pic/hp.jpg");
		r_monster = loadImage("pic/levelonemonster.jpg");
		f_monster = loadImage("pic/leveltwomonster.jpg");
		e_monster = loadImage("pic/levelthreemonster.jpg");
		rogue = loadImage("pic/rogue.jpg");
	}

	/**
	 * Set up game
	 * 
	 * @param model
	 */
	public void setGame(Game model) {
		this.model = model;
		this.board = model.getDungeon().getBoard();
		this.level = model.getLevel();
		// System.out.println(Arrays.deepToString(board));
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		// get board size
		int row = board.length;
		int column = board[0].length;

		// paint char array by value
		g2D.setStroke(stroke);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				int x = j * size + pad;
				int y = i * size + pad;
				if (board[i][j] == '+') {
					// corridor
					g2D.drawImage(corridorList[level - 1], x + 5, y + 5, 25, 25, null);
				} else if (board[i][j] == '.') {
					// room
					g2D.drawImage(roomList[level - 1], x + 5, y + 5, 25, 25, null);
				} else {
					// wall
					g2D.drawImage(wallList[level - 1], x + 5, y + 5, 25, 25, null);
				}
			}
		}

		// paint tunnel position
		paintImage(model.getTunnelSite(), tunnel, g2D);

		// paint sword position
		paintImage(model.getSwordSite(), sword, g2D);

		// paint power up position
		for (Site powerUpSite : model.getPowerUpSiteMap()) {
			paintImage(powerUpSite, powerup, g2D);
		}

		// paint rogue position
		paintImage(model.getRogueSite(), rogue, g2D);

		// paint monster position
		HashMap<Monster, Site> monsterSiteMap = model.getMonsterSiteMap();
		for (Monster m : monsterSiteMap.keySet()) {
			String mName = m.getName();
			if (mName.equals("R")) {
				paintImage(monsterSiteMap.get(m), r_monster, g2D);
			} else if (mName.equals("F")) {
				paintImage(monsterSiteMap.get(m), f_monster, g2D);
			} else {
				paintImage(monsterSiteMap.get(m), e_monster, g2D);
			}
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

	/**
	 * Open and return a image file
	 * 
	 * @param filename
	 * @return
	 */
	private BufferedImage loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Load image list
	 */
	private void loadImageList() {
		roomList = new BufferedImage[5];
		for (int i = 0; i < roomList.length; i++) {
			roomList[i] = loadImage("pic/room" + (i + 1) + ".jpg");
		}

		corridorList = new BufferedImage[5];
		for (int i = 0; i < corridorList.length; i++) {
			corridorList[i] = loadImage("pic/corridor" + (i + 1) + ".jpg");
		}

		wallList = new BufferedImage[5];
		for (int i = 0; i < wallList.length; i++) {
			wallList[i] = loadImage("pic/wall" + (i + 1) + ".jpg");
		}

	}

	/**
	 * Paint image file
	 * 
	 * @param characterSite
	 * @param character
	 * @param g2D
	 */
	private void paintImage(Site characterSite, Image character, Graphics2D g2D) {
		if (characterSite != null) {
			int X = characterSite.getX();
			int Y = characterSite.getY();
			int x = Y * size + pad;
			int y = X * size + pad;
			g2D.drawImage(character, x + 5, y + 5, 25, 25, null);
		}
	}

}
