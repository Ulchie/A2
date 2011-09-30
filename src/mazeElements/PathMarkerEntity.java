/**
 * Used as a marker when printing out the path and the maze together
 */
package mazeElements;

/**
 * @author JKidney
 *
 */
public class PathMarkerEntity extends MazeEntity {

	private int pathStep = -1;
	
	/**
	 * @param me
	 */
	public PathMarkerEntity(PathMarkerEntity pe) 
	{
		super(pe);
		this.pathStep = pe.pathStep;
	}

	/**
	 * @param row
	 * @param col
	 */
	public PathMarkerEntity(int row, int col, int pathStep) {
		super(row, col);
		this.pathStep = pathStep;
	}

	/* (non-Javadoc)
	 * @see display.AsciiEntityDisplay#getDisplayString()
	 */
	@Override
	public String getDisplayString() { return  String.format("[%2d]", pathStep); }

	/* (non-Javadoc)
	 * @see mazeElements.MazeEntity#canMoveOnto()
	 */
	@Override
	public boolean canMoveOnto(){ return false; }

	/* (non-Javadoc)
	 * @see mazeElements.MazeEntity#duplicate()
	 */
	@Override
	public MazeEntity duplicate() { return new PathMarkerEntity(this); }
}
