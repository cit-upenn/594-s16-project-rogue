/**
 * This is the main tester class.
 * It creates one Game View and simulates the GUI game play.
 * 
 * @author SHANG
 *
 */
public class Main {

	/**
	 * instance variables
	 * view: represents a lobby view
	 */
	public static GameView gameView;
	public static EasyView easyView;
	public static MediumView mediumView;
	public static HardView hardView;
	
	/**
	 * constructor
	 */
	public Main() {

		easyView = new EasyView();
		mediumView = new MediumView();
		hardView = new HardView();
		gameView = new GameView();

	}
	
	/**
	 * main method
	 */
	public static void main(String[] args) {
		
		Main main = new Main();

	}

}
