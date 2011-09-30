/**
 * Base abstract class to set up common functions required for building a maze
 */
package mazeBuild;

import java.util.Random;

import mazeElements.Maze;
import mazeElements.MazeEntity;


/**
 * Base abstract class to set up common functions required for building a maze
 * @author JKidney
 */
/**
 * @author JKidney
 *
 */
public abstract class MazeBuilder 
{
	private int numRows;
	private int numCols;
	protected Maze m = null;
	
	public MazeBuilder()
	{
		numRows = numCols = 0;
	}
	
	/**
	 * Set up a blank maze of a specific size
	 * @param numRows number of rows in the maze
	 * @param numCols number of columns in the maze
	 */
	public MazeBuilder(int numRows, int numCols) 
	{
		super();
		setSize(numRows, numCols);
	}

	
	/**
	 * Set up a blank maze of a specific size
	 * @param numRows number of rows in the maze
	 * @param numCols number of columns in the maze
	 */
	public void setSize(int numRows, int numCols)
	{
		this.numRows = numRows;
		this.numCols = numCols;
		m = new Maze(numRows, numCols);
	}
		
	public int getNumRows() { return numRows; }
    public int getNumCols() { return numCols; }

    
    /**
     * Finds a random open location to place an element in the maze
     * Note: if maze is full then this methods will turn into an infinite 
     * loop.
     * @param et the element to place in the  maze
     */
    protected void setRandomLocation_findOpen(MazeEntity et)
	   {
    	   Random r = new Random();
		   boolean exit = false;
		   int row =0;
		   int col = 0;

			 do {
			     row = r.nextInt(m.numRows());	
				 col = r.nextInt(m.numCols());
				 
				 if(m.getEntity(row,col) == null)
				 {
					 m.setEntity(row, col, et);
					 exit = true;
				 }
			    }
			 while(!exit);
	   }
	 
    
	/**
	 * builds and returns a complete maze
	 * @return
	 */
	public abstract Maze build();
}
