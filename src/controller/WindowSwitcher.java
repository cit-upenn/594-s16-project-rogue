package controller;

/**
 * The switcher controls the whole game's window switch
 * 
 * @author zhiyuanli
 *
 */
public class WindowSwitcher {

	private MainMenu mainMenu;
	private DifficultyMenu difficultyMenu;
	private GameController gameController;

	/**
	 * Constructor
	 */
	public WindowSwitcher() {
		mainMenu = new MainMenu(this);
		difficultyMenu = new DifficultyMenu(this);
		gameController = new GameController(this);

	}

	/**
	 * only show main menu
	 */
	public void showMainMenu() {
		difficultyMenu.disable();
		gameController.disable();
		mainMenu.enable();
	}

	/**
	 * show one map menu with specific difficulty level
	 * 
	 * @param level
	 */
	public void showDifficultyMenu() {
		mainMenu.disable();
		gameController.disable();
		difficultyMenu.enable();
	}

	/**
	 * show one specific map for player to play
	 * 
	 * @param mapName
	 */
	public void showGame(String mapName) {
		mainMenu.disable();
		difficultyMenu.disable();
		gameController.setGameMap(mapName);
		gameController.enable();
	

	}

	public static void main(String[] args) {
		WindowSwitcher switcher = new WindowSwitcher();
		switcher.showMainMenu();
	}

}
