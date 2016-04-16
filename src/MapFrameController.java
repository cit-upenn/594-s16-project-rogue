import javax.swing.JFrame;

/**
 * This class contains a frame which let the user to choose one from 4 maps with
 * same difficulty. This class is a controller of the frame, it sets up the GUI
 * and handles all the controls (buttons, menu items, etc.)
 * 
 * @author zhiyuanli
 *
 */
public class MapFrameController {

	private JFrame frame;
	private Controller controller;
	private String difficulty;

	public MapFrameController(Controller controller) {
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

	/**
	 * @return the difficulty
	 */
	public String getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty
	 *            the difficulty to set
	 */
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

}
