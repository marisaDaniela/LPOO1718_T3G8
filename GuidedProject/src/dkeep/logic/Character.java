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

	/*public static void checkDirection(char input, Coordinates coord, char[][]board, char mover)
	{

		if(input == 'A' || input == 'a')
		{	
			if(board[coord.getX()][coord.getY()-1]==' '){
				board[coord.getX()][coord.getY()-1]=mover;
				board[coord.getX()][coord.getY()]=' ';
			}else if(mover=='O' && board[coord.getX()][coord.getY()-1]=='k') {
				board[coord.getX()][coord.getY()-1]='$';
				board[coord.getX()][coord.getY()]=' ';
			}else if(mover==Game.hero && board[coord.getX()][coord.getY()-1]=='I' && Game.game_flag==2) {
				if(Game.flag_move==0) {
					Game.flag_move=1;
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


	}*/

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
				board[5][0]='S';
				board[6][0]='S';
				Game.flag=1;
			}
			else
				System.out.println("Nothing to do, try again!!");
		}
		if(input == 'C' || input == 'c') {
			if(atLever(x,y, board))
			{
				Game.hero='K';
				board[1][7]=' ';
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

}
