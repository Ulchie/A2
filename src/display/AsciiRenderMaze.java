/**
 * Draws a maze to the screen using ascii characters 
 */
package display;


import mazeElements.Maze;

/**
 * Draws a maze to the screen using ascii characters
 * \author Jordan Kidney
 */

public class AsciiRenderMaze extends RenderMaze
{

  /**
 * @param m the maze to display
 */
public AsciiRenderMaze(Maze m)
  {
	  super(m);
  }
  

  /* (non-Javadoc)
 * @see display.RenderMaze#draw()
 */
public void draw()
  { 
     String line ="+";
	 for(int j=0; j < myMaze.numCols(); j++)
		line += "----"; 	 
     line += "+";
     
     System.out.println(line);
	 for(int i=0; i < myMaze.numRows(); i++)
     {
		System.out.print("|");
    	 for(int j=0; j < myMaze.numCols(); j++)
    	 {
    		 AsciiEntityDisplay aed = (AsciiEntityDisplay) myMaze.getEntity(i,j);
    		 if(aed != null)
    			 System.out.print(aed.getDisplayString());
    	 }
    	System.out.println("|"); 
     }
	 System.out.println(line);
  }

}
