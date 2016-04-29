package model;

/**
 * This class is used to store rogue info
 * 
 * @author Zhiyuan Li
 * @author Yi Shang
 * @author Di Wu
 */
public class Rogue {

	/**
	 * instance variable for rogue
	 */
	private int hp;
	private boolean hasSword;

	/**
	 * constructor
	 * 
	 * @param game
	 *            the game this rogue is in
	 */
	public Rogue(Game game) {
		// initialize instance variables
		this.hp = 1;
	}

	/**
	 * rogue power up
	 */
	public void powerup() {
//		System.out.println(hp);
		hp = hp + 1;
	}

	/**
	 * rogue take damage
	 * 
	 * @param damage
	 */
	public void takeDamage(int damage) {
		hp = hp - damage;
	}
	
	/**
	 * @return the hasSword
	 */
	public boolean isHasSword() {
		return hasSword;
	}

	/**
	 * @param hasSword the hasSword to set
	 */
	public void setHasSword(boolean hasSword) {
		this.hasSword = hasSword;
	}

	/**
	 * check is rogue dead
	 * @return true / false
	 */
	public boolean isDead() {
		return (hp < 0);
	}

}
