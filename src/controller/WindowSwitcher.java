package controller;

/**
 * The window switcher controls the whole game's window switch
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 *
 */
public class WindowSwitcher {

	/**
	 * represent 3 controllers of each kind of window in the game
	 */
	private MainMenu mainMenu; // main menu("lobby")
	private DifficultyMenu difficultyMenu;// the menu to select difficulty
	private GameController gameController;// the controller for the actual game

	/**
	 * Constructor
	 */
	public WindowSwitcher() {
		mainMenu = new MainMenu(this);
		difficultyMenu = new DifficultyMenu(this);
		gameController = new GameController(this);

	}

	/**
	 * show main menu
	 */
	public void showMainMenu() {
		difficultyMenu.disable();
		gameController.disable();
		mainMenu.enable();
	}

	/**
	 * show a menu with 3 difficulty levels to choose
	 * 
	 */
	public void showDifficultyMenu() {
		mainMenu.disable();
		gameController.disable();
		difficultyMenu.enable();
	}

	/**
	 * show the window with one specific map to play
	 * 
	 * @param mapFile
	 *            the map file to load
	 */
	public void showGame(String mapFile) {
		mainMenu.disable();
		difficultyMenu.disable();
		gameController.setGameMap(mapFile);
		gameController.enable();
	}

}
