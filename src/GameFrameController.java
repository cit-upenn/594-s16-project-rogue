import javax.swing.JFrame;

/**
 * This class contains a frame let user to play th game. This class is a
 * controller of the frame, it sets up the GUI and handles all the controls
 * (buttons, menu items, etc.)
 * 
 * @author zhiyuanli
 *
 */
public class GameFrameController {

	private JFrame frame;
	private Controller controller;
	private Game game;

	public GameFrameController(Controller controller) {
		this.controller = controller;
		init();
	}

	/**
	 * set visible of this frame
	 * 
	 * @param visible
	 */
	public void setFrameVisible(boolean visible) {
		frame.setVisible(visible);
	}

	/**
	 * Displays the GUI.
	 */
	private void init() {
		layOutComponents();
		attachListenersToComponents();
	}

	/**
	 * Arranges the various components in the GUI.
	 */
	private void layOutComponents() {

	}

	/**
	 * Attaches listeners to the components, and schedules a Timer.
	 */
	private void attachListenersToComponents() {

	}

	
}
