import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel implements Observer {

	private Game model;
	private char[][] board;

	public View(Game model) {
		this.model = model;
		this.board = model.getDungeon().getBoard();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		int row = board.length;
		int column = board[0].length;
		g.fillRect(0, 0, row, column);
		// paint the by value

		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (board[i][j] == '+') {
					// corridor
				} else if (board[i][j] == '.') {
					// room
				} else {
					// wall
				}
			}
		}
		
		// paint rogue position
		Site rogueSite = model.getRogueSite();
		int rogueX = rogueSite.getX();
		int rogueY = rogueSite.getY();
		
		// paint monster position
		Site monsterSite = model.getMonsterSite();
		int monsterX = monsterSite.getX();
		int monsterY = monsterSite.getY();


	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
