/**
 * represents an open area that can be moved onto in the maze
 */
package mazeElements;

/**
 * \author Jordan Kidney
 *
 */
public class OpenEntity extends MazeEntity
{	
	
	public OpenEntity(OpenEntity oe)
	{
		super(oe);
	}
	
	public OpenEntity(int row, int col)
	{
		super(row,col);
	}

	/* (non-Javadoc)
	 * @see mazeElements.MazeEntity#duplicate()
	 */
	public MazeEntity duplicate() { return new OpenEntity(this); }

	/* (non-Javadoc)
	 * @see mazeElements.MazeEntity#canMoveOnto()
	 */
	public boolean canMoveOnto(){ return true; }

	/* (non-Javadoc)
	 * @see display.AsciiEntityDisplay#getDisplayString()
	 */
	public String getDisplayString() 
	{
		return "[  ]";
    }
}
