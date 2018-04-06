package dkeep.logic;

public abstract class Level {
	
	protected char[][] map;

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}
	/**
	 * Method to print game
	 * @return A String data type
	 */
	public abstract String printGame();
	
}
