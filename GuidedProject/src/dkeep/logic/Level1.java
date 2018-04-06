package dkeep.logic;


public class Level1 extends Level{

	public Level1() {
		char[][] map=new char [][]{
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
			{'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
			{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', ' ', 'X'},
			{'I', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', ' ', 'X'},
			{'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
			{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X'},
			{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}

		};
		this.setMap(map);
	}

	@Override
	public String printGame() {
		String toPrint = "";
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 10; i++) {
					toPrint += (Character.toString(map[i][j]));
			}
			toPrint += ("\n");
		}
		return toPrint;
	}
}
