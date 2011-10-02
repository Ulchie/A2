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
	private MazeEntity location;
	private ArrayList<MazeEntity> rankedLocations;
	
	private boolean biasNorth; //these are boolean controllers for our ranking
	private boolean biasSouth; //algorithm. A given path choice is ranked based
	private boolean biasWest;  //on how much closer it will move towards the end
	private boolean biasEast;  //location.
	
	/**
	 * 
	 */
	public StackElement(MazeEntity loc, Maze myMaze) {
		// TODO Auto-generated constructor stub
		this.location = loc;
		setBiases(myMaze.getEndLoc()); //set our biases based on where the end loc is
		rankPathChoices(myMaze);
	}

	private void setBiases(EndEntity end)
	{
		//initialize our biases
		biasNorth = false;
		biasSouth = false;
		biasWest = false;
		biasEast = false;
		
		int endX = end.getRow();
		int endY = end.getCol();
		
		//set our biases to true if they match any of the conditions
		if (location.getCol() - endY > 0)
			biasNorth = true;
		else if (location.getCol() - endY < 0)
			biasSouth = true;
		
		if (location.getRow() - endX > 0)
			biasWest = true;
		else if (location.getRow() - endX < 0)
			biasEast = true;
	}
	//TODO OPTOMIZE this ordering
	private void rankPathChoices(Maze myMaze) {
		rankedLocations = myMaze.getOpenLocationsAround(location);
		MazeEntity placeHolder;
		boolean doneLoop = false;
		
		//in-place sort
		for (int i = 1; i < rankedLocations.size(); i++)
		{
			for (int j = 0; j < i && !doneLoop; j++)
			{
				if (stepVal(rankedLocations.get(i)) >= stepVal(rankedLocations.get(j)))
				{
					rankedLocations.add(j, rankedLocations.get(i));
					rankedLocations.remove(i+1); //removes where i was before the insertion
					doneLoop = true;
				}
			}
			doneLoop = false;
		}
	}
	/**
	 * 
	 * @param pathChoice
	 * @return
	 */
	private int stepVal(MazeEntity pathChoice)
	{
		int choiceVal = 0;
		
		
		
		return choiceVal;
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
