/**
 * A maze solver that uses a stack
 */
package mazeSolve;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import mazeElements.*;

/**
 * @author Ryan Ulch
 * @version Final, due date Wednesday, October 12/11
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
		MazePath foundPath = new MazePath(); //will hold our final path that we return
		ArrayList<MazeEntity> visited = new ArrayList<MazeEntity>(0); //holds a visited list to ensure we don't go to locations twice
	    MazeEntity startLocation = myMaze.getStartLoc(); //holds the starting location of the maze
	    MazeEntity nextChoice = null; //our choice holder for where to go next at each point in the maze
	    Stack<StackElement> stackPath = new Stack<StackElement>(); //primary Stack we will work with using StackElement elements
	    Stack<MazeEntity> reverseStack = new Stack<MazeEntity>(); //reverse stack used to pop elements in correct order to foundPath
	    
	    stackPath.add(new StackElement(startLocation, myMaze));//initialize our stack to contain the starting location
	    visited.add(startLocation); //add the start to the visited list as well

	    //while we have a non-empty stack and the last element in the stack isn't the end location, keep looping
	    while (stackPath.size() > 0 &&  !stackPath.lastElement().returnLocation().isEndLocation())
	    {
	    	nextChoice = stackPath.lastElement().nextStep(visited); //grabs the next step from the last element in the stack
	    	
	    	if (nextChoice == null) //if next step is null, we know we have no steps left to take from last element, so pop it off.
	    		stackPath.pop();
	    	else //we have a step to take, so add it to the stack
	    		stackPath.add(new StackElement(nextChoice, myMaze));
	    }
	    
	    //System.out.println(stackPath); //testing output
	    
	    //reverse the stack, and then pop it onto the foundPath in correct order
	    while (stackPath.size() != 0)
	    	reverseStack.push(stackPath.pop().returnLocation()); //simultaneously reverse and pare down to only dealing with MazeEntity's
	    																
	    while (reverseStack.size() != 0)
	    	foundPath.add(reverseStack.pop());
	    
	    optimizePath(foundPath); //remove elements from path that are redundant
		return foundPath;
	}
	
	/**
	 * Optimizes the path by tracing back through the stack and eliminating unnecessary
	 * steps. It does so by finding the lowest index'ed element that is a part of the 
	 * open locations surrounding the element being currently evaluated.
	 * 
	 * @param foundPath The path we are working on.
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
































	 