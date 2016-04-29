package model;

/**
 * This monster can move to 8 directions: E, S, N, W, WN, NE, ES, SW
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 *
 */
public class EightDirectionMonster extends Monster {

	/**
	 * Constructor
	 * @param game the rogie
	 */
	public EightDirectionMonster(Game game, String name) {
		super(game, name);
		this.damage = 3;
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
		EightBreadthFirstPaths bfp = new EightBreadthFirstPaths(dungeon, monster);
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
