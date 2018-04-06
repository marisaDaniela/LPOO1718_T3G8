package dkeep.logic;
/**  
* Level.java - class for the Level
*/ 
public abstract class Level {
	
	protected char[][] map;
	/**
	 * Method to gets a current board
	 * @return A char[][] data type
	 */
	public char[][] getMap() {
		return map;
	}
	/**
	 * Method to sets a current board
	 * @param map new map to replace old
	 */
	public void setMap(char[][] map) {
		this.map = map;
	}
}
