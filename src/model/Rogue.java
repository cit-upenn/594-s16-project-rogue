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
	private int numOfSword;

	/**
	 * constructor
	 * 
	 * @param game
	 *            the game this rogue is in
	 */
	public Rogue(Game game) {
		// initialize instance variables
		hp = 1;
		numOfSword = 0;
	}

	/**
	 * rogue power up
	 */
	public void powerup() {
		hp++;
	}

	/**
	 * rogue take damage
	 * 
	 * @param damage
	 */
	public void takeDamage(int damage) {
		hp -= damage;
	}
	
	/**
	 * @return the hasSword
	 */
	public boolean hasSword() {
		return numOfSword > 0;
	}
	
	/**
	 * adds a sword to rogue
	 */
	public void addSword() {
		numOfSword++;
	}
	
	/**
	 * removes a sword from rogue
	 */
	public void removeSword() {
		numOfSword--;
	}

	/**
	 * check is rogue dead
	 * @return true / false
	 */
	public boolean isDead() {
		return hp <= 0;
	}

}
