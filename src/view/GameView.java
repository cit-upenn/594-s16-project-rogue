package view;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Game;
import model.Site;

/**
 * This class used to show view when playing game
 * 
 * @author woody
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
	private final static int pad = 50;
	private final static int size = 25;
	private final static BasicStroke stroke = new BasicStroke(2.0f);

	/**
	 * Constructor for View
	 * 
	 * @param model
	 */
	public GameView(Game model) {
		this.model = model;
		this.board = model.getDungeon().getBoard();
		System.out.println(Arrays.deepToString(board));
		room = loadImage("pic/room3.jpg");
		corridor = loadImage("pic/corridor3.jpg");
		wall = loadImage("pic/wall3.jpg");
		tunnel = loadImage("pic/tunnel.jpg");
		powerup = loadImage("pic/hp.jpg");
		monster = loadImage("pic/randommonster.jpg");
		rogue = loadImage("pic/rogue.jpg");

	}

	/**
	 * Open and return a fixed size image file
	 * @param filename
	 * @return
	 */
	private BufferedImage loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);
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
					g2D.drawImage(corridor, x+5, y+5, 25, 25, null);
				} else if (board[i][j] == '.') {
					// room
					g2D.drawImage(room, x+5, y+5, 25, 25, null);
				} else {
					// wall
					g2D.drawImage(wall, x+5, y+5, 25, 25, null);
				}
			}
		}

		// paint rogue position
		Site rogueSite = model.getRogueSite();
		int rogueX = rogueSite.getX();
		int rogueY = rogueSite.getY();
		System.out.println("rogue: " + rogueX + "," + rogueY);
		int x = rogueY * size + pad;
		int y = rogueX * size + pad;
		g2D.drawImage(rogue, x+5, y+5, 25, 25, null);

		// paint monster position
		Site monsterSite = model.getMonsterSite();
		int monsterX = monsterSite.getX();
		int monsterY = monsterSite.getY();
		System.out.println("monster: " + monsterX + "," + monsterY);
		x = monsterY * size + pad;
		y = monsterX * size + pad;
		g2D.drawImage(monster, x+5, y+5, 25, 25, null);

		// paint monster position
		Site tunnelSite = model.getTunnelSite();
		int tunnelX = tunnelSite.getX();
		int tunnelY = tunnelSite.getY();
		System.out.println("tunnel: " + tunnelX + "," + tunnelY);
		x = tunnelY * size + pad;
		y = tunnelX * size + pad;
		g2D.drawImage(tunnel, x+5, y+5, 25, 25, null);

		// paint monster position
		ArrayList<Site> powerUpSiteMap = model.getPowerUpSiteMap();
		for (Site p: powerUpSiteMap) {
			int powerupX = p.getX();
			int powerupY = p.getY();
			System.out.println("powerup: " + powerupX + "," + powerupY);
			x = powerupY * size + pad;
			y = powerupX * size + pad;
			g2D.drawImage(powerup, x+5, y+5, 25, 25, null);
		}
		

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		repaint();
	}
}
