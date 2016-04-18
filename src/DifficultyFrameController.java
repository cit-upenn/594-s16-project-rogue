import javax.swing.JFrame;

/**
 * This class contains a frame which let the user to choose one from 3
 * difficulty levels. This class is a controller of the frame, it sets up the
 * GUI and handles all the controls (buttons, menu items, etc.)
 * 
 * @author zhiyuanli
 *
 */
public class DifficultyFrameController {

	private JFrame frame;
	private Controller controller;

	public DifficultyFrameController(Controller controller) {
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
