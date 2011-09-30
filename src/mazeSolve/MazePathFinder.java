/**
 * Base class for all maze solvers
 */
package mazeSolve;

import mazeElements.Maze;

/**
 * Base class for all maze solvers
 * @author JKidney
 *
 */
public abstract class MazePathFinder 
{
  /** the maze the solver should find a path through */
  protected Maze myMaze = null;

  /**
   * Base constructor
   * @param myMaze the maze that will be used to find a path in
   */
  public MazePathFinder(Maze myMaze) 
  {
	super();
	this.myMaze = myMaze;
  }
  
  /**
   * finds and returns a MazePath objects that represents a found path in the maze
   * @see MazePath
   * @return a MazePath object witht he found path
  */
public abstract MazePath findPath();
}
