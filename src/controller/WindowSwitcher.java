package controller;

/**
 * The switcher controls the whole game's window switch
 * 
 * @author zhiyuanli
 *
 */
public class WindowSwitcher {

	private MainMenu mainMenu;
	private MapMenu mapMenu;
	private GameController gameController;

	/**
	 * Constructor
	 */
	public WindowSwitcher() {
		mainMenu = new MainMenu(this);
		mapMenu = new MapMenu(this);
		gameController = new GameController(this);

	}

	/**
	 * only show main menu
	 */
	public void showMainMenu() {
		mapMenu.disable();
		gameController.disable();
		mainMenu.enable();
	}

	/**
	 * show one map menu with specific difficulty level
	 * 
	 * @param level
	 */
	public void showMapMenu(String level) {
		mainMenu.disable();
		gameController.disable();
		mapMenu.setLevel(level);
		mapMenu.enable();
	}

	/**
	 * show one specific map for player to play
	 * 
	 * @param mapName
	 */
	public void showGame(String mapName) {
		mainMenu.disable();
		mapMenu.disable();
		gameController.setGameMap(mapName);
		gameController.enable();
	

	}

	public static void main(String[] args) {
		WindowSwitcher switcher = new WindowSwitcher();
		switcher.showMainMenu();
	}

}
