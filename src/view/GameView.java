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
	private BufferedImage wall, corridor, room;
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
		room = loadImage("pic/room.png");
		corridor = loadImage("pic/corridor.png");
		wall = loadImage("pic/wall.png");
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
					//					g2D.setColor(Color.yellow);
					g2D.drawImage(corridor, x+5, y+5, 25, 25, null);
					//					g2D.fill(new Rectangle2D.Double(x + 5, y + 5, size-2, size-2));
				} else if (board[i][j] == '.') {
					// room
					//					g2D.setColor(Color.gray);
					g2D.drawImage(room, x+5, y+5, 25, 25, null);
					//					g2D.fill(new Rectangle2D.Double(x + 5, y + 5, size-2, size-2));
				} else {
					// wall
					//					g2D.setColor(Color.white);
					g2D.drawImage(wall, x+5, y+5, 25, 25, null);
					//					g2D.fill(new Rectangle2D.Double(x + 5, y + 5, size-2, size-2));
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
		g2D.setColor(Color.red);
		g2D.fill(new Rectangle2D.Double(x + 5, y + 5, size-2, size-2));

		// paint monster position
		Site monsterSite = model.getMonsterSite();
		int monsterX = monsterSite.getX();
		int monsterY = monsterSite.getY();
		System.out.println("monster: " + monsterX + "," + monsterY);
		x = monsterY * size + pad;
		y = monsterX * size + pad;
		g2D.setColor(Color.green);
		g2D.fill(new Rectangle2D.Double(x + 5, y + 5, size-2, size-2));

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		repaint();
	}
}
