import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class SudokuGUI {
	private JFrame frame = new JFrame("Sudoku solver");
	private JTextField grid[][];
	private JButton solveButton;
	private JButton clearButton;
	private JButton exitButton;
	private SudokuSolver sr;

	/*
	 * Create a new 9*9 textField
	 * 
	 * implement the SukdokuReader
	 * 
	 * class initialize the GUI
	 */
	public SudokuGUI() {
		grid = new JTextField[9][9];
		sr = new SudokuReader();
		setGUI();
	}

	/**
	 * Making a grid with random colors
	 * 
	 * Adjust the squares sizes with right values
	 * 
	 * Making Clear and Solve buttons
	 * 
	 * Creating the frame and put all the things in it
	 */
	public void setGUI() {

		Random rand = new Random();
		Color[] col = new Color[9];
		for (int i = 0; i < 9; i++) {
			int randColNbr = rand.nextInt(150, 210);
			Color randCol = Color.getHSBColor(randColNbr, randColNbr, randColNbr);
			col[i] = randCol;
		}

		frame = new JFrame("SUDOKU SOLVER");

		// hur rutorna och texten i de ser ut
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grid[i][j] = new JTextField();
				grid[i][j].setBounds(60 + j * 40, 40 + i * 40, 40, 40);
				grid[i][j].setBackground(col[(i / 3) * 3 + (j / 3)]);
				grid[i][j].setBorder(new LineBorder(Color.black, 1));
				grid[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				grid[i][j].setText("");
				Font textFont = new Font("SansSerif", Font.ITALIC, 30);
				grid[i][j].setFont(textFont);
				frame.add(grid[i][j]);
			}

			/*
			 * Lägger till solveknappen & clearknappen
			 */
			clearButton = new JButton("CLEAR");

			clearButton.setBounds(80, 450, 80, 80);
			clearButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					sr.clear();
					for (int i = 0; i < 9; i++) {
						for (int k = 0; k < 9; k++) {

							grid[i][k].setText(null);
							grid[i][k].setEditable(true);

						}
					}
				}
			});

			exitButton = new JButton("EXIT");
			exitButton.setBounds(200, 450, 80, 80);
			exitButton.addActionListener((event) -> System.exit(0));

			solveButton = new JButton("SOLVE");
			solveButton.setBounds(330, 450, 80, 80);
			solveButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean shouldSolve = true;

					for (int i = 0; i < 9; i++) {
						for (int k = 0; k < 9; k++) {
							int value = 0;

							if (grid[i][k].getText().isEmpty()) {
								value = 0;
								sr.setCell(i, k, value);
							} else if (isInputValid()) {
								value = Integer.parseInt(grid[i][k].getText());
								sr.setCell(i, k, value);
							} else {
								shouldSolve = false;
								JOptionPane.showMessageDialog(frame, "Illegal character.");
								grid[i][k].setEditable(true);
								break;
							}
						}
					}

					if (sr.solve() && shouldSolve == true) {

						for (int i = 0; i < 9; i++) {
							for (int k = 0; k < 9; k++) {
								grid[i][k].setText(Integer.toString(sr.getCell(i, k)));
								grid[i][k].setEditable(false);

							}
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Couldn't find a solution.");

					}
				}
			});
			frame.add(solveButton);
			frame.add(clearButton);
			frame.add(exitButton);

		}

		frame.setSize(500, 600);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocation(300, 100);

	}

	/**
	 * Checks if the inputs are valid
	 * 
	 * The allowed values are 0-9
	 */
	public boolean isInputValid() {
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (!grid[x][y].getText().equals("")) {
					try {
						int digit = Integer.parseInt(grid[x][y].getText());
						if (digit <= 0 || digit >= 10)
							return false;
					} catch (NumberFormatException e) {
						return false;
					}
				}
			}
		}
		return true;
	}

}