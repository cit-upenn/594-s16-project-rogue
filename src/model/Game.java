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
	private static final char R_MONSTER = 'R'; // name of the random monster
	private static final char F_MONSTER = 'F'; // name of the four direction
												// monster
	private static final char E_MONSTER = 'E'; // name of the eight direction
												// monster
	private static final char ROGUE = '@'; // name of the rogue
	private static final char POWERUP = '*'; // name of the power up
	private static final char TUNNEL = '#'; // name of tunnel
	private static final char SWORD = '$';

	/**
	 * class instance variables that used in game
	 */
	private Dungeon dungeon; // the dungeon
//	private ArrayList<Monster> monster; // the monster
	private Rogue rogue; // the rogue

	/**
	 * keep track to record monster, rogue tunnel powerup site
	 */
	private HashMap<String, Site> monsterNameSiteMap;
	private HashMap<Monster, Site> monsterSiteMap; // location of monster
	private Site rogueSite; // location of rogue
	private Site tunnelSite; // location of tunnel
	private ArrayList<Site> powerUpSiteMap; // location of power up
	private Site swordSite; // location of sword

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
		System.out.println(filename);
		powerUpSiteMap = new ArrayList<Site>();
		monsterNameSiteMap = new HashMap<String, Site>();
		monsterSiteMap = new HashMap<Monster, Site>();
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

//	/**
//	 * gets the monster of this game
//	 * 
//	 * @return the monster of this game
//	 */
//	public ArrayList<Monster> getMonster() {
//		return monster;
//	}

	/**
	 * gets the monster site info of this game
	 * 
	 * @return the monsterSite of this game
	 */
	public HashMap<Monster, Site> getMonsterSiteMap() {
		return monsterSiteMap;
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
	public Site getMonsterSite(String name) {
		for (Monster m : monsterSiteMap.keySet())
			if (m.getName().equals(name))
				return monsterSiteMap.get(m);
		return null;

	}

	/**
	 * sets the position of monster
	 * 
	 * @return the position of monster
	 */
	public void setMonsterSite(HashMap<Monster, Site> monsterSite) {
		this.monsterSiteMap = monsterSite;
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
		return monsterSiteMap.containsValue(rogueSite);
	}
	
	/**
	 * remove the monster
	 * @param m
	 */
	public void removeMonster(Monster m) {
		monsterSiteMap.remove(m);
	}
	
	/**
	 * return which monster caught the rogue
	 * @return
	 */
	public Monster caughtBy() {
		for (Monster m : monsterSiteMap.keySet()) {
			if (monsterSiteMap.get(m).equals(rogueSite)) {
				return m;
			}
		}
		return null;
	}

	/**
	 * check is current rogue site is sword site
	 * 
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
					if (board[i][j] == R_MONSTER) { // check for monster's
													// location
						monsterNameSiteMap.put("R", new Site(i, j));
						board[i][j] = '.';
						// System.out.println("monster@ " + i + "," + j);
					} else if (board[i][j] == F_MONSTER) { // check for
															// monster's
						// location
						monsterNameSiteMap.put("F", new Site(i, j));
						board[i][j] = '.';
					} else if (board[i][j] == E_MONSTER) { // check for
															// monster's
						// location
						monsterNameSiteMap.put("E", new Site(i, j));
						board[i][j] = '.';
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
					} else if (board[i][j] == SWORD) {
						swordSite = new Site(i, j);
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
		for (String s : monsterNameSiteMap.keySet()) {
			if (s.equals("R")) {
				monsterSiteMap.put(new RandomMonster(this, s), monsterNameSiteMap.get(s));
			}else if (s.equals("F")) {
				monsterSiteMap.put(new FourDirectionMonster(this, s), monsterNameSiteMap.get(s));
			} else {
				monsterSiteMap.put(new EightDirectionMonster(this, s), monsterNameSiteMap.get(s));
			}
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
