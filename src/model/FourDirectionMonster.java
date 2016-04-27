package model;

/**
 * NaiveMonster, can only move to 4 direction: East, South, West, North
 * @author woody
 *
 */
public class FourDirectionMonster extends Monster {

	/**
	 * Constructor for NaiveMonster.
	 * @param game
	 */
	public FourDirectionMonster(Game game) {
		super(game);
	}

	@Override
	public Site move() {
		// get current sites for monster and rogue
        Site monster = game.getMonsterSite();
        Site rogue = game.getRogueSite();
        
		BreadthFirstPaths bfp = new BreadthFirstPaths(dungeon, monster);
		return bfp.pathTo(rogue).pop();
	}

}
