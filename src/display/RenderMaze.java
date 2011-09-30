/**
 * Base class used to represent entities that could display/render the maze in different 
 * formats. 
 */
package display;

import mazeElements.*;

/**
 *  Base class used to represent entities that could display/render the maze in different 
 * formats. 
 * \author Jordan Kidney
 */
public abstract class RenderMaze
{
	protected Maze myMaze;
	
    /**
     * sets the reference to the maze that will be displayed/rendered
     * @param myMaze the maze to use
     */
    public RenderMaze(Maze myMaze) {
		super();
		this.myMaze = myMaze;
	}

	/**
	 * displays/renders the maze
	 */
	public abstract void draw();
}
