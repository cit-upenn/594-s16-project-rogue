/*************************************************************************
 *  Compilation:  javac Rogue.java
 * 
 *************************************************************************/
public class Rogue {
	
    private Game game;    // the game this rogue is in
    private Dungeon dungeon;    // the dungeon in the game
    private int size;    // size of the dungeon

    /**
     * constructor
     * @param game the game this rogue is in
     */
    public Rogue(Game game) {
    	// initialize instance variables
        this.game = game;
        this.dungeon = game.getDungeon();
        this.size = dungeon.size();
    }
    
    /**
     * takes a legal move for the rogue
     * @return the next site to move in
     */
    public Site move() {
    	// current sites for monster and rogue
        Site monster = game.getMonsterSite();
        Site rogue = game.getRogueSite();
        Site move = null;

        // take random legal move
        int n = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Site site = new Site(i, j);
                if (dungeon.isLegalMove(rogue, site)) {
                    n++;
                    if (Math.random() <= 1.0 / n) move = site;
                }
            }
        }
        
        return move;
    }

}


