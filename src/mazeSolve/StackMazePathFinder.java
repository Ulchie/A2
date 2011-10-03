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
		ArrayList<MazeEntity> visited = new ArrayList<MazeEntity>(0);
	    MazeEntity startLocation = myMaze.getStartLoc();
	    MazeEntity endLocation = myMaze.getEndLoc();
	    MazeEntity nextChoice = null;
	    Stack<StackElement> stackPath = new Stack<StackElement>();
	     
	    stackPath.add(new StackElement(startLocation, myMaze));
	    foundPath.add(startLocation);
	    visited.add(startLocation);

	    while (!foundPath.get(foundPath.size() - 1).isEndLocation() && foundPath.size() > 0)
	    {
	    	nextChoice = stackPath.get(stackPath.size() - 1).nextStep(visited);
	    	
	    	if (nextChoice == null)
	    	{
	    		foundPath.removeLast();
	    		stackPath.remove(stackPath.size() - 1);
	    	}
	    	else
	    	{
	    		foundPath.add(nextChoice);
	    		stackPath.add(new StackElement(nextChoice, myMaze));
	    	}
	    }
	    /*
	    //Below is demo code that shows step for getting open locations
	    //around the start location followed by adding the start to the found path plus one
	    // step. Replace this code with your stack maze solver
	    
	    ArrayList<MazeEntity> openPaths = myMaze.getOpenLocationsAround(startLocation);
	    MazeEntity step = openPaths.get(0);
	    
	    foundPath.add(startLocation);
	    foundPath.add(step);
	    */
	    //Note: The path should have the following format
	    //     index 0 = start location
	    //     index 1 = next step after the start
	    //       .
	    //       .
	    //      index n = the end location   
	    
	    
		return foundPath;
	}

}
	 