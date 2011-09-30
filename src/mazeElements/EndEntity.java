/**
 *  represents the end spot to be found by the maze solver
 */
package mazeElements;

/**
 * \author Jordan Kidney
 *
 */
public class EndEntity extends OpenEntity
{
	public EndEntity(EndEntity ee)
	{ 
		super(ee);
	}
	
	public EndEntity(int row, int col)
	{
		super(row,col);
	}

	/* (non-Javadoc)
	 * @see mazeElements.OpenEntity#duplicate()
	 */
	public MazeEntity duplicate() { return new EndEntity(this); }
	/* (non-Javadoc)
	 * @see mazeElements.OpenEntity#getDisplayString()
	 */
	public String getDisplayString() { return "[EN]"; }
	
	/* (non-Javadoc)
	 * @see mazeElements.MazeEntity#isEndLocation()
	 */
	public boolean isEndLocation() { return true; }
}
