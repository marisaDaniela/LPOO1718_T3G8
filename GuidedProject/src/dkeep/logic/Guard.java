package dkeep.logic;

public class Guard extends Character{
	
	public static void movement(char[][]board, int count) {
		Coordinates pos= getPos(board, 'G', 10);
		char moves[] = {'A','S','S','S','S','A','A','A','A','A','A','S','D','D','D','D','D','D','D','W','W','W','W','W'};
		checkDirection(moves[count], pos, board, 'G');
	}

}
