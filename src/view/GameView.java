package view;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
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
	}

	@Override
	public void paint(Graphics g) {
		System.out.println("paint");
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
					g2D.setColor(Color.yellow);
					g2D.fill(new Rectangle2D.Double(x + 5, y + 5, size - 10, size - 10));
				} else if (board[i][j] == '.') {
					// room
					g2D.setColor(Color.gray);
					g2D.fill(new Rectangle2D.Double(x + 5, y + 5, size - 10, size - 10));
				} else {
					// wall
					g2D.setColor(Color.white);
					g2D.fill(new Rectangle2D.Double(x + 5, y + 5, size - 10, size - 10));
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
		g2D.fill(new Rectangle2D.Double(x + 5, y + 5, size - 10, size - 10));

		// paint monster position
		Site monsterSite = model.getMonsterSite();
		int monsterX = monsterSite.getX();
		int monsterY = monsterSite.getY();
		System.out.println("monster: " + monsterX + "," + monsterY);
		x = monsterY * size + pad;
		y = monsterX * size + pad;
		g2D.setColor(Color.green);
		g2D.fill(new Rectangle2D.Double(x + 5, y + 5, size - 10, size - 10));

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		repaint();
	}

	// /**
	// * For test
	// * @param args
	// */
	// public static void main(String[] args) {
	// Game model = new Game("dungeonA.txt");
	// GameView v = new GameView(model);
	// v.board = new char[10][10];
	// for (int i = 0; i < 10; i++) {
	// for (int j = 0; j < 10; j++) {
	// if (j < 5) {
	// v.board[i][j] = '+';
	// } else if (j < 8){
	// v.board[i][j] = '.';
	// } else {
	// v.board[i][j] = ' ';
	// }
	// }
	// }
	// JFrame p = new JFrame("helloc");
	// p.add(v);
	// p.setPreferredSize(new Dimension(600, 600));
	// p.setVisible(true);
	//
	// v.repaint();
	// }
}
