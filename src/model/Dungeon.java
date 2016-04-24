package model;

/*************************************************************************
 *  Compilation:  javac Dungeon.java
 *
 *************************************************************************/
public class Dungeon {
	
	private char[][] board;    // the character representation of this board   
    private boolean[][] isRoom;    // is (x, y) a room site?
    private boolean[][] isCorridor;    // is (x, y) a corridor site?
    private int size;    // dimension of the dungeon

    /**
     * constructor
     * initializes a new dungeon based on the given board
     * @param board the board info from input file
     */
    public Dungeon(char[][] board) {
    	// TODO
        this.size = board.length;
        this.board = board;
        this.isRoom = new boolean[size][size];
        this.isCorridor = new boolean[size][size];
        
        // initialize each grid
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if      (board[i][j] == '.') isRoom[i][j] = true;
                else if (board[i][j] == '+') isCorridor[i][j] = true;
            }
        }
        
    }

    /**
     * returns dimension of the dungeon
     * @return dimension of the dungeon
     */
    public int size() { 
    	return size; 
    }
    
    /**
     * returns character representation of this board
     * @return character representation of this board
     */
    public char[][] getBoard() {
    	return board;
    }

    /**
     * tells if site is a corridor site
     * @param site the input site
     * @return true if site is a corridor
     */
    public boolean isCorridor(Site site) {
    	// get (x, y)
        int x = site.getX();
        int y = site.getY();
        
        // return false if out of bounds
        if (x < 0 || y < 0 || x >= size || y >= size) return false;
        
        return isCorridor[x][y];
    }

    /**
     * tells if site is a room site
     * @param site the input site
     * @return true if site is a room
     */
    public boolean isRoom(Site site) {
    	// get (x, y)
        int x = site.getX();
        int y = site.getY();
        
        // return false if out of bounds
        if (x < 0 || y < 0 || x >= size || y >= size) return false;
        
        return isRoom[x][y];
    }

    /**
     * tells if this site is a wall
     * @param site the input site
     * @return true if this site is a wall
     */
    public boolean isWall(Site site) {
        return !isRoom(site) && !isCorridor(site);
    }

    /**
     * tells if s1 to s2 is a legal move
     * @param s1 site 1
     * @param s2 site 2
     * @return true if s1 to s2 is a legal move
     */
    public boolean isLegalMove(Site s1, Site s2) {
    	// get (x, y)
        int x1 = s1.getX();
        int y1 = s1.getY();
        int x2 = s2.getX();
        int y2 = s2.getY();
        
        // return false if out of bound
        if (x1 < 0 || y1 < 0 || x1 >= size || y1 >= size) return false;
        if (x2 < 0 || y2 < 0 || x2 >= size || y2 >= size) return false;
        
        // return false if any of two site is a wall
        if (isWall(s1) || isWall(s2)) return false;
        
        // return false if the vertical or horizontal distance is more than 1
        if (Math.abs(x1 - x2) > 1)  return false;
        if (Math.abs(y1 - y2) > 1)  return false;
        
        // return true if both sites are rooms
        if (isRoom(s1) && isRoom(s2)) return true;
        
        // return true if adjacent vertical or horizontal move
        if (x1 == x2) return true;
        if (y1 == y2) return true;

        // otherwise return false
        return false;
    }

}


