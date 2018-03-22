package dkeep.logic;

public class Club extends Character{
	
	public static void clubSwing(Coordinates pos, char[][]board2) {
		char move= randomDirection();
		Coordinates position= getPos(board2, '*', 9);
		
		switch(move) {
		
		case 'W':
			if(board2[pos.getX()-1][pos.getY()]==' ') {
				board2[position.getX()][position.getY()]=' ';
				board2[pos.getX()-1][pos.getY()]='*';
			}else if(board2[pos.getX()-1][pos.getY()]=='X' || board2[pos.getX()-1][pos.getY()]=='I') {
				clubSwing(pos, board2);
			}
			
			else if(board2[pos.getX()-1][pos.getY()]=='k') {
				board2[position.getX()][position.getY()]=' ';
				board2[pos.getX()-1][pos.getY()]='$';
			}
			break;
			
		case 'A':
			if(board2[pos.getX()][pos.getY()-1]==' ') {
				board2[position.getX()][position.getY()]=' ';
				board2[pos.getX()][pos.getY()-1]='*';
			}else if(board2[pos.getX()][pos.getY()-1]=='X' || board2[pos.getX()][pos.getY()-1]=='I') {
				clubSwing(pos, board2);
			}
			else if(board2[pos.getX()][pos.getY()-1]=='k') {
				board2[position.getX()][position.getY()]=' ';
				board2[pos.getX()][pos.getY()-1]='$';
			}
			break;
			
		case 'S':
			if(board2[pos.getX()+1][pos.getY()]==' ') {
				board2[position.getX()][position.getY()]=' ';
				board2[pos.getX()+1][pos.getY()]='*';
			}else if(board2[pos.getX()+1][pos.getY()]=='X' || board2[pos.getX()+1][pos.getY()]=='I') {
				clubSwing(pos, board2);
			}
			else if(board2[pos.getX()+1][pos.getY()]=='k') {
				board2[position.getX()][position.getY()]=' ';
				board2[pos.getX()+1][pos.getY()]='$';

			}
			break;
			
		case 'D':
			if(board2[pos.getX()][pos.getY()+1]==' ') {
				board2[position.getX()][position.getY()]=' ';
				board2[pos.getX()][pos.getY()+1]='*';
			}else if(board2[pos.getX()][pos.getY()+1]=='X' || board2[pos.getX()][pos.getY()+1]=='I') {
				clubSwing(pos, board2);
			}
			else if(board2[pos.getX()][pos.getY()+1]=='k') {
				board2[position.getX()][position.getY()]=' ';
				board2[pos.getX()][pos.getY()+1]='$';
			}
			break;
		}
	}

}
