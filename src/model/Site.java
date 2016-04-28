package model;

/**
 * This class is used to keep location info
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 */
public class Site {

	/**
	 * Instance variable for Site
	 */
	private int x; // row position x
	private int y; // column position y

	/**
	 * constructor
	 * 
	 * @param x
	 *            row position x
	 * @param y
	 *            column position y
	 */
	public Site(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * gets the row position x
	 * 
	 * @return row position x
	 */
	public int getX() {
		return x;
	}

	/**
	 * gets the column position y
	 * 
	 * @return column position y
	 */
	public int getY() {
		return y;
	}

	/**
	 * gets the Manhattan distance between this site and the other
	 * 
	 * @param that
	 *            the other site
	 * @return Manhattan distance between these two
	 */
	public int manhattanTo(Site that) {
		return Math.abs(this.getX() - that.getX()) + Math.abs(this.getY() - that.getY());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Site other = (Site) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
