
public class Position {

	// The indices of the position
	private int row, col;

	/**
	 * Create an Position.
	 * 
	 * @param row The row of the position.
	 * @param col The width of the position.
	 */
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Place an row and a column to a position.
	 * 
	 * @param row The row of the position.
	 * @param col The column of the position.
	 */
	public void setPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Place an position to a position.
	 * 
	 * @param position The position to be copied to a different position.
	 */
	public void setPosition(Position position) {
		this.row = position.getRow();
		this.col = position.getCol();
	}

	/**
	 * @return The row of the position.
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * @return The col of the position.
	 */
	public int getCol() {
		return this.col;
	}
}
