package dkeep.logic;

public class GameState {

	public static void printBoard(char[][] board, int size) {
		System.out.println();
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				System.out.print(board[i][j]);
				System.out.print("  ");
			}
			System.out.println();
		}

	}
	
}
