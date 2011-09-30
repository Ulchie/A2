/**
 * represents a wall area that can not be moved onto
 */
package mazeElements;

/**
 * \author Jordan Kidney
 *
 */
public class WallEntity extends MazeEntity
{
	
	public WallEntity(WallEntity we)
	{
		super(we);
	}
	
	public WallEntity(int row, int col)
	{
		super(row, col);
	}

	/* (non-Javadoc)
	 * @see mazeElements.MazeEntity#duplicate()
	 */
	public MazeEntity duplicate() { return new WallEntity(this); }
	
	/* (non-Javadoc)
	 * @see mazeElements.MazeEntity#canMoveOnto()
	 */
	public boolean canMoveOnto(){return false; }

	/* (non-Javadoc)
	 * @see display.AsciiEntityDisplay#getDisplayString()
	 */
	public String getDisplayString() { return "[##]"; }
}
