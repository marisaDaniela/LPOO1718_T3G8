//package dkeep.gui;
//
//import java.awt.EventQueue;
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.JFrame;
//
//import dkeep.logic.Coordinates;
//
//public class ImagesGUI {
//	private static BufferedImage hero;
//	private static BufferedImage heroArmed;
//	private static BufferedImage heroWithKey;
//	private static BufferedImage heroWinner;
//	private static BufferedImage heroLoser;
//	private static BufferedImage guard;
//	private static BufferedImage guardSleeping;
//	private static BufferedImage ogre;
//	private static BufferedImage club;
//	private static BufferedImage doorOpen;
//	private static BufferedImage doorClosed;
//	private static BufferedImage path;
//	private static BufferedImage wall;
//	private static BufferedImage ogreAtKey;
//	private static BufferedImage key;
//	private static BufferedImage clubAtKey;
//
//	public ImagesGUI() {
//		if (hero == null)
//			try {
//
//				hero = ImageIO.read(new File("images/mazeRunner.png"));
//				heroArmed = ImageIO.read(new File("images/mazeRunner.png"));
//				heroWinner = ImageIO.read(new File("images/mazeRunner.png"));
//				heroLoser = ImageIO.read(new File("images/mazeRunner.png"));
//				heroWithKey = ImageIO.read(new File("images/mazeRunner.png"));
//
//				guard = ImageIO.read(new File("images/mazeRunner.png"));
//				guardSleeping = ImageIO.read(new File("images/mazeRunner.png"));
//
//				ogre = ImageIO.read(new File("images/mazeRunner.png"));
//				ogreAtKey = ImageIO.read(new File("images/mazeRunner.png"));
//
//				club = ImageIO.read(new File("images/club2.png"));
//				clubAtKey = ImageIO.read(new File("images/mazeRunner.png"));
//
//				doorOpen = ImageIO.read(new File("images/mazeRunner.png"));
//				doorClosed = ImageIO.read(new File("images/mazeRunner.png"));
//
//				path = ImageIO.read(new File("images/mazeRunner.png"));
//				wall = ImageIO.read(new File("images/mazeRunner.png"));
//				key = ImageIO.read(new File("images/mazeRunner.png"));
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	}
//
//	public BufferedImage getImage(char c) {
//
//		switch (c) {
//
//		case ' ':
//			return path;
//		case 'g':
//			return guardSleeping;
//		case 'G':
//			return guard;
//		case 'H':
//			return hero;
//		case 'K':
//			return heroWithKey;
//		case 'k':
//			return key;
//		case 'O':
//			return ogre;
//		case '*':
//			return club;
//		case '$':
//			return ogreAtKey;
//		case '%':
//			return clubAtKey;
//		case 'X':
//			return wall;
//		case 'I':
//			return doorClosed;
//		case 'S':
//			return doorOpen;
//		case 'A':
//			return heroArmed;
//		case 'W':
//			return heroWinner;
//		case 'L':
//			return heroLoser;
//		default:
//			return null;
//
//		}
//	}
//
//	public void drawElementOfGame(Graphics g, char c, int squareToDraw, Coordinates coor) {
//		BufferedImage image = getImage(c);
//		g.drawImage(image, coor.getX(), coor.getY(), coor.getX() + squareToDraw - 1, coor.getY() + squareToDraw - 1, 0, 0, image.getWidth(), image.getHeight(),
//				null);
//	}
//
//}
