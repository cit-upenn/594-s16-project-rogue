package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Game;
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
	private BufferedImage rogue, monster;
	private BufferedImage wall, corridor, room, tunnel, powerup;
	private final static int pad = 0;
	private final static int size = 25;
	private final static BasicStroke stroke = new BasicStroke(2.0f);

	/**
	 * Constructor for View
	 * 
	 * @param model
	 */
	public GameView(Game model) {
		setOpaque(false);
		setGame(model);
		room = loadImage("pic/room3.jpg");
		corridor = loadImage("pic/corridor3.jpg");
		wall = loadImage("pic/wall3.jpg");
		tunnel = loadImage("pic/tunnel.jpg");
		powerup = loadImage("pic/hp.jpg");
		monster = loadImage("pic/randommonster.jpg");
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
		// System.out.println(Arrays.deepToString(board));
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		// get board size
		int row = board.length;
		int column = board[0].length;

		// paint the by value
		g2D.setStroke(stroke);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				int x = j * size + pad;
				int y = i * size + pad;
				if (board[i][j] == '+') {
					// corridor
					g2D.drawImage(corridor, x + 5, y + 5, 25, 25, null);
				} else if (board[i][j] == '.') {
					// room
					g2D.drawImage(room, x + 5, y + 5, 25, 25, null);
				} else {
					// wall
					g2D.drawImage(wall, x + 5, y + 5, 25, 25, null);
				}
			}
		}

		// paint rogue position
		paintImage(model.getRogueSite(), rogue, g2D);

		// paint monster position
		paintImage(model.getMonsterSite(), monster, g2D);

		// paint tunnel position
		paintImage(model.getTunnelSite(), tunnel, g2D);

		// paint power up position
		ArrayList<Site> powerUpSiteMap = model.getPowerUpSiteMap();
		for (Site powerUpSite : powerUpSiteMap) {
			paintImage(powerUpSite, powerup, g2D);
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
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
	 * @param characterSite
	 * @param character
	 * @param g2D
	 */
	private void paintImage(Site characterSite, Image character, Graphics2D g2D) {
		int X = characterSite.getX();
		int Y = characterSite.getY();
		// System.out.println("tunnel: " + X + "," + Y);
		int x = Y * size + pad;
		int y = X * size + pad;
		g2D.drawImage(character, x + 5, y + 5, 25, 25, null);
	}

}
