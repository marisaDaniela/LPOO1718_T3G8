
package dkeep.logic;

import dkeep.cli.Game;

public class Ogre extends Character {
	
	public static void ogreMovement(char[][]board2) {
		
		char move= randomDirection();		
		
		Coordinates pos= getPos(board2, 'O', 9);
		
		checkDirection(move, pos, board2, 'O');
		
		pos= getPos(board2, 'O', 9);
		
		if(Game.hero!='K')
			board2[1][7]='k';
		
		Club.clubSwing(pos, board2);
		
	}
	
	

}
