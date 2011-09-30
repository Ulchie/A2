/**
 * Builds a random maze
 */
package mazeBuild;

import java.util.Random;

import mazeElements.*;


/**
 * Builds a random maze
 * @author JKidney
 *
 */
public class RandomMazeBuilder extends MazeBuilder 
{
	private int wallFactor;
	
	/**
	 * @param numRows number of rows in the maze
	 * @param numCols number of column in the maze
	 * @param wallFactor factur used to determin frequency placement of wall objects
	 */
	public RandomMazeBuilder(int numRows, int numCols, int wallFactor) 
	{
		super(numRows, numCols);
		this.wallFactor = wallFactor % 100;
	}

	/**
	 *  main method called to build the random maze
	 */
	private void randomCreateMaze()
	   {
		 Random r = new Random();
		
		 StartEntity startLoc = new StartEntity(0,0);
		 EndEntity endLoc = new EndEntity(0,0);
		 
		 setRandomLocation_findOpen(startLoc);
		 setRandomLocation_findOpen(endLoc);
		 
		 m.setStartLoc(startLoc);
		 m.setEndLoc(endLoc);
		 
		 for(int i=0; i < m.numRows(); i++)
			 for(int j=0; j < m.numCols(); j++)
			 {
				 if(m.getEntity(i,j) != null) continue;
				 
				 if( (r.nextInt(100) % wallFactor) == 0)
					 m.setEntity(i,j, new WallEntity(i,j));
				 else
					 m.setEntity(i,j,new OpenEntity(i,j)); 
			 }
	   }
	
	/* (non-Javadoc)
	 * @see Build.MazeBuilder#build()
	 */
	public Maze build() 
	{
		randomCreateMaze();
		return m;
	}
}
