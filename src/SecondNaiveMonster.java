import java.util.*;

/**
 * SecondNaiveMonster, can have 8 directions: E, S, N, W, WN, NE, ES, SW
 * @author woody
 *
 */
public class SecondNaiveMonster extends Monster {

	/**
	 * Constructor for NaiveMonster.
	 * @param game
	 */
	public SecondNaiveMonster(Game game) {
		super(game);
	}

	@Override
	public Site move() {
		// get current sites for monster and rogue
        Site monster = game.getMonsterSite();
        Site rogue = game.getRogueSite();
        Site move = null;
        
        // get potential move to an hashmap
        Hashtable<Site, Integer> potentialMoves = new Hashtable<Site, Integer>();
        potentialMoves.put(new Site(monster.getX() - 1, monster.getY()), 0);
        potentialMoves.put(new Site(monster.getX(), monster.getY() - 1), 0);
        potentialMoves.put(new Site(monster.getX() + 1, monster.getY()), 0);
        potentialMoves.put(new Site(monster.getX(), monster.getY() + 1), 0); 
        potentialMoves.put(new Site(monster.getX() - 1, monster.getY() - 1), 0);
        potentialMoves.put(new Site(monster.getX() - 1, monster.getY() + 1), 0);
        potentialMoves.put(new Site(monster.getX() + 1, monster.getY() - 1), 0);
        potentialMoves.put(new Site(monster.getX() + 1, monster.getY() + 1), 0);
        for (Site s: potentialMoves.keySet()) {
        	if (dungeon.isLegalMove(monster, s)) {
        		potentialMoves.put(s, s.manhattanTo(rogue));
        	} else {
        		potentialMoves.remove(s);
        	}
        }
        // compute the min abs distance of potential move to rogue
        if (potentialMoves.isEmpty()) {
        	// do random move take random legal move
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
        } else {
        	return MapSort.sortByValue(potentialMoves, 1).keySet().iterator().next();
        }  
	}
}
