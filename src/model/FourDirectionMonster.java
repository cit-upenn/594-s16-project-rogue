package model;

/**
 * This monster can only move to 4 direction: East, South, West, North.
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 *
 */
public class FourDirectionMonster extends Monster {

	/**
	 * Constructor
	 * @param game the rogue game
	 */
	public FourDirectionMonster(Game game, String name) {
		super(game, name);
		this.damage = 1;
	}

	@Override
	public Site move() {
		// get current sites for monster and rogue
        Site monster = game.getMonsterSite(name);
        Site rogue = game.getRogueSite(); 
        
        // stay still if already hit rogue
        if (monster.equals(rogue)) {
        	hit = true;
        	return monster; 
        }
        
        // create BFS paths from monster to rogue
		FourBreadthFirstPaths bfp = new FourBreadthFirstPaths(dungeon, monster);
		Site next = bfp.pathTo(rogue).pop();
		if (next.equals(rogue)) {
			if (hit) {
				hit = false;
				return monster;
			} else {
				hit = true;
				return next;
			}
		}
		
		hit = false;
		return next;
	}

}
