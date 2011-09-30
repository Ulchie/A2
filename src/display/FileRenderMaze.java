/**
 * Converts a maze objects into a text file that can be reused at  a later time
 * @see mazeBuild.FileMazeBuilder
 */
package display;

import java.io.*;

import mazeElements.*;


/**
 * @author JKidney
 *
 */
public class FileRenderMaze extends RenderMaze 
{
	private PrintStream output = null;
	
	
	/**
	 * @param filename file to save maze into
	 * @param m the maze to convert
	 * @throws IOException if any errors with the file occur.
	 */
	public FileRenderMaze(String filename, Maze m) throws IOException
	{
	  super(m);
	  output = new PrintStream(new FileOutputStream(filename));
	}
	
	/* (non-Javadoc)
	 * @see Display.RenderMaze#draw()
	 */
	@Override
	public void draw() 
	{
		 output.println(""+myMaze.numCols() + " " + myMaze.numRows());
		 for(int i=0; i < myMaze.numRows(); i++)
	     {
	    	 for(int j=0; j < myMaze.numCols(); j++)
	    	 {
	    		 AsciiEntityDisplay aed = (AsciiEntityDisplay) myMaze.getEntity(i,j);
	    		 if(aed != null)
	    			 output.print(aed.getDisplayString() + ",");
	    	 }
	    	output.println(""); 
	     }
		 
      output.close();
	}

}
