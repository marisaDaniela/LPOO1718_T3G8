package dkeep.logic;

import java.util.Random;

public class Character {
	
	static int flag=0;
	static char hero='H';
	static int game_flag=1;
	static int flag_move=0;
	
	public static Coordinates getPos(char[][] board, char mover, int size){
		Coordinates pos= new Coordinates();
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(board[i][j]==mover)
				{
					pos.setX(i); 
					pos.setY(j);
				}
			}
		}
		return pos;
	}
	
	public static void checkDirection(char input, Coordinates coord, char[][]board, char mover)
	{
		
		if(input == 'A' || input == 'a')
		{	
			if(board[coord.getX()][coord.getY()-1]==' '){
				board[coord.getX()][coord.getY()-1]=mover;
				board[coord.getX()][coord.getY()]=' ';
			}else if(mover=='O' && board[coord.getX()][coord.getY()-1]=='k') {
				board[coord.getX()][coord.getY()-1]='$';
				board[coord.getX()][coord.getY()]=' ';
			}else if(mover==hero && board[coord.getX()][coord.getY()-1]=='I' && game_flag==2) {
				if(flag_move==0) {
					flag_move=1;
					board[coord.getX()][coord.getY()-1]='S';}
				else {
					System.out.println("END OF GAME\n");
				}
					
			}

		}
		if(input == 'S' || input == 's')
		{
			if(board[coord.getX()+1][coord.getY()]==' '){
				board[coord.getX()+1][coord.getY()]=mover;
				board[coord.getX()][coord.getY()]=' ';
			}else if(mover=='O' && board[coord.getX()+1][coord.getY()]=='k') {
				board[coord.getX()+1][coord.getY()]='$';
				board[coord.getX()][coord.getY()]=' ';
			}
		
		}
		if(input == 'W' || input == 'w')
		{
			if(board[coord.getX()-1][coord.getY()]==' '){
				board[coord.getX()-1][coord.getY()]=mover;
				board[coord.getX()][coord.getY()]=' ';
			}else if(mover=='O' && board[coord.getX()-1][coord.getY()]=='k') {
				board[coord.getX()-1][coord.getY()]='$';
				board[coord.getX()-1][coord.getY()]=' ';
			}

		}
		if(input == 'D' || input == 'd')
		{
			if(board[coord.getX()][coord.getY()+1]==' '){
				board[coord.getX()][coord.getY()+1]=mover;
				board[coord.getX()][coord.getY()]=' ';
			}else if(mover=='O' && board[coord.getX()][coord.getY()+1]=='k') {
				board[coord.getX()][coord.getY()+1]='$';
				board[coord.getX()][coord.getY()+1]=' ';
			}
	
		}
		

	}
	
		
public static char randomDirection() {
		
		Random rand = new Random();
		
		char move=' ';
		
		int num_move=rand.nextInt(4) + 1;
		
		switch(num_move) {
			case 1:
				move='A';
				break;
			case 2:
				move='S';
				break;
			case 3:
				move='W';
				break;
			case 4:
				move='D';
				break;
		
		}
		
		return move;
		
	}

}
