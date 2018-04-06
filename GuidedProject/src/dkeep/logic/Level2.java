package dkeep.logic;

public class Level2 extends Level{
	
	public Level2() {
		char[][] map=new char [][]{
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'I', ' ', ' ', ' ', 'O', '*', ' ', 'k', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', 'X'},
			{'X', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', 'X'},
			{'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}

		};
		this.setMap(map);
	}

	@Override
	public String printGame() {
		String toPrint = "";
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
					toPrint += (Character.toString(map[i][j]));
			}
			toPrint += ("\n");
		}
		return toPrint;
	}

}
