/**
 * The game controller controls the whole game's frame switch
 * 
 * @author zhiyuanli
 *
 */
public class GameController {

	private GameFrame gameFrame;
	private MapFrame mapFrame;

	/**
	 * Constructor
	 */
	public GameController() {
		gameFrame = new GameFrame(this);
		mapFrame = new MapFrame(this);

		gameFrame.setVisible(true);
		mapFrame.setVisible(false);
	}

	/**
	 * only show game frame
	 */
	public void showGameFrame() {
		gameFrame.setVisible(true);
		mapFrame.setVisible(false);
	}

	/**
	 * show one map frame with specific difficulty level
	 * 
	 * @param level
	 */
	public void showMapFrame(String level) {
		gameFrame.setVisible(false);
		mapFrame.setLevel(level);
		mapFrame.setVisible(true);
	}

	public static void main(String[] args) {
		GameController gameController = new GameController();
	}

}
