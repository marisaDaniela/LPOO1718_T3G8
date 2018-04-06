package dkeep.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dkeep.cli.Game;
import dkeep.logic.Character;
import dkeep.logic.Club;
import dkeep.logic.Coordinates;
import dkeep.logic.Hero;
import dkeep.logic.Level;
import dkeep.logic.Level2;
import dkeep.logic.Ogre;

public class TestKeepGameLogic {

	char map[][] = {
			{'X','X','X','X','X'},
			{'X','H',' ','O','X'},
			{'I',' ',' ',' ','X'},
			{'X','k',' ',' ','X'},
			{'X','X','X','X','X'},
	};

	@Test
	public void testIsKilledByOgre() {
		Coordinates heroPosition = Character.getPos(map, 'H', 5);
		assertEquals(false,Hero.isAdjacent(heroPosition, map, 'O'));

		// Se andar para baixo nao fica ao pe do ogre
		Character.checkDirection('S', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);
		assertEquals(false,Hero.isAdjacent(heroPosition, map, 'O'));

		Character.checkDirection('D', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);
		assertEquals(false,Hero.isAdjacent(heroPosition, map, 'O'));

		Character.checkDirection('W', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);
		assertEquals(true,Hero.isAdjacent(heroPosition, map, 'O'));
	}

	@Test
	public void testHeroCatchKey() {
		Coordinates heroPosition = Character.getPos(map, 'H', 5);
		Coordinates key = Character.getPos(map, 'k', 5);

		assertEquals(key.getX(), 3);
		assertEquals(key.getY(), 1);

		Character.checkDirection('S', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);

		// catch the key with the letter c
		Character.checkDirection('c', heroPosition, map, 'H');
		assertEquals(Game.hero, 'K');
		assertEquals(map[3][1], ' ');
	}

	@Test
	public void testCantWithoutKey() {
		Coordinates heroPosition = Character.getPos(map, 'H', 5);
		Coordinates door = Character.getPos(map, 'I', 5);

		assertEquals(heroPosition.getX(), 1);
		assertEquals(heroPosition.getY(), 1);

		Character.checkDirection('S', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);

		Character.checkDirection('A', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);

		assertEquals(map[door.getX()][door.getY()], 'I');
	}

	@Test
	public void testHeroLeaves() {
		Game.game_flag=2;
		Coordinates heroPosition = Character.getPos(map, 'H', 5);
		Coordinates key = Character.getPos(map, 'k', 5);

		assertEquals(key.getX(), 3);
		assertEquals(key.getY(), 1);

		Character.checkDirection('S', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);

		// catch the key with the letter c
		Character.checkDirection('c', heroPosition, map, 'H');
		assertEquals(map[3][1], ' ');
		heroPosition = Character.getPos(map, 'K', 5);

		assertEquals(heroPosition.getX(), 2);
		assertEquals(heroPosition.getY(), 1);

		Character.checkDirection('a', heroPosition, map, 'K');
		heroPosition = Character.getPos(map, 'K', 5);
		Character.checkDirection('a', heroPosition, map, 'K');

		assertEquals(map[2][0], 'S');
	}

	@Test
	public void testWinGame() {
		Game.game_flag=2;
		Coordinates heroPosition = Character.getPos(map, 'H', 5);
		Coordinates key = Character.getPos(map, 'k', 5);

		assertEquals(key.getX(), 3);
		assertEquals(key.getY(), 1);

		Character.checkDirection('S', heroPosition, map, 'H');
		heroPosition = Character.getPos(map, 'H', 5);

		// catch the key with the letter c
		Character.checkDirection('c', heroPosition, map, 'H');
		assertEquals(map[3][1], ' ');
		heroPosition = Character.getPos(map, 'K', 5);

		assertEquals(heroPosition.getX(), 2);
		assertEquals(heroPosition.getY(), 1);

		Character.checkDirection('a', heroPosition, map, 'K');
		heroPosition = Character.getPos(map, 'K', 5);
		Character.checkDirection('a', heroPosition, map, 'K');

		assertEquals(map[2][0], 'S');
		assertTrue(Game.WIN);

	}

}
