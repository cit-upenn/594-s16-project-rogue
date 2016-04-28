package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This class represents breadth first paths, which use BFS to calculate paths
 * from a certain source site.
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 * 
 */
public class FourBreadthFirstPaths {
	
	/**
	 * instance variables
	 */
	private static final int INFINITY = Integer.MAX_VALUE;    // max distance value
	private boolean[][] marked;    // is this site visited?
	private Site[][] edgeTo;    // last edge to this site on the path
	private int[][] distTo;    // distance from source to this site

	/**
	 * Computes the shortest path between the source site 
	 * and every other vertex in the dungeon.
	 * @param d the dungeon
	 * @param s the source site
	 */
	public FourBreadthFirstPaths(Dungeon d, Site s) {
		marked = new boolean[d.size()][d.size()];
		distTo = new int[d.size()][d.size()];
		edgeTo = new Site[d.size()][d.size()];
		bfs(d, s);
	}

	/**
	 * BFS from a single source site
	 * @param d the dungeon
	 * @param s the source site
	 */
	private void bfs(Dungeon d, Site s) {
		
		// use a queue to do BFS, and initialize instance variables
		Queue<Site> q = new LinkedList<Site>();
		for (int x = 0; x < d.size(); x++)
			for (int y = 0; y < d.size(); y++)
				distTo[x][y] = INFINITY;       
		distTo[s.getX()][s.getY()] = 0;
		marked[s.getX()][s.getY()] = true;
		
		// pop site from queue until it's empty
		q.offer(s);
		while (!q.isEmpty()) {
			
			// pop the next site in the queue
			Site v = q.poll();    	        
			int x = v.getX();
			int y = v.getY();

			// 4 adjacent sites
			Site east = new Site(x + 1, y);
			Site west = new Site(x - 1, y);
			Site north = new Site(x, y - 1);
			Site south = new Site(x, y + 1);

			// BFS the rest of the dungeon
			if (d.isLegalMove(v, east) && !marked[east.getX()][east.getY()]) {
				edgeTo[east.getX()][east.getY()] = v;
				distTo[east.getX()][east.getY()] = distTo[x][y] + 1;
				marked[east.getX()][east.getY()] = true;
				q.offer(east);
			}
			if (d.isLegalMove(v, west) && !marked[west.getX()][west.getY()]) {
				edgeTo[west.getX()][west.getY()] = v;
				distTo[west.getX()][west.getY()] = distTo[x][y] + 1;
				marked[west.getX()][west.getY()] = true;
				q.offer(west);
			}
			if (d.isLegalMove(v, north) && !marked[north.getX()][north.getY()]) {
				edgeTo[north.getX()][north.getY()] = v;
				distTo[north.getX()][north.getY()] = distTo[x][y] + 1;
				marked[north.getX()][north.getY()] = true;
				q.offer(north);
			}
			if (d.isLegalMove(v, south) && !marked[south.getX()][south.getY()]) {
				edgeTo[south.getX()][south.getY()] = v;
				distTo[south.getX()][south.getY()] = distTo[x][y] + 1;
				marked[south.getX()][south.getY()] = true;
				q.offer(south);
			}

		}

	}

	/**
	 * Is there a path between the source site and site s?
	 * @param s the site s
	 * @return true if there is a path
	 */
	public boolean hasPathTo(Site s) {
		return marked[s.getX()][s.getY()];
	}

	/**
	 * Returns the number of edges in a BFS shortest path between the source site and s.
	 * @param s the site s
	 * @return the number of edges in a BFS shortest path
	 */
	public int distTo(Site s) {
		return distTo[s.getX()][s.getY()];
	}

	/**
	 * Returns a shortest path between the source site and s.
	 * @param s the site s
	 * @return the sequence of vertices on a shortest path
	 */
	public Stack<Site> pathTo(Site s) {
		
		// put all sites on the path into a stack
		if (!hasPathTo(s)) return null;
		Stack<Site> path = new Stack<Site>();
		for (Site site = s; distTo[site.getX()][site.getY()] != 0; site = edgeTo[site.getX()][site.getY()])
			path.push(site);
		return path;
	}

}
