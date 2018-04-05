package dkeep.test;

import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.Coordinates;
import dkeep.logic.Hero;
import dkeep.logic.Character;


public class TestDungeonGameLogic {

	char map[][] = {
			{'X','X','X','X','X'},
			{'X','H',' ','G','X'},
			{'I',' ',' ',' ','X'},
			{'X','k',' ',' ','X'},
			{'X','X','X','X','X'},
	};

	@Test
	public void testMoveToFreeCell(){
		Coordinates heroPosition = Character.getPos(map, 'H', 5);

		assertEquals(heroPosition.getX(), 1);
		assertEquals(heroPosition.getY(), 1);

		// Para baixo
		Character.checkDirection('S', heroPosition, map, 'H');

		heroPosition = Character.getPos(map, 'H', 5);

		assertEquals(heroPosition.getX(), 2);
		assertEquals(heroPosition.getY(), 1);

		// Para direita
		Character.checkDirection('D', heroPosition, map, 'H');

		assertEquals(heroPosition.getX(), 2); // fica com a posicao anterior
		assertEquals(heroPosition.getY(), 1);

		heroPosition = Character.getPos(map, 'H', 5);

		assertEquals(heroPosition.getX(), 2);
		assertEquals(heroPosition.getY(), 2);
	}

	@Test
	public void testMoveIntoWall(){
		Coordinates heroPosition = Character.getPos(map, 'H', 5);

		int xi = heroPosition.getX();
		int yi = heroPosition.getY();

		assertEquals(heroPosition.getX(), 1);
		assertEquals(heroPosition.getY(), 1);

		// Para esquerda
		Character.checkDirection('A', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);

		assertEquals(heroPosition.getX(), xi);
		assertEquals(heroPosition.getY(), yi);

		// Para cima
		Character.checkDirection('W', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);

		assertEquals(heroPosition.getX(), xi);
		assertEquals(heroPosition.getY(), yi);

	}

	@Test
	public void testIsCapturedByGuard(){
		Coordinates heroPosition = Character.getPos(map, 'H', 5);
		assertEquals(false,Hero.isAdjacent(heroPosition, map, 'G'));

		// Se andar para baixo nao fica ao pe do guarda
		Character.checkDirection('S', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);
		assertEquals(false,Hero.isAdjacent(heroPosition, map, 'G'));

		Character.checkDirection('D', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);
		assertEquals(false,Hero.isAdjacent(heroPosition, map, 'G'));

		Character.checkDirection('W', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);
		assertEquals(true,Hero.isAdjacent(heroPosition, map, 'G'));

	}

	@Test
	public void testExitClosedDoors(){
		Coordinates heroPosition = Character.getPos(map, 'H', 5);
		Coordinates door = Character.getPos(map, 'I', 5);

		assertEquals(door.getX(), 2);
		assertEquals(door.getY(), 0);

		//Anda para baixo
		Character.checkDirection('S', heroPosition, map, 'H');
		Coordinates posActual = Character.getPos(map, 'H', 5);

		Character.checkDirection('A', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);

		// manteve a sua posicao
		assertEquals(heroPosition.getX(), posActual.getX());
		assertEquals(heroPosition.getY(), posActual.getY());		
	}

//
//	@Test // TODO:
//	public void testMoveToLeverOpenDoors(){
//		Coordinates heroPosition = Character.getPos(map, 'H', 5);
//		Coordinates door = Character.getPos(map, 'I', 5);
//		System.out.println(door.getX());
//		System.out.println(door.getY());
//		boolean doorOpen = false;
//		assertEquals(door.getX(), 2);
//		assertEquals(door.getY(), 0);
//		
//		Character.checkDirection('S', heroPosition, map, 'H');
//		heroPosition = Character.getPos(map, 'H', 5);
//		Character.checkDirection('T', heroPosition, map, 'H');
//		
//		if(map[0][2] == 'S') {
//			doorOpen = true;
//		}
//		assertEquals(true, doorOpen);
//	}
//	
//		@Test
//		public void testMoveToNextLevel(){
//	
//		}

}