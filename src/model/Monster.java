package model;

/*************************************************************************
 *  Compilation:  javac Monster.java
 * 
 *************************************************************************/
public abstract class Monster {
	
	protected Game game;    // the game this monster is in
	protected Dungeon dungeon;    // the dungeon in the game
	protected int size;    // size of the dungeon
	protected int damage;
	protected boolean hit;    // if hit the rogue last round	

	/**
     * constructor
     * @param game the game this monster is in
     */
    public Monster(Game game) {
    	// initialize instance variables
        this.game = game;
        this.dungeon = game.getDungeon();
        this.size = dungeon.size();
        this.hit = false;
    }
    
    /**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}
	
	/**
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @return the dungeon
	 */
	public Dungeon getDungeon() {
		return dungeon;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

    /**
     * takes a legal move for the monster
     * @return the next site to move in
     */
    public abstract Site move();

}


