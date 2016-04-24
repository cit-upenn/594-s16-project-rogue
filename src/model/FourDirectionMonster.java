package model;

import java.util.*;

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
        
        // get 4 adjacent sites
        int x = monster.getX();
        int y = monster.getY();
        
        Site east = new Site(x + 1, y);
        Site west = new Site(x - 1, y);
        Site north = new Site(x, y - 1);
        Site south = new Site(x, y + 1);
        
        // create a priority queue to choose next site to move
        PriorityQueue<Site> pq = new PriorityQueue<Site>(new Comparator<Site>() {

			@Override
			public int compare(Site s1, Site s2) {
				return s1.manhattanTo(rogue) - s2.manhattanTo(rogue);
			}
        	
		});
        
        // add 4 adjacent sites to priority queue if legal move
        if (dungeon.isLegalMove(monster, east)) pq.offer(east);
        if (dungeon.isLegalMove(monster, west)) pq.offer(west);
        if (dungeon.isLegalMove(monster, north)) pq.offer(north);
        if (dungeon.isLegalMove(monster, south)) pq.offer(south);
        
        // return the site with minimum distance to rogue
        return pq.poll();
	}

}
