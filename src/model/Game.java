package model;

import java.io.File;
import java.io.FileNotFoundException;
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
    private char MONSTER;        // name of the monster (A - Z)
    private char ROGUE = '@';    // name of the rogue
    private int N;               // board dimension
    private Site monsterSite;    // location of monster
    private Site rogueSite;      // location of rogue
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

        // read in data
        N = Integer.parseInt(sc.nextLine());
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) { 
            String s = sc.nextLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(2 * j);

                // check for monster's location
                if (board[i][j] >= 'A' && board[i][j] <= 'Z') {
                    MONSTER = board[i][j];
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
            }
        }
        
        // initialize dungeon, monster, rogue
        dungeon = new Dungeon(board);
        monster = new RandomMonster(this);
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
    
    public boolean isEnd() {
    	return (rogueSite.equals(monsterSite));
    }
    
    public void nextStep(Site next) {
    	setRogueSite(next);
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

