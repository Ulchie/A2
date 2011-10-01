package mazeSolve;

import java.util.ArrayList;

import mazeElements.*;

/**
 * <p>This will be the class to hold our StackElements. Each StackElement
 * will contain a MazeEntity that will hold its location as well as
 * an ArrayList<MazeEntity> that will hold the open positions around
 * it. </p>
 * <p>When created, the list will be ranked. That is, preferred elements
 * will be determined and ranked earlier in the list accordingly.
 * Each time an open position is taken, we will remove it from the
 * open locations list.</p>
 * @author Ryan
 *
 */
public class StackElement {
	MazeEntity location;
	ArrayList<MazeEntity> rankedLocations;
	/**
	 * 
	 */
	public StackElement(MazeEntity loc, Maze myMaze) {
		// TODO Auto-generated constructor stub
		this.location = loc;
		rankedLocations = myMaze.getOpenLocationsAround(location);
	}

	private void rankPathChoices(Maze myMaze) {
		//ranking algorithm here
		rankedLocations = myMaze.getOpenLocationsAround(location);
	}
	
	public MazeEntity nextStep( ArrayList<MazeEntity> exhaustedLocations )
	{
		MazeEntity nextChoice = null;
		boolean choiceFound = false;
		//grab the prioritized next choice
		
		while (!rankedLocations.isEmpty() && !choiceFound) //if choices left
		{
			//If we have already exhausted our options with the best ranked
			//choice from this node, remove it from the ranking
			if (exhaustedLocations.contains(rankedLocations.get(0)))
				rankedLocations.remove(0);
			else //make this our next step
			{
				nextChoice = rankedLocations.get(0);
				choiceFound = true;
			}
		}
		return nextChoice;		
	}
	
}
