package dkeep.logic;
/**
 * Level2.java - class for level 2
 * @see Level
 */
public class Level2 extends Level{

	/**
	 * Default contructor for level2
	 */
	public Level2() {
		char[][] map=new char [][]{
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'I', ' ', ' ', ' ', 'O', '*', ' ', 'k', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', 'a' ,' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', 'X'},
			{'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}

		};
		this.setMap(map);
	}


}
