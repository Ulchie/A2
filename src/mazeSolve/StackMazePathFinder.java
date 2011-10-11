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
	    MazeEntity nextChoice = null;
	    Stack<StackElement> stackPath = new Stack<StackElement>();
	    Stack<MazeEntity> flipStack = new Stack<MazeEntity>();
	    StackElement workingItem;
	    
	    stackPath.add(new StackElement(startLocation, myMaze));
	    visited.add(startLocation);

	    while (stackPath.size() > 0 &&  !stackPath.lastElement().returnLocation().isEndLocation())
	    {
	    	nextChoice = stackPath.lastElement().nextStep(visited);
	    	
	    	if (nextChoice == null)
	    	{
	    		workingItem = stackPath.pop();
	    		visited.add(workingItem.returnLocation());
	    	}
	    	else
	    		stackPath.add(new StackElement(nextChoice, myMaze));
	    }
	    
	    //System.out.println(stackPath); //testing output
	    
	    //reverse the stack, and then pop it onto the foundPath in correct order
	    while (stackPath.size() != 0)
	    	flipStack.push(stackPath.pop().returnLocation()); //simultaneously reverse and pare down to only dealing with MazeEntity's
	    																
	    while (flipStack.size() != 0)
	    	foundPath.add(flipStack.pop());
	    
	    optimizePath(foundPath); //remove elements from path that are redundant
		return foundPath;
	}
	
	/**
	 * Optimizes the path by tracing back through the stack and eliminating unnecessary
	 * steps. It does so by finding the lowest index'ed element that is a part of the 
	 * open locations surrounding the element being currently evaluated.
	 * 
	 * @param foundPath
	 */
	private void optimizePath(MazePath foundPath)
	{
		ArrayList<MazeEntity> currentOpenLocs; //will store the open locations for each element evaluated
		int lowestIndexFound = -1; //will hold the index of the lowest element found within the open locs
								   //that is also a part of the path.
		int index = foundPath.size() - 1; //get last index in the path found
		
		for (; index > 0; index--) //start at the end of the path and work our way backwards
		{
			currentOpenLocs = myMaze.getOpenLocationsAround(foundPath.get(index)); //get open locs around element in path
			
			for (int i = 0; i < foundPath.size() && lowestIndexFound == -1; i++) //start from the beginning of the path
				if (currentOpenLocs.contains(foundPath.get(i))) //if the current open locs contains our element i, set our index
					lowestIndexFound = i;
			
			if (lowestIndexFound != -1) //if we found an element...we are guaranteed to as we have a connected path but this is for safety
			{
				while (foundPath.get(lowestIndexFound + 1) != foundPath.get(index)) //remove every element in between lowest index and current index
				{
					foundPath.remove(foundPath.get(lowestIndexFound + 1));
					index--; //we need to adjust this as removing an element results in also moving index as we work from end of list
				}
				lowestIndexFound = -1; //reset our lowestIndexFound to ensure proper setting every time
			}
		}		
	}
}
































	 