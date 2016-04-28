package model;

/*************************************************************************
 *  Compilation:  javac Rogue.java
 * 
 *************************************************************************/
public class Rogue {
	
    private Game game;    // the game this rogue is in
    private Dungeon dungeon;    // the dungeon in the game
    private int size;    // size of the dungeon
    private int hp;
    private int damage;

    /**
     * constructor
     * @param game the game this rogue is in
     */
    public Rogue(Game game) {
    	// initialize instance variables
        this.game = game;
        this.dungeon = game.getDungeon();
        this.size = dungeon.size();
        this.hp = 100;
    }
    
    /**
     * rogue power up
     */
    public void powerup() {
    	System.out.println(hp);
    	hp = hp + 1;
    }
    
    /**
     * rogue take damage
     * @param damage
     */
    public void takeDamage(int damage) {
    	hp = hp - damage;
    	System.out.println(hp);
    }
    
    public boolean isDead() {
    	return (hp < 0);
    }
   
}


