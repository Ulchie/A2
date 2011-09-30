/**
 * A maze solver that uses a stack
 */
package mazeSolve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import mazeElements.*;

/**
 * @author JKidney
 *
 */
public class StackMazePathFinder extends MazePathFinder {

	/**
	 * @param myMaze
	 */
	public StackMazePathFinder(Maze myMaze) 
	{
		super(myMaze);
	}

	
	/* (non-Javadoc)
	 * @see mazeSolve.MazeSolver#findPath()
	 */
	@Override
	public MazePath findPath()
	{
	    MazePath foundPath = new MazePath();
	    MazeEntity startLocation = myMaze.getStartLoc();
	    MazeEntity endLocation = myMaze.getEndLoc();
	   
	  
	    //Below is demo code that shows step for getting open locations
	    //around the start location followed by adding the start to the found path plus one
	    // step. Replace this code with your stack maze solver
	    
	    ArrayList<MazeEntity> openPaths = myMaze.getOpenLocationsAround(startLocation);
	    MazeEntity step = openPaths.get(0);
	    
	    foundPath.add(startLocation);
	    foundPath.add(step);
	    
	    //Note: The path should have the following format
	    //     index 0 = start location
	    //     index 1 = next step after the start
	    //       .
	    //       .
	    //      index n = the end location   
		return foundPath;
	}

}
	 