package dkeep.logic;

import java.util.Random;
import dkeep.cli.Game;

public class Character {

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

	public static boolean atLever(int x, int y, char[][]board)
	{
		if(board[x-1][y] == 'k' || board[x+1][y] == 'k' || board[x][y-1] == 'k' || board[x][y+1] == 'k')
			return true;
		else
			return false;
	}

	public static void checkDirection(char input, Coordinates pos, char[][]board, char mover)
	{
		int x= pos.getX();
		int y= pos.getY();


		if(input == 'A' || input == 'a')
		{	
			if(board[x][y-1]==' '){
				board[x][y-1]=mover;
				board[x][y]=' ';
			}else if(mover=='O' && board[x][y-1]=='k') {
				board[x][y-1]='$';
				board[x][y]=' ';
			}else if(mover==Game.hero && board[x][y-1]=='S' && Game.game_flag==1) {
				Game.game_flag=2;
			}else if(mover==Game.hero && board[x][y-1]=='I' && Game.game_flag==2) {
				board[x][y-1]='S';
			}else if(mover==Game.hero && board[x][y-1]=='S' && Game.game_flag==2) {
				System.out.println("END OF GAME\n");
				Game.flag_move=1;
				Game.WIN = true;
			}

		}
		if(input == 'S' || input == 's')
		{
			if(board[x+1][y]==' '){
				board[x+1][y]=mover;
				board[x][y]=' ';
			}else if(mover=='O' && board[x+1][y]=='k') {
				board[x+1][y]='$';
				board[x][y]=' ';
			}

		}
		if(input == 'W' || input == 'w')
		{
			if(board[x-1][y]==' '){
				board[x-1][y]=mover;
				board[x][y]=' ';
			}else if(mover=='O' && board[x-1][y]=='k') {
				board[x-1][y]='$';
				board[x-1][y]=' ';
			}

		}
		if(input == 'D' || input == 'd')
		{
			if(board[x][y+1]==' '){
				board[x][y+1]=mover;
				board[x][y]=' ';
			}else if(mover=='O' && board[x][y+1]=='k') {
				board[x][y+1]='$';
				board[x][y+1]=' ';
			}

		}
		if(input == 'T' || input == 't') {
			if(atLever(x,y, board))
			{
				for (int i = 0; i < board[0].length; i++) {
					for (int j = 0; j < board[0].length; j++) {
						if (board[i][j] == 'I')
							board[i][j] = 'S';
					}
				}
			}
			else
				System.out.println("Nothing to do, try again!!");
		}
		if(input == 'C' || input == 'c') {
			if(atLever(x,y, board))
			{
				Game.hero='K';

				for (int i = 0; i < board[0].length; i++) {
					for (int j = 0; j < board[0].length; j++) {
						if (board[i][j] == 'k')
							board[i][j] = ' ';
					}
				}

				board[x][y]='K';
			}
			else
				System.out.println("Nothing to do, try again!!");
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

	public static int randomChoice() {
		Random rand = new Random();

		int choice=rand.nextInt(2) + 1;

		return choice;

	}

	public static int randomNumberPlays() {
		Random rand = new Random();

		int num=rand.nextInt(6) + 2;

		return num;

	}

	public static String toString(char c) {
		// TODO Auto-generated method stub
		return null;
	}

}
