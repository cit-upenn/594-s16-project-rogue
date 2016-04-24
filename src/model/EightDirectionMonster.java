package model;

import java.util.*;

/**
 * SecondNaiveMonster, can have 8 directions: E, S, N, W, WN, NE, ES, SW
 * @author woody
 *
 */
public class EightDirectionMonster extends Monster {

	/**
	 * Constructor for NaiveMonster.
	 * @param game
	 */
	public EightDirectionMonster(Game game) {
		super(game);
	}

	@Override
	public Site move() {
		// get current sites for monster and rogue
        Site monster = game.getMonsterSite();
        Site rogue = game.getRogueSite();
        
        // get 8 adjacent sites
        int x = monster.getX();
        int y = monster.getY();
        
        Site east = new Site(x + 1, y);
        Site west = new Site(x - 1, y);
        Site north = new Site(x, y - 1);
        Site south = new Site(x, y + 1);
        Site northeast = new Site(x + 1, y - 1);
        Site northwest = new Site(x - 1, y - 1);
        Site southeast = new Site(x + 1, y + 1);
        Site southwest = new Site(x - 1, y + 1);
        
        // create a priority queue to choose next site to move
        PriorityQueue<Site> pq = new PriorityQueue<Site>(new Comparator<Site>() {

			@Override
			public int compare(Site s1, Site s2) {
				return s1.manhattanTo(rogue) - s2.manhattanTo(rogue);
			}
        	
		});
        
        // add 8 adjacent sites to priority queue if legal move
        if (dungeon.isLegalMove(monster, east)) pq.offer(east);
        if (dungeon.isLegalMove(monster, west)) pq.offer(west);
        if (dungeon.isLegalMove(monster, north)) pq.offer(north);
        if (dungeon.isLegalMove(monster, south)) pq.offer(south);
        if (dungeon.isLegalMove(monster, northeast)) pq.offer(northeast);
        if (dungeon.isLegalMove(monster, northwest)) pq.offer(northwest);
        if (dungeon.isLegalMove(monster, southeast)) pq.offer(southeast);
        if (dungeon.isLegalMove(monster, southwest)) pq.offer(southwest);
        
        // return the site with minimum distance to rogue
        return pq.poll();
	}
	
}
