package dkeep.logic;

public class Ogre extends Character{
	
	public static void ogreMovement(char[][]board2) {
		
		char move= randomDirection();		
		
		Coordinates pos= getPos(board2, 'O', 9);
		
		checkDirection(move, pos, board2, 'O');
		
		pos= getPos(board2, 'O', 9);
		
		if(hero!='K')
			board2[1][7]='k';
		
		clubSwing(pos, board2);
		
	}
	
	

}
