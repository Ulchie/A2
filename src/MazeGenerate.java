import display.FileRenderMaze;
import mazeBuild.RandomMazeBuilder;
import mazeElements.Maze;

/**
 * Generates a maze and outputs the result to a file
 * configured using command line arguments
 * 
 * usage 1 random: java MazeGenerate filename <type> <rows> <cols>
 * 
 * <type> -> r = random
 */

/**
 * @author JKidney
 *
 */
public class MazeGenerate 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		if(args.length >= 4)
		{
			String filename = args[0];
			String type = args[1];
			int rows = Integer.parseInt(args[2]);
			int cols = Integer.parseInt(args[3]);
			
			Maze myMaze = null;
			
			if(type.compareToIgnoreCase("r") == 0)
			{
				RandomMazeBuilder rmb = new RandomMazeBuilder(rows, cols, 3);
				myMaze = rmb.build();
			}
			
			try
			{
			  FileRenderMaze frm = new FileRenderMaze(filename, myMaze);
			  frm.draw();
			  
			  System.out.println("Maze generated and saved to file: " + filename);
			}
			catch(Exception e)
			{
				System.out.println("Error: unable to save maze to file : " + e.getMessage());
			}
			
		}
		else
		{
			System.out.println("usage: java MazeGenerate filename <type> <rows> <cols>");
		}

	}

}
