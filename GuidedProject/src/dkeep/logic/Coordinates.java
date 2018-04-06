package dkeep.logic;

/**
 *  Coordinates.java - class for the Coordinates
 */
public class Coordinates {
	
	private int x;
	private int y;
	
	/**
	 * default constructor for class 'Coordinates'
	 */
	public Coordinates() {
	}
	
	/**
	 * constructor for class 'Coordinates'
	 * @param x xx's position
	 * @param y yy's position
	 */
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * getX - to get xx's position
	 * @return A int data type
	 */
	public int getX() {
		return x;
	}

	/**
	 * setX - to set xx's position
	 * @param x new x to replace old x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * getY - to get yy's position
	 * @return A int data type
	 */
	public int getY() {
		return y;
	}

	/**
	 * setY - to set yy's position
	 * @param y new y to replace old y
	 */
	public void setY(int y) {
		this.y = y;
	}

}
