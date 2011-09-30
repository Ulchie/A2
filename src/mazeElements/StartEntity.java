/**
 *  represents the start location in the maze
 */
package mazeElements;

/**
 * \author Jordan Kidney
 *
 */
public class StartEntity extends OpenEntity
{
	
	public StartEntity(StartEntity se)
	{ 
		super(se);
	}
	
	public StartEntity(int row, int col)
	{
		super(row,col);
	}

	/* (non-Javadoc)
	 * @see mazeElements.OpenEntity#duplicate()
	 */
	public MazeEntity duplicate() { return new StartEntity(this); }
	
	/* (non-Javadoc)
	 * @see mazeElements.OpenEntity#getDisplayString()
	 */
	public String getDisplayString() { return "[ST]"; }

	/* (non-Javadoc)
	 * @see mazeElements.MazeEntity#isStartLocation()
	 */
	public boolean isStartLocation() { return true; }
}
