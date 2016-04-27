package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstPaths {
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[][] marked;  // marked[v] = is there an s-v path
	private Site[][] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
	private int[][] distTo;      // distTo[v] = number of edges shortest s-v path

	/**
	 * Computes the shortest path between the source vertex <tt>s</tt>
	 * and every other vertex in the graph <tt>G</tt>.
	 * @param G the graph
	 * @param s the source vertex
	 */
	public BreadthFirstPaths(Dungeon d, Site s) {
		marked = new boolean[d.size()][d.size()];
		distTo = new int[d.size()][d.size()];
		edgeTo = new Site[d.size()][d.size()];
		bfs(d, s);
	}

	// breadth-first search from a single source
	private void bfs(Dungeon d, Site s) {
		Queue<Site> q = new LinkedList<Site>();
		for (int x = 0; x < d.size(); x++)
			for (int y = 0; y < d.size(); y++)
				distTo[x][y] = INFINITY;       
		distTo[s.getX()][s.getY()] = 0;
		marked[s.getX()][s.getY()] = true;
		q.offer(s);

		while (!q.isEmpty()) {
			Site v = q.poll();    	        
			int x = v.getX();
			int y = v.getY();

			Site east = new Site(x + 1, y);
			Site west = new Site(x - 1, y);
			Site north = new Site(x, y - 1);
			Site south = new Site(x, y + 1);

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
	 * Is there a path between the source vertex <tt>s</tt> (or sources) and vertex <tt>v</tt>?
	 * @param v the vertex
	 * @return <tt>true</tt> if there is a path, and <tt>false</tt> otherwise
	 */
	public boolean hasPathTo(Site s) {
		return marked[s.getX()][s.getY()];
	}

	/**
	 * Returns the number of edges in a shortest path between the source vertex <tt>s</tt>
	 * (or sources) and vertex <tt>v</tt>?
	 * @param v the vertex
	 * @return the number of edges in a shortest path
	 */
	public int distTo(Site s) {
		return distTo[s.getX()][s.getY()];
	}

	/**
	 * Returns a shortest path between the source vertex <tt>s</tt> (or sources)
	 * and <tt>v</tt>, or <tt>null</tt> if no such path.
	 * @param s the vertex
	 * @return the sequence of vertices on a shortest path, as an Iterable
	 */
	public Stack<Site> pathTo(Site s) {
		if (!hasPathTo(s)) return null;
		Stack<Site> path = new Stack<Site>();
		Site site;
		for (site = s; distTo[site.getX()][site.getY()] != 0; site = edgeTo[site.getX()][site.getY()])
			path.push(site);
		return path;
	}

}
