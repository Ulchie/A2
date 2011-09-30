/**
 * Builds a maze based upon a file input
 */
package mazeBuild;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import mazeElements.EndEntity;
import mazeElements.Maze;
import mazeElements.MazeEntity;
import mazeElements.OpenEntity;
import mazeElements.StartEntity;
import mazeElements.WallEntity;


/**
 * Builds a maze based upon a file input
 * @author JKidney
 *
 */
public class FileMazeBuilder extends MazeBuilder 
{
	private Scanner input = null;
	
	/**
	 * @param filename file to open
	 * @throws FileNotFoundException if file could not be found
	 */
	public FileMazeBuilder(String filename) throws FileNotFoundException
	{
	   input = new Scanner( new File(filename) );
	}

	/* (non-Javadoc)
	 * @see Build.MazeBuilder#build()
	 */
	@Override
	public Maze build() 
	{
		int numRows = input.nextInt();
		int numCols = input.nextInt();
		input.nextLine(); //remove end of line character
		
	    setSize(numRows, numCols);
	    
		for(int i=0; i < m.numRows(); i++)
		{
			String[] cols = input.nextLine().split(",");
			for(int j=0; j < cols.length; j++)
			{
				MazeEntity me = null;
				
				if(cols[j].compareTo("[  ]") == 0) // open space
					me = new OpenEntity(i, j);
				else if(cols[j].compareTo("[##]") == 0) // wall
					me = new WallEntity(i, j);
				else if(cols[j].compareTo("[ST]") == 0) // start
				{
					me = new StartEntity(i, j);
					m.setStartLoc((StartEntity)me);
				}
				else if(cols[j].compareTo("[EN]") == 0) // end
				{
					me = new EndEntity(i, j);
					m.setEndLoc((EndEntity)me);
				}
				
				m.setEntity(i, j, me); // add element to the maze
			}
		}
	    
		input.close();
		return m;
	}

}
