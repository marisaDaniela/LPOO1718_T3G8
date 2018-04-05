package dkeep.gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

import dkeep.logic.Coordinates;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;

public class ConstructGameGUI extends JPanel implements MouseListener, MouseMotionListener {
	private static final int HEIGHT = 660;
	private static final int WIDTH = 640;
	private JLabel warnings;
	private JLabel mazeSide;
	private JTextField fldMazeSize;
	private char iconSelected = ' ';
	private char map[][] = null;

	
	private Coordinates position = new Coordinates();
	private int sizeToDraw = 9;

	private JFrame frame;
	private JTextField textField;
	/**
	 * @wbp.nonvisual location=236,1
	 */
	private final JButton button = new JButton("New button");
	
	/**
	 * Create the application.
	 */
	public ConstructGameGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setEnabled(false);
		frame.setBounds(88, 75, 706, 411);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(this);
		frame.pack();
		this.frame.setVisible(true);

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		setLayout(null);

		warnings = new JLabel("");
		warnings.setForeground(Color.RED);
		warnings.setBounds(10, 546, 364, 14);
		frame.getContentPane().add(warnings);

		mazeSide = new JLabel("Maze Side");
		mazeSide.setBounds(20, 11, 131, 21);
		frame.getContentPane().add(mazeSide);

		fldMazeSize = new JTextField();
		fldMazeSize.setText("11");
		fldMazeSize.setBounds(123, 11, 86, 21);
		frame.getContentPane().add(fldMazeSize);
		fldMazeSize.setColumns(10);

		warnings = new JLabel("");
		warnings.setForeground(Color.RED);
		warnings.setBounds(10, 546, 364, 14);
		frame.getContentPane().add(warnings);

		mazeSide = new JLabel("Maze Side");
		mazeSide.setBounds(20, 11, 131, 21);
		frame.getContentPane().add(mazeSide);

		fldMazeSize = new JTextField();
		fldMazeSize.setText("11");
		fldMazeSize.setBounds(123, 11, 86, 21);
		frame.getContentPane().add(fldMazeSize);
		fldMazeSize.setColumns(10);



		JLabel lblNewLabel = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		textField = new JTextField();
		frame.getContentPane().add(textField, BorderLayout.EAST);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("New label");
		frame.getContentPane().add(lblNewLabel_1, BorderLayout.WEST);

		JComboBox comboBox = new JComboBox();
		frame.getContentPane().add(comboBox, BorderLayout.CENTER);

		JButton btnNewButton = new JButton("New game");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// TODO: complete this action
			}
		});
		btnNewButton.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		btnNewButton.setBounds(475, 565, 130, 29);
		frame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);


		JButton btnConstEmptyMaze = new JButton("Empty Maze");
		btnConstEmptyMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: complete this action;			
			}
		});
		btnConstEmptyMaze.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		btnConstEmptyMaze.setBounds(475, 535, 130, 29);
		frame.getContentPane().add(btnConstEmptyMaze);
	}


	@Override
	public void mousePressed(MouseEvent e) {
		warnings.setText("");
		int xMouse = e.getX();
		int yMouse = e.getY();
		iconSelect(xMouse, yMouse); 
		putSelectItemMap(xMouse, yMouse);
		repaint();
	}


	public void putSelectItemMap(int xMouse, int yMouse) {
		position.setX(120); 
		position.setY(16);
		int i = (int) ((double) (yMouse - position.getY())) / sizeToDraw;
		int j = (int) ((double)(xMouse - position.getX()))/ sizeToDraw;
		if (i >= 0 && i < map.length && j >= 0 && j < map.length)
			if (iconSelected == 'N')
				warnings.setText("Nao selecionou nenhum icone!");
			else
				map[i][j] = iconSelected;
	}


	public void iconSelect(int xMouse, int yMouse) {
		if (xMouse >= 5 && xMouse <= 54)
			if (yMouse >= 16 && yMouse <= 65)
				iconSelected = 'H'; 
			else if (yMouse >= 66 && yMouse <= 115)
				iconSelected = 'O'; 
			else if (yMouse >= 116 && yMouse <= 165)
				iconSelected = 'k'; 
			else if (yMouse >= 166 && yMouse <= 215)
				iconSelected = 'I'; 
			else if (yMouse >= 216 && yMouse <= 265)
				iconSelected = ' '; 
			else if (yMouse >= 266 && yMouse <= 315)
				iconSelected = 'X';
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
