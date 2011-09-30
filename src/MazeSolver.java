import display.*;
import mazeBuild.*;
import mazeElements.*;
import mazeSolve.*;

/**
 *  Main start up class for running the solvers against a maze
 *  
 *  usage: java MazeSolver <maze gen type> < arguments >
 *  
 *       (1) Open maze from a file: java MazeSolver file <filename>
 *       (2)           random maze: java MazeSolver rand <rows> <cols>   
 */

/**
 * @author JKidney
 *
 */
public class MazeSolver 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	  Maze mazeToSolve = null;
	  MazeBuilder builder = null;
	  MazePath stackPath = null;
	
	
	  StackMazePathFinder stackPathFind = null;  
	  
	  
	 if(args.length >= 1)
	 {
	   //get start up maze type
	   String type = args[0];	 
		 
	  try
	  {
		  System.out.println("[1] Building Maze");
		  if(type.compareToIgnoreCase("file") == 0) // build the maze from a file
		  {
			  builder = new FileMazeBuilder(args[1]);
		  }
		  else if(type.compareToIgnoreCase("rand") == 0) //  build a random maze
		  {
			  int rows = Integer.parseInt(args[1]);
		      int cols = Integer.parseInt(args[2]);
			  builder = new RandomMazeBuilder(rows, cols, 3);
		  }
		  
		  //Print the maze
		  mazeToSolve = builder.build();
		  mazeToSolve.print();
		  
		  //run the solver
		  System.out.println("[2] Running Stack path finder");
		  stackPathFind = new StackMazePathFinder(mazeToSolve);
		  
		  //Print the maze and path combined
		  stackPath = stackPathFind.findPath();
		  Maze stackMaze = new Maze(mazeToSolve);
		  stackMaze.markPath(stackPath);
		  stackMaze.print();
		  		  
		  //print the path
		  System.out.println("[3] Path found");
		  System.out.println("    Stack: " + stackPath );
		  
	  }
	  catch(Exception e)
	  {
		System.out.println("Error: unable to solve maze");
		e.printStackTrace();
	  }
	  
	 }
	 else
	 {
		 System.out.println("Error: wrong arguments");
	 }
		
	}

}
