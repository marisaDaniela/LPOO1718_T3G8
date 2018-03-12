package dungeon;
import java.util.Scanner;

public class Game {
	
	public static void printBoard(char[][] board) {
		System.out.println();
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				System.out.print(board[i][j]);
				System.out.print("  ");
			}
			System.out.println();
		}

	}


	public static int[] getPos(char[][] board){
		int[] pos = new int[2];
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				if(board[i][j]=='H' || board[i][j] == 'K')
				{
					pos[0] = i; 
					pos[1] = j;
				}
			}
		}
		return pos;
	}


	public static void checkDirection(char input, int[] pos, char[][]board)
	{
		int x= pos[0];
		int y= pos[1];
		System.out.println(pos[0]);
		System.out.println(pos[1]);


		if(input == 'A' || input == 'a')
		{	
			if(board[x][y-1]==' '){
				board[x][y-1]='H';
				board[x][y]=' ';
			}
			else
				if(atLever(x,y-1, board))
				{
					board[x][y-1]='K';
					board[x][y]=' ';
				}

		}
		if(input == 'S' || input == 's')
		{
			if(board[x+1][y]==' '){
				board[x+1][y]='H';
				board[x][y]=' ';
			}
			else
				if(atLever(x+1,y, board))
				{
					board[x+1][y]='K';
					board[x][y]=' ';
				}

		}
		if(input == 'W' || input == 'w')
		{
			if(board[x-1][y]==' '){
				board[x-1][y]='H';
				board[x][y]=' ';
			}
			else
				if(atLever(x-1,y, board))
				{
					board[x-1][y]='K';
					board[x][y]=' ';
				}

		}
		if(input == 'D' || input == 'd')
		{
			if(board[x][y+1]==' '){
				board[x][y+1]='H';
				board[x][y]=' ';
			}
			else
				if(atLever(y+1,y, board))
				{
					board[x+1][y]='K';
					board[x][y]=' ';
				}


		}

	}
	public static void main(String[] args) {

		char[][] board = new char [][]{
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
	// TODO: lever esta a desaparecer, why? 
	// movimentos do guarda

		while(true)
		{
			Scanner key = new Scanner(System.in);
			char key2 = '1';

			while (true) 
			{
				printBoard(board);
				key2 = key.next().charAt(0);

				if(key2 == 'q' || key2 == 'Q') // Para sair TODO: ver porque esta a dar erro ao pressionar esta tecla
					break;


				int pos[] = getPos(board);
				if(adjacentGuard(pos[0], pos[1], board))
				{
					System.out.println("GAME OVER!");
					return;
				}
				checkDirection(key2, pos, board);
				printBoard(board);

			}
			key.close();

		}

	}

	public static boolean atLever(int x, int y, char[][]board)
	{
		if(board[x][y] == 'k')
			return true;
		else
			return false;
	}
	
	public static boolean adjacentGuard(int x, int y, char[][]board)
	{
		if(board[x][y] == 'H' && board[x+1][y] == 'G')
			return true;
		else if(board[x][y] == 'H' && board[x-1][y] == 'G')
			return true;
		else if(board[x][y] == 'H' && board[x][y+1] == 'G')
			return true;
		else if(board[x][y] == 'H' && board[x][y-1] == 'G')
			return true;
		else
			return false;
	}
	
	

}
