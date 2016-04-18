/*************************************************************************
 *  Compilation:  javac Monster.java
 * 
 *************************************************************************/
public abstract class Monster {
	
    private Game game;    // the game this monster is in
    private Dungeon dungeon;    // the dungeon in the game
    private int size;    // size of the dungeon

	/**
     * constructor
     * @param game the game this monster is in
     */
    public Monster(Game game) {
    	// initialize instance variables
        this.game = game;
        this.dungeon = game.getDungeon();
        this.size = dungeon.size();
    }
    
    /**
	 * @return the game
	 */
	public Game getGame() {
		return game;
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


