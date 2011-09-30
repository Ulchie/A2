package mazeSolve;

import java.util.ArrayList;

import mazeElements.EndEntity;
import mazeElements.MazeEntity;
import mazeElements.OpenEntity;
import mazeElements.StartEntity;

/**
 * Used to represent a path that was found in a maze
 * @author JKidney
 */
public class MazePath 
{
  private ArrayList<MazeEntity> mazePathElements = null;
  
  public MazePath()
  {
	  mazePathElements = new ArrayList<MazeEntity>();
  }
  
  /**
   * returns the number of elements in the path
   * @return the number of elements in the path
   */
  public int size() { return mazePathElements.size(); }
  public MazeEntity get(int index) {  return mazePathElements.get(index); }
  
  /**
   * Adds an element to the end of the path
   * @param me the element to add
   */
public void add(MazeEntity me) {  mazePathElements.add(me);  }
 

 /**
 *  removes the last element from the list
 */
public void removeLast() 
  {
	  if(mazePathElements.size() >= 1)
	  {
		  mazePathElements.remove(mazePathElements.size()-1);
	  }
  }
  
  /**
   * Removes an object from the list =. Comparison is done using the objects reference
  * @param me
  */
public void remove(MazeEntity me) { mazePathElements.remove(me); }
  
   /* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
public String toString() 
   {
	return mazePathElements.toString();
   }
	
}
