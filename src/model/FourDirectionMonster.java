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
				return distance(s1, rogue) - distance(s2, rogue);
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
	
	/**
	 * gets the distance between start and end
	 * @param start start site
	 * @param end end site
	 * @return distance between them
	 */
	private int distance(Site start, Site end) {
		// return 0 if same site
		if (start.equals(end)) return 0;
		
        // get 4 adjacent sites
        int x = start.getX();
        int y = start.getY();
        
        Site east = new Site(x + 1, y);
        Site west = new Site(x - 1, y);
        Site north = new Site(x, y - 1);
        Site south = new Site(x, y + 1);
        
        // get 4 distances to end
        int eastDist = Integer.MAX_VALUE;
        int westDist = Integer.MAX_VALUE;
        int northDist = Integer.MAX_VALUE;
        int southDist = Integer.MAX_VALUE;
        
        if (dungeon.isLegalMove(start, east)) eastDist = distance(east, end);
        if (dungeon.isLegalMove(start, west)) westDist = distance(west, end);
        if (dungeon.isLegalMove(start, north)) northDist = distance(north, end);
        if (dungeon.isLegalMove(start, south)) southDist = distance(south, end);
        
        // return the smallest distance
        if (eastDist < westDist && eastDist < northDist && eastDist < southDist) return eastDist + 1;
        if (westDist < eastDist && westDist < northDist && westDist < southDist) return westDist + 1;
        if (northDist < eastDist && northDist < westDist && northDist < southDist) return northDist + 1;
        return southDist + 1;
	}

}
