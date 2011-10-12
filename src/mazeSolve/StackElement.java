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
 * </p>
 * 
 * <p>All cases are assumed to work at this point. All test cases have been passed and there are no bugs
 * that I am currently aware of. If we encounter an unsolvable Maze, the final path just ends up being
 * empty as there was no path found.</p>
 * 
 * @author Ryan
 * @version Final, due date October 12/11
 *
 */
public class StackElement {
	private MazeEntity location; //holds our location in the maze
	private ArrayList<MazeEntity> rankedLocations; //will hold a ranked list of open locations surrounding our location
	
	private boolean biasNorth; //these are boolean controllers for our ranking
	private boolean biasSouth; //algorithm. A given path choice is ranked based
	private boolean biasWest;  //on how much closer it will move towards the end
	private boolean biasEast;  //location.
	
	/**
	 * @param loc type MazeEntity - the location to be represented by this StackElement
	 * @param myMaze of type Maze - the Maze that we are working with
	 */
	public StackElement(MazeEntity loc, Maze myMaze) {
		this.location = loc;
		setBiases(myMaze.getEndLoc()); //set our biases based on where the end loc is
		rankPathChoices(myMaze); //rank our path choices
	}

	/**
	 * 
	 * @param end This is the end entity of the Maze. Used for setting our directional
	 * movement biases.
	 */
	private void setBiases(EndEntity end)
	{
		//initialize our biases
		biasNorth = false;
		biasSouth = false;
		biasWest = false;
		biasEast = false;
		
		//set our biases to true if they match any of the conditions
		if (location.getRow() > end.getRow())
			biasNorth = true;
		else if (location.getRow() < end.getRow())
			biasSouth = true;
		
		if (location.getCol() > end.getCol())
			biasWest = true;
		else if (location.getCol() < end.getCol())
			biasEast = true;
		
	}
	
	/*
	 * Test method to print out biases to ensure they are being set properly
	 
	public void printBiases()
	{
		System.out.println("North = " + biasNorth + "  South = " + biasSouth);
		System.out.println("West = " + biasWest + "  East = " + biasEast);
	}
	*/
	
	/**
	 * Takes our pathChoices and ranks them. Helper method to be used in the constructor.
	 * 
	 * @param myMaze contains the maze we are working on.
	 */
	private void rankPathChoices(Maze myMaze) {
		ArrayList<MazeEntity> temp = myMaze.getOpenLocationsAround(location); //our temporary list of open locs
		rankedLocations = new ArrayList<MazeEntity>(0); //our ranked list...we start at 0 and grab from the temp list
		boolean added = false; //control var for loop below
		EndEntity end = myMaze.getEndLoc(); //end entity of the maze
		
		//rankedLocations.add(temp.get(0));
		
		for (int i = 0; i < temp.size(); i++) //for each element in temp
		{
			for (int ind = 0; ind < rankedLocations.size() && !added; ind++) //for each element in the ranked locations list
			{
				if ( stepVal(temp.get(i), end) > stepVal(rankedLocations.get(ind), end)) //check to see if the current temp element
				{																		 //has a higher ranking...if so, insert
					rankedLocations.add(ind, temp.get(i)); //add at the index ind, shifting everything with higher or equal indexes up one
					added = true;
				}
			}
			if (!added) //if we didn't find a spot...
				rankedLocations.add(temp.get(i)); //add to the end of the ranked locations list
			
			added = false; //reset for next loop
		}
	}
	
	/**
	 * Returns the next step to be taken, or null if none available.
	 *  
	 * @param visited This is the list of elements already visited for tracking purposes
	 * @return Returns the MazeEntity of the next step to be taken, or null if none avail.
	 */
	public MazeEntity nextStep(ArrayList<MazeEntity> visited)
	{
		MazeEntity choice = null;
		boolean choiceFound = false;
		
		while(rankedLocations.size() > 0 && !choiceFound) //while we don't have a choice selected
		{
			choice = rankedLocations.get(0); //select first element as it is pre-ranked
			
			if (visited.contains(choice)) //if already visited, remove from list and set choice to null
			{
				rankedLocations.remove(0); //remove selection from ranked list so we don't revisit anything
				choice = null; //set choice to null to ensure we don't return invalid choice
			}
			else //else we have an element to return
			{
				choiceFound = true; //end the loop
				visited.add(choice); //add to our visited 
				rankedLocations.remove(0); //remove from our ranked to ensure we don't reevaluate in the future
			}
		}

		//System.out.println(choice);//testing printout
		return choice;
	}
	
	
	/**
	 * Returns the MazeEntity location of the current StackElement
	 * @return The MazeEntity we are returning
	 */
	public MazeEntity returnLocation()
	{
		return this.location;
	}
	
	/**
	 * Defines the toString for this class. Only want to print out the locations toString.
	 * Used for testing purposes
	 */
	public String toString()
	{
		return location.toString();
	}
	
	
	/**
	 * Returns a value representing the weighting of a given pathChoice.
	 * The more positive, the better. 
	 * 
	 * @param pathChoice This is the potential path choice we are looking at taking
	 * @return Returns the weighting of our choice. The higher, the better.
	 */
	private int stepVal(MazeEntity pathChoice, MazeEntity end)
	{
		int choiceVal = 0;
		boolean movesRowCloser = false; //used for determining some weights below
		boolean movesColCloser = false; //used for determining some weights below
		
		if(pathChoice.getCol() > location.getCol() && biasEast) //if we are moving east, and the bias is east
		{														//slightly encourage
			choiceVal++; 
			movesColCloser = true; //used for later evaluation
		}
		else if (pathChoice.getCol() < location.getCol() && biasWest) //if we are moving west and bias is west
		{															  //slightly encourage
			choiceVal++;
			movesColCloser = true; //used for later evaluation
		}
		
		if (pathChoice.getRow() > location.getRow() && biasSouth) //if the choice moves south and bias is south
		{														  //slightly encourage
			choiceVal++;
			movesRowCloser = true; //used for later evaluation
		}	
		else if (pathChoice.getRow() < location.getRow() && biasNorth) //if choice moves north and bias is north
		{															   //slightly encourage
			choiceVal++;
			movesRowCloser = true; //used for later evaluation
		}
		
		if (pathChoice.getRow() == end.getRow()) //if it moves into/maintains the same row, then encourage choice
		{
			choiceVal += 2;
			if (!movesColCloser) //if it moves the column further away to satisfy the row, slightly dissuade
				choiceVal--;
		}
		
		if (pathChoice.getCol() == end.getCol()) //if it moves into/maintains same column, then encourage choice
		{
			choiceVal +=2;	
			if (!movesRowCloser) //if it moves the row further away to satisfy column, slightly dissuade
				choiceVal--;
		}
		
		//if the difference in columns is greater than the difference in rows, and the choice moves the col closer, encourage 
		if (Math.abs(pathChoice.getCol() - end.getCol()) > Math.abs(pathChoice.getRow() - end.getRow()) && movesColCloser)
		{
			choiceVal += 2;
		}
		//if the difference in columns is greater than the difference in rows, and the choice moves the col closer, encourage 
		else if (Math.abs(pathChoice.getRow() - end.getRow()) > Math.abs(pathChoice.getCol() - end.getCol()) && movesColCloser)
		{
			choiceVal += 2;
		}
			

		
		if (pathChoice.getRow() > location.getRow() && biasNorth) //if we move south but should be moving north
			choiceVal--;										  //slightly dissuade
		else if (pathChoice.getRow() < location.getRow() && biasSouth) //if we move north but should be moving south
			choiceVal--;											   //slightly dissuade
		
		if (pathChoice.getCol() > location.getCol() && biasWest) //if we are moving east but we should be moving west
			choiceVal--;										 //slightly dissuade
		else if (pathChoice.getCol() < location.getCol() && biasEast) //if we are moving west but we should be moving east
			choiceVal--;											  //slightly dissuade
		
		if (movesRowCloser && movesColCloser) //if both row and column are moving closer, give huge priority
			choiceVal += 10;
		
		if( pathChoice.isEndLocation()) //if the choice IS the end location, then just fucking go there
			choiceVal += 50;
		
		return choiceVal;
	}
}
