package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

/*************************************************************************
 *  Compilation:  javac Game.java
 *  Execution:    java Game < input.txt
 *  Dependencies: Dungeon.java Site.java In.java Monster.java Rogue.java
 *
 *************************************************************************/
public class Game extends Observable {

	private Dungeon dungeon;     // the dungeon
	public static final char MONSTER = 'M';  // name of the monster
	public static final char ROGUE = '@';    // name of the rogue
	public static final char POWERUP = '*';	 // name of the power up
	public static final char TUNNEL = '#';	 // name of tunnel
	private int N;               // board dimension
	private Site monsterSite;    // location of monster
	private Site rogueSite;      // location of rogue
	private Site tunnelSite;	 // location of tunnel
	private ArrayList<Site> powerUpSiteMap;	 // location of power up
	private Monster monster;     // the monster
	private Rogue rogue;         // the rogue

	// initialize board from file
	public Game(String filename) {
		// create Scanner to read in file
		Scanner sc = null;
		try {
			sc = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		powerUpSiteMap = new ArrayList<>();

		// read in data
		N = Integer.parseInt(sc.nextLine());
		char[][] board = new char[N][N];
		for (int i = 0; i < N; i++) { 
			String s = sc.nextLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = s.charAt(2 * j);

				// check for monster's location
				if (board[i][j] == MONSTER) {
					board[i][j] = '.';
					System.out.println("monster@ " + i  +  "," + j);
					monsterSite = new Site(i, j);
				}

				// check for rogue's location
				if (board[i][j] == ROGUE) {
					board[i][j] = '.';
					rogueSite  = new Site(i, j);
					System.out.println("rogue@ " + i  +  "," + j);
				}

				// check for power up location
				if (board[i][j] == POWERUP) {
					board[i][j] = '.';
					powerUpSiteMap.add(new Site(i, j));
				}

				// check for tunnel location
				if (board[i][j] == TUNNEL) {
					board[i][j] = '.';
					tunnelSite  = new Site(i, j);
				}
			}
		}

		// initialize dungeon, monster, rogue
		dungeon = new Dungeon(board);
		switch (filename) {
		// easy mode monster
		case "dungeon/1.txt":
		case "dungeon/2.txt":
		case "dungeon/dungeonC.txt":
			monster = new RandomMonster(this);
			break;
			// medium mode monster
		case "dungeon/dungeonF.txt":
		case "dungeon/dungeonI.txt":
		case "dungeon/dungeonH.txt":
			monster = new FourDirectionMonster(this);
			break;
			// hard mode monster
		case "dungeon/dungeonP.txt":
		case "dungeon/dungeonJ.txt":
		case "dungeon/dungeonQ.txt":
			monster = new EightDirectionMonster(this);
			break;
		}

		rogue = new Rogue(this);
	}

	/**
	 * gets the name of the monster
	 * @return the name of the monster
	 */
	public char getMonsterName() {
		return MONSTER;
	}

	/**
	 * gets the rogue of this game
	 * @return the rogue of this game
	 */
	public Rogue getRogue() {
		return rogue;
	}
	
	public void removePowerUpSiteMap(Site s) {
		powerUpSiteMap.remove(s);
		System.out.println("after: " + powerUpSiteMap.size());
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
	 * gets the monster of this game
	 * @return the monster of this game
	 */
	public Monster getMonster() {
		return monster;
	}

	/**
	 * gets the position of monster
	 * @return the position of monster
	 */
	public Site getMonsterSite() { 
		return monsterSite; 
	}

	/**
	 * sets the position of rogue
	 * @return the position of rogue
	 */
	public void setRogueSite(Site rogueSite) { 
		this.rogueSite = rogueSite;
		setChanged();
		notifyObservers();
	}

	/**
	 * sets the position of monster
	 * @return the position of monster
	 */
	public void setMonsterSite(Site monsterSite) { 
		this.monsterSite = monsterSite; 
		setChanged();
		notifyObservers();
	}

	/**
	 * gets the position of rogue
	 * @return the position of rogue
	 */
	public Site getRogueSite() { 
		return rogueSite;   
	}

	/**
	 * gets the dungeon
	 * @return the dungeon
	 */
	public Dungeon getDungeon() { 
		return dungeon;     
	}

	/**
	 * check is games end
	 * @return true / false
	 */
	public boolean isEnd() {
		return (rogueSite.equals(monsterSite));
	}

	/**
	 * get next step of monster
	 * @param next
	 */
	public void nextStep(Site next) {
		setRogueSite(next);
		removePowerUpSiteMap(next);
		if (!isEnd()) {
			setMonsterSite(getMonster().move());
		}
	}

	//    /**
	//     * simulates the game play
	//     * play until monster catches the rogue
	//     */
	//    public void play() {
	//        for (int t = 1; true; t++) {
	//            System.out.println("Move " + t);
	//            System.out.println();
	//            
	//            // monster moves
	//            if (monsterSite.equals(rogueSite)) break;
	//            Site next = monster.move();
	//            if (dungeon.isLegalMove(monsterSite, next)) monsterSite = next;
	//            else throw new RuntimeException("Monster caught cheating");
	//            System.out.println(this);
	//
	//            // rogue moves
	//            if (monsterSite.equals(rogueSite)) break;
	//            next = rogue.move();
	//            if (dungeon.isLegalMove(rogueSite, next)) rogueSite = next;
	//            else throw new RuntimeException("Rogue caught cheating");
	//            System.out.println(this);
	//        }
	//
	//        System.out.println("Caught by monster");
	//
	//    }

}

