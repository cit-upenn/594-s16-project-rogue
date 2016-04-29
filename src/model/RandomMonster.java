package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Random monster which moves randomly when near to the rogue, 
 * otherwise move towards the rogue.
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 *
 */
public class RandomMonster extends Monster {
	
	/**
	 * Constructor
	 * @param game the game monster is in
	 */
    public RandomMonster(Game game, String name) {
    	super(game, name);
    	this.damage = 2;
	}

	@Override
	public Site move() {
    	// current sites for monster and rogue
        Site monster = game.getMonsterSite(name);
        Site rogue = game.getRogueSite();
        
        // move closer if monster is too far from rogue
        if (monster.manhattanTo(rogue) > 3) {            
    		FourBreadthFirstPaths bfp = new FourBreadthFirstPaths(dungeon, monster);
    		return bfp.pathTo(rogue).pop();
        }
        
        // get 4 adjacent sites
        int x = monster.getX();
        int y = monster.getY();
        
        Site east = new Site(x + 1, y);
        Site west = new Site(x - 1, y);
        Site north = new Site(x, y - 1);
        Site south = new Site(x, y + 1);
        
        // add legal moves to a list
        ArrayList<Site> sites = new ArrayList<Site>();
        if (dungeon.isLegalMove(monster, east)) sites.add(east);
        if (dungeon.isLegalMove(monster, west)) sites.add(west);
        if (dungeon.isLegalMove(monster, north)) sites.add(north);
        if (dungeon.isLegalMove(monster, south)) sites.add(south);
        
        // take random legal move
        Collections.shuffle(sites);        
        return sites.get(0);
	}

}
