/**
 * Class used to represent a maze and provide basic functionality
 */
package mazeElements;

import java.util.ArrayList;

import display.AsciiRenderMaze;

import mazeSolve.MazePath;

/**
 * Class used to represent a maze and provide basic functionality
 * \author Jordan Kidney
 */
public class Maze
{
   /** array representing the maze */
   private MazeEntity locations[][] = null; 
   /** reference to the start location, also referenced by locations */
   private StartEntity startLoc;
   /** reference to the end location, also referenced by locations */
   private EndEntity endLoc;
   /** display/render object to draw the maze to the console */
   private AsciiRenderMaze displayMaze = new AsciiRenderMaze(this);
   
   
 /**
  * Copy constructor to duplicate a maze
 * @param m
 */
public Maze(Maze m)
   {
	   this(m.numRows(),m.numCols());
	   
	   for(int i =0; i < m.numRows(); i++)
		   for(int j=0; j < m.numCols(); j++)
		   {
			   locations[i][j] = m.locations[i][j].duplicate();
		   }
	   
	   startLoc = (StartEntity) m.startLoc.duplicate();
	   endLoc = (EndEntity) m.endLoc.duplicate();
   }
   
   
 /**
  * Generates an empty maze of the given size
 * @param numRows number of rows to create
 * @param numCols number of column to create
 */
public Maze(int numRows, int numCols)
   {
	   locations = new MazeEntity[numRows][numCols];
	   
	   for(int i =0; i < numRows; i++)
		   for(int j=0; j < numCols; j++)
		   {
			   locations[i][j] = null;
		   }
   }
   
   public int numRows() { return locations.length; }
   public int numCols() { return locations[0].length; }
   
 /**
  * Determins if a given location can be moved onto
 * @param row the row location to check
 * @param col the col location to check
 * @return true if it can be moved onto false otherwise
 */
public boolean canMoveOnto(int row, int col)
   {
	   boolean res = false;
	   if(row >= 0 && row < numRows() && col >= 0 && col < numCols() )
	   {
	     if(locations[row][col] != null)
	    	 if(locations[row][col].canMoveOnto())
	    		 res = true;
	   }
	   return res;
   }
   
   
 /**
  * Returns an ArrayList of all maze entities 1 step around the given entity 
  * that can be moved onto. Note: this list contains references to the original
  * objects in the maze not duplicates.
  * @param me the entity to check for open areas around
  * @return the list of open locations that can be moved onto
  */
public ArrayList<MazeEntity> getOpenLocationsAround(MazeEntity me)
   {
	   return getOpenLocationsAround(me.getRow(), me.getCol());
   }
   
   
 /**
 *  Returns an ArrayList of all maze entities 1 step around the given entity 
 *  that can be moved onto.Note: this list contains references to the original
  * objects in the maze not duplicates.
 * @param row the row location of the entity
 * @param col the col location of the entity
 * @return the list of open locations that can be moved onto
 */
public ArrayList<MazeEntity> getOpenLocationsAround(int row, int col)
   {
	   ArrayList<MazeEntity> openLocations = new ArrayList<MazeEntity>();
	
		  // {-1,-1} {-1,0} {-1, 1}  row and col offsets to check all locations around an entity
	      // { 0,-1}        { 0, 1}
	      // { 1,-1} {1, 0} { 1, 1}
		  int rows[] = {-1, -1,-1, 0,0, 1,1,1};
		  int cols[] = {-1,  0, 1,-1,1,-1,0,1};
		  
		 for(int i=0; i < rows.length; i++)
		 {
			int nrow = row + rows[i];
			int ncol = col + cols[i];
				
			if(canMoveOnto(nrow, ncol))
				openLocations.add(getEntity(nrow, ncol));
		 }
	
	   return openLocations;
   }
   
   
 /**
  * returns an entity at a given location. Note this is  a reference not a duplicate
 * @param row the row location of the entity to return
 * @param col the col location of the entity to return
 * @return null if the row and col are out of bounds otherwise the maze entity at the location
 */
public MazeEntity getEntity(int row, int col)
   {
	   MazeEntity ret = null;
	   
	   if(row >= 0 && row < numRows() && col >= 0 && col < numCols() )
	   {
		   ret = locations[row][col];
	   }
	   
     return ret;
   }

 /**
  * sets an element in the maze based upon the given location
 * @param row the row location to place the entity at
 * @param col the row location to place the entity at
 * @param me the entity to set
 */
public void setEntity(int row, int col, MazeEntity me)
   {  
	   if(row >= 0 && row < numRows() && col >= 0 && col < numCols() )
	   {
		  locations[row][col] = me;
          me.setRow(row);
	      me.setCol(col);
	   }
   }
   
 /**
  * Used when displaying the combination of a maze and a path
  * this method will replace entities in the maze with PathMarkerEntity
  * @param path the path to use a s markers
  */
public void markPath(MazePath path)
   {
	 for (int i = 0; i < path.size(); i++) 
	 {
	   MazeEntity step = path.get(i);
	   
	   setEntity(step.getRow(), step.getCol(),  new PathMarkerEntity(step.getRow(), step.getCol(), i) );
	 }   
   }
   
 /**
 * Prints the current maze to the screen 
 */
public void print()
   {
     displayMaze.draw();	  
   }
   
  public StartEntity getStartLoc() { return startLoc; }
  public EndEntity getEndLoc() { return endLoc; } 
  public void setStartLoc(StartEntity startLoc) { this.startLoc = startLoc; }
  public void setEndLoc(EndEntity endLoc) { this.endLoc = endLoc; }
  
}
