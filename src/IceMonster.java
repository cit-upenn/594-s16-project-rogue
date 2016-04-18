
public class IceMonster extends Monster {
	
	private Game game;    // the game this monster is in
    private Dungeon dungeon;    // the dungeon in the game
    private int size;    // size of the dungeon
    
    public IceMonster(Game game) {
    	super(game);
	}

	@Override
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
                if (dungeon.isLegalMove(monster, site)) {
                    n++;
                    if (Math.random() <= 1.0 / n) move = site;
                }
            }
        }
        
        return move;
	}

}
