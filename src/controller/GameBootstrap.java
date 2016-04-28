package controller;

/**
 * This class represents the bootstrap of the Rogue Game
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 *
 */
public class GameBootstrap {

	public static void main(String[] args) {
		// construct a window switcher
		WindowSwitcher switcher = new WindowSwitcher();

		// show the main menu of the game
		switcher.showMainMenu();
	}

}
