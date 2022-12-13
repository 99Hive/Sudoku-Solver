public interface SudokuSolver {

	boolean solve();

	/*
	 * Empties the grid.
	 */
	void clear();

	boolean validCell(int digit, int row, int col);

	/*
	 * Fills the grid with the digits starting with row 0 and col 0
	 * 
	 * @param row The row
	 * 
	 * @param col The column
	 * 
	 */
	boolean solver(int row, int col);

	/*
	 * Puts digit in the box row, col.
	 * 
	 * @param row The row
	 * 
	 * @param col The column
	 * 
	 * @param digit The digit to insert in box row, col
	 * 
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 * [0..9]
	 */
	void setCell(int row, int col, int digit) throws IllegalArgumentException;

	/*
	 * @param row must be between 1-9 since the sudokuboard is 9x9
	 * 
	 * @param col must be between 1-9 since the sudokuboard is 9x9
	 * 
	 * @throws IllegalArgumentException if any of the parameters are out of range
	 */
	int getCell(int row, int col) throws IllegalAccessError;

	/*
	 * Check if the digit in row r and col c is legal
	 * 
	 * @param row must be between 1-9 since the sudokuboard is 9x9
	 * 
	 * @param col must be between 1-9 since the sudokuboard is 9x9
	 * 
	 */
	boolean legal(int r, int c);

	/*
	 * Returns a matrix with the current values.
	 * 
	 * @return integer matrix with current values
	 */
	int[][] getMatrix();

}
