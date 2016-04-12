/*************************************************************************
 *  Compilation:  javac Site.java
 *
 *  simple data type for an (x, y) site
 *
 *************************************************************************/
public class Site {
	
    private int x;    // row position x
    private int y;    // column position y

    /**
     * constructor
     * @param x row position x
     * @param y column position y
     */
    public Site(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * gets the row position x
     * @return row position x
     */
    public int getX() { 
    	return x; 
    }

    /**
     * gets the column position y
     * @return column position y
     */
    public int getY() { 
    	return y; 
    }

    /**
     * gets the Manhattan distance between this site and the other
     * @param that the other site
     * @return Manhattan distance between these two
     */
    public int manhattanTo(Site that) {
        return Math.abs(this.getX() - that.getX()) + Math.abs(this.getY() - that.getY());
    }

    /**
     * tells if this site is equal to the other site
     * @param that the other site
     * @return true if they are equal
     */
    public boolean equals(Site that) {
        return manhattanTo(that) == 0;
    }

}


