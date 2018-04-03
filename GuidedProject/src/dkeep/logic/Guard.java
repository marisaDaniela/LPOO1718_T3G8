package dkeep.logic;

import java.util.Random;

public class Guard extends Character{
	
	public static String personality= new String();
	
	public static void movement(char[][]board, int count) {
		Coordinates pos= getPos(board, 'G', 10);
		char moves[] = {'A','S','S','S','S','A','A','A','A','A','A','S','D','D','D','D','D','D','D','W','W','W','W','W'};
		if(personality == "Rookie") {
			checkDirection(moves[count], pos, board, 'G');
		}else if(personality == "Drunken") {
			
			int moving = randomChoice();
			
			if(moving==1) {
				checkDirection(moves[count], pos, board, 'G');
			}
			else if(moving==2) {
				board[pos.getX()][pos.getY()]='g';
				count--;
			}

		}
		
		
	}

}
