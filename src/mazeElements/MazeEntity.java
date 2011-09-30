/**
 * Base class used to represent a single entitiy in the maze
 * 
 * @author Jordan Kidney
 * @version Sept 22, 2011
 */
package mazeElements;

import display.*;

public abstract class MazeEntity implements AsciiEntityDisplay
{
	   private int row;
	   private int col;

	 /**
	  * Base copy constructor 
	  * @param me maze element to copy information from
	  */
	public MazeEntity(MazeEntity me)
	 {
		row = me.row;
		col = me.col;
	 }
	   
	/**
	 * sets the x and y location of the entity
	 * @param row the row the entity is located at
	 * @param col the col the entity is located at
	 */
   public MazeEntity(int row, int col)
	{
		super();
		this.col = col;
		this.row = row;
	}

   /**
    * Used to detect if the entity can be moved onto while travelling 
    * through the maze
    * @return returns true if the location can be moved onto false otherwise
    */
   public abstract boolean canMoveOnto();
   
   
   /**
   * duplicates the current object and returns a copy
   * @return a duplicate copy of the current object
   */
   public abstract MazeEntity duplicate();
    
   /**
    * indicates if the current entity is the start location or not
    * @return true if it is the start entity false otherwise
    */
   public boolean isStartLocation() { return false; }
   
   /**
    * indicates if the current entity is the end location or not
    * @return true if it is the end entity false otherwise
    */
   public boolean isEndLocation() { return false; }
      
   /**
    * access the row location of the entity
    * @return the row location
    */
   public int getRow() { return row; }
   
   /**
    * access the col location of the entity
    * @return the col location
    */
   public int getCol() { return col; }

   public void setRow(int row){this.row = row;}
   public void setCol(int col){this.col = col;}
   

   public String toString() 
   {
		return "("+row+","+col+")";
   }
}
