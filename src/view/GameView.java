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
	private BufferedImage wall1, corridor1, room1;
	private BufferedImage wall2, corridor2, room2;
	private BufferedImage wall3, corridor3, room3;
	private BufferedImage wall4, corridor4, room4;
	private BufferedImage wall5, corridor5, room5;
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
		room1 = loadImage("pic/room1.jpg");
		room2 = loadImage("pic/room2.jpg");
		room3 = loadImage("pic/room3.jpg");
		room4 = loadImage("pic/room4.jpg");
		room5 = loadImage("pic/room5.jpg");
		corridor1 = loadImage("pic/corridor1.jpg");
		corridor2 = loadImage("pic/corridor2.jpg");
		corridor3 = loadImage("pic/corridor3.jpg");
		corridor4 = loadImage("pic/corridor4.jpg");
		corridor5 = loadImage("pic/corridor5.jpg");
		wall1 = loadImage("pic/wall1.jpg");
		wall2 = loadImage("pic/wall2.jpg");
		wall3 = loadImage("pic/wall3.jpg");
		wall4 = loadImage("pic/wall4.jpg");
		wall5 = loadImage("pic/wall5.jpg");
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
					g2D.drawImage(corridor3, x + 5, y + 5, 25, 25, null);
				} else if (board[i][j] == '.') {
					// room
					g2D.drawImage(room3, x + 5, y + 5, 25, 25, null);
				} else {
					// wall
					g2D.drawImage(wall3, x + 5, y + 5, 25, 25, null);
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
