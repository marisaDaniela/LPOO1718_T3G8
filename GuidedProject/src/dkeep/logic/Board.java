package dkeep.logic;

public class Board {
	
	private char[][] map;

	public Board(char[][] map) {
		this.setMap(map);
	}

	public char[][] getMap() {
		return map;
	}

	public void setMap(char[][] map) {
		this.map = map;
	}

}
