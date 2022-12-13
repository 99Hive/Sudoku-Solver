import javax.swing.JOptionPane;

public class SudokuReader implements SudokuSolver {

	/** Creating a sudoku grid. */
	private int[][] board;

	/** Deciding the size of the grid */
	public SudokuReader() {
		board = new int[9][9];
	}

	public SudokuReader(int board[][]) {
		this.board = board;
	}

	@Override
	/**
	 * Methode for checking double numbers if double numbers doesn't exist, start
	 * solving with the grid [0][0]
	 * 
	 * @return false if double number exists
	 */
	public boolean solve() {

		boolean shouldSolve = true;
		int count = 0;
		if (shouldSolve) {
			for (int p = 0; p < 9; p++) {
				for (int j = 0; j < 9; j++) {
					if (!(board[p][j] == (0))) {
						if (!legal(p, j)) {
							count++;
						}
					}
				}
			}
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "Double numbers");
				shouldSolve = true;
				return false;
			}
		}
		return solver(0, 0);
	}

	/**
	 * @param row must be between 1-9 since the sudokuboard is 9x9
	 * @param col must be between 1-9 since the sudokuboard is 9x9 When the row
	 *            reach 8 and the col 9 the program will @return true which means
	 *            that the whole grid is solved. When the col reach 9 means that the
	 *            row is finished and we must move to the second one.
	 */
	@Override
	public boolean solver(int row, int col) {

		if (row == 8 && col == 9) {
			return true;
		}

		if (col == 9) {
			col = 0;
			row++;
		}
		if (board[row][col] != 0) {
			return solver(row, col + 1);
		}

		/*
		 * @returen true when the value is available in the cell
		 * 
		 * @returen false when the value is unavailable in the cell
		 */
		for (int i = 1; i <= 9; i++) {

			if (validCell(row, col, i)) {
				board[row][col] = i;
				if (solver(row, col + 1)) {
					return true;
				} else {

					board[row][col] = 0;
				}
			}
		}
		return false;

	}

	/*
	 * @return true when the numbers which are written before clicking solve isn't
	 * duplicated in the same row, column or the 3x3 square.
	 * 
	 * @return false otherwise
	 */
	@Override
	public boolean legal(int r, int c) {
		for (int i = 0; i < 9; i++) {
			if (i != c) {

				if (board[r][i] == board[r][c]) {
					return false;
				}
			}
			if (i != r) {
				if (board[i][c] == board[r][c]) {
					return false;
				}
			}
		}

		int rr = 3 * (r / 3);
		int cc = 3 * (c / 3);
		for (int rad = rr; rad <= rr + 2; rad++) {
			for (int kol = cc; kol <= cc + 2; kol++) {
				if (board[rad][kol] == board[r][c] && rad != r && kol != c) {
					return false;
				}
			}

		}
		return true;
	}

	@Override
	/*
	 * @return true when the programe chose a valid number for the grid
	 * 
	 * @rteurn false when the programe chose a duplicate number whether in the row ,
	 * column or the 3x3 square.
	 */
	public boolean validCell(int row, int col, int value) {

		for (int i = 0; i < 9; i++) {
			if ((value == board[row][i] && i != col) || (value == board[i][col] && i != row)) { // kollar sÃ¥ den inte
				return false;
			}

			int smallRowStart = (row / 3) * 3;
			int smallColStart = (col / 3) * 3;

			for (int r = smallRowStart; r < smallRowStart + 3; r++) {
				for (int c = smallColStart; c < smallColStart + 3; c++) {
					if (board[r][c] == value) {

						return false;
					}
				}
			}
		}
		return true;

	}

	@Override
	public void setCell(int row, int col, int val) {
		board[row][col] = val;

	}

	@Override

	/* @returen the value in the cell with the given parameters */
	public int getCell(int row, int col) {
		return board[row][col];

	}

	@Override

	/* Clear the grid. */
	public void clear() {
		for (int i = 0; i <= 8; i++) {
			for (int k = 0; k <= 8; k++) {
				setCell(i, k, 0);
			}
		}
	}

	/* Get a copy of the grid. */
	@Override
	public int[][] getMatrix() {
		return board.clone();
	}

	/* Initializing the GUI. */
	public static void main(String[] args) {
		SudokuGUI gui = new SudokuGUI();
	}

}