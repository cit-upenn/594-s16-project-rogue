package model;

import java.io.*;
import java.util.*;

/**
 * Game class to control whole game, offer method for Game controller
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 */
public class Game extends Observable {

	/**
	 * static char variables used in game
	 */
	private static final char MONSTER = 'M'; // name of the monster
	private static final char ROGUE = '@'; // name of the rogue
	private static final char POWERUP = '*'; // name of the power up
	private static final char TUNNEL = '#'; // name of tunnel

	/**
	 * class instance variables that used in game
	 */
	private Dungeon dungeon; // the dungeon
	private Monster monster; // the monster
	private Rogue rogue; // the rogue

	/**
	 * keep track to record monster, rogue tunnel powerup site
	 */
	private Site monsterSite; // location of monster
	private Site rogueSite; // location of rogue
	private Site tunnelSite; // location of tunnel
	private Site swordSite; // location of sword
	private ArrayList<Site> powerUpSiteMap; // location of power up

	/**
	 * Constructor for game
	 */
	public Game() {
	}

	/**
	 * Used for set level map
	 * 
	 * @param filename
	 *            the file that contains the map
	 */
	public void setLevelMap(String filename) {
		powerUpSiteMap = new ArrayList<>();
		char[][] board = readMap(filename);
		init(board, filename);
		sendNotification();
	}

	/**
	 * gets the rogue of this game
	 * 
	 * @return the rogue of this game
	 */
	public Rogue getRogue() {
		return rogue;
	}

	/**
	 * gets the monster of this game
	 * 
	 * @return the monster of this game
	 */
	public Monster getMonster() {
		return monster;
	}

	/**
	 * gets the dungeon
	 * 
	 * @return the dungeon
	 */
	public Dungeon getDungeon() {
		return dungeon;
	}

	/**
	 * gets the position of monster
	 * 
	 * @return the position of monster
	 */
	public Site getMonsterSite() {
		return monsterSite;
	}

	/**
	 * sets the position of monster
	 * 
	 * @return the position of monster
	 */
	public void setMonsterSite(Site monsterSite) {
		this.monsterSite = monsterSite;
		sendNotification();
	}

	/**
	 * gets the position of rogue
	 * 
	 * @return the position of rogue
	 */
	public Site getRogueSite() {
		return rogueSite;
	}

	/**
	 * sets the position of rogue
	 * 
	 * @return the position of rogue
	 */
	public void setRogueSite(Site rogueSite) {
		this.rogueSite = rogueSite;
		sendNotification();
	}

	/**
	 * get rid of the power up location if it exists
	 * 
	 * @param s
	 */
	public boolean removePowerUpSiteMap(Site s) {
		return powerUpSiteMap.remove(s);
	}

	/**
	 * @return the powerUpSiteMap
	 */
	public ArrayList<Site> getPowerUpSiteMap() {
		return powerUpSiteMap;
	}

	/**
	 * @return the tunnelSite
	 */
	public Site getTunnelSite() {
		return tunnelSite;
	}

	/**
	 * check is current rogue site is tunnel site
	 * 
	 * @return true / false
	 */
	public boolean isTunnelSite() {
		return rogueSite.equals(tunnelSite);
	}

	/**
	 * check is current rogue site is monster site
	 * 
	 * @return true / false
	 */
	public boolean isMonsterSite() {
		return rogueSite.equals(monsterSite);
	}
	
	/**
	 * check is current rogue site is sword site
	 * @return
	 */
	public boolean isSwordSite() {
		return rogueSite.equals(swordSite);
	}

	/**
	 * @return the swordSite
	 */
	public Site getSwordSite() {
		return swordSite;
	}
	

	/**
	 * This is a helper method to read file, get map info if it success,
	 * otherwise system exit will be called.
	 */
	private char[][] readMap(String filename) {
		// create Scanner to read in file
		try (Scanner sc = new Scanner(new File(filename))) {
			// read in data
			int boardLength = Integer.parseInt(sc.nextLine());
			char[][] board = new char[boardLength][boardLength];
			for (int i = 0; i < boardLength; i++) {
				String s = sc.nextLine();
				for (int j = 0; j < boardLength; j++) {
					board[i][j] = s.charAt(2 * j);
					if (board[i][j] == MONSTER) { // check for monster's
													// location
						monsterSite = new Site(i, j);
						board[i][j] = '.';
						// System.out.println("monster@ " + i + "," + j);
					} else if (board[i][j] == ROGUE) { // check for rogue's
														// location
						rogueSite = new Site(i, j);
						board[i][j] = '.';
						// System.out.println("rogue@ " + i + "," + j);
					} else if (board[i][j] == POWERUP) { // check for power up's
															// location
						powerUpSiteMap.add(new Site(i, j));
						board[i][j] = '.';
					} else if (board[i][j] == TUNNEL) { // check for tunnel's
														// location
						tunnelSite = new Site(i, j);
						board[i][j] = '.';
					}
				}
			}
			return board;
		} catch (FileNotFoundException e) {
			System.exit(-1);
			return null;
		}
	}

	/**
	 * This is a helper method to initialize game variables.
	 * 
	 * @param board
	 * @param filename
	 */
	private void init(char[][] board, String filename) {
		// initialize dungeon, rogue, monster
		dungeon = new Dungeon(board);
		rogue = new Rogue(this);
		switch (filename) {
		// easy mode monster
		case "dungeon/1.txt":
		case "dungeon/2.txt":
			monster = new RandomMonster(this);
			break;
		// medium mode monster
		case "dungeon/3.txt":
			monster = new FourDirectionMonster(this);
			break;
		// hard mode monster
		case "dungeon/4.txt":
		case "dungeon/5.txt":
			monster = new EightDirectionMonster(this);
			break;
		}

	}

	/**
	 * This is a helper method is used to send notification to observers
	 */
	private void sendNotification() {
		setChanged();
		notifyObservers();
	}

}
