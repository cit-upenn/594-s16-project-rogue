package model;

/**
 * This class represents an abstract monster.
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 *
 */
public abstract class Monster {
	
	/**
	 * instance variables
	 */
	protected Game game;    // the game this monster is in
	protected Dungeon dungeon;    // the dungeon in the game
	protected int size;    // size of the dungeon
	protected int damage;    // the damage done by this monster
	protected boolean hit;    // if hit the rogue last round	
	protected String name;    // name of the monster

	/**
     * constructor
     * @param game the game this monster is in
     */
    public Monster(Game game, String name) {
    	// initialize instance variables
        this.game = game;
        this.name = name;
        this.dungeon = game.getDungeon();
        this.size = dungeon.size();
        this.hit = false;
    }
    
    /**
     * gets the name of the monster
     * @return the name of the monster
     */
	public String getName() {
		return name;
	}
    
    /**
     * gets the game
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}
	
	/**
	 * gets the damage
	 * @return the damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * gets the dungeon
	 * @return the dungeon
	 */
	public Dungeon getDungeon() {
		return dungeon;
	}

	/**
	 * gets the size
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


