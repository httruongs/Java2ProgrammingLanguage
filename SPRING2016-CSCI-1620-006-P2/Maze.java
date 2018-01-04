// YOUR HEADER HERE!!!
//
public class Maze {


    // Sample maze 1
    final int MAZE1_ROWS = 12;
    final int MAZE1_COLS = 12;
    final int MAZE1_ENTERROW = 4;
    final int MAZE1_EXITROW = 8;
    final char[][] MAZE1 = {
        {'#','#','#','#','#','#','#','#','#','#','#','#'},
        {'#','.','.','.','#','.','.','.','.','.','.','#'},
        {'#','.','#','.','#','.','#','#','#','#','.','#'},
        {'#','.','#','.','#','.','.','.','.','#','.','#'},
        {'.','.','#','.','.','#','#','#','.','#','.','#'},
        {'#','#','#','#','.','#','.','.','.','#','.','#'},
        {'#','.','.','#','.','.','.','#','#','#','.','#'},
        {'#','#','.','#','.','#','#','#','.','#','.','#'},
        {'#','.','.','.','.','.','.','.','.','#','.','.'},
        {'#','#','#','#','#','#','.','#','#','#','.','#'},
        {'#','.','.','.','.','.','.','#','.','.','.','#'},
        {'#','#','#','#','#','#','#','#','#','#','#','#'}};


    // Sample maze 2
    final int MAZE2_ROWS = 8;
    final int MAZE2_COLS = 12;
    final int MAZE2_ENTERROW = 6;
    final int MAZE2_EXITROW = 1;
    final char[][] MAZE2 = {
        {'#','#','#','#','#','#','#','#','#','#','#','#'},
        {'#','.','.','.','#','.','.','.','#','#','.','.'},
        {'#','.','#','.','.','.','#','.','.','.','.','#'},
        {'#','.','#','#','#','#','.','#','.','#','.','#'},
        {'#','.','.','.','#','#','.','.','.','.','.','#'},
        {'#','#','#','.','#','#','#','#','.','#','.','#'},
        {'.','.','.','.','.','.','.','.','.','.','#','#'},
        {'#','#','#','#','#','#','#','#','#','#','#','#'}};

    // Sample maze 3
    final int MAZE3_ROWS = 9;
    final int MAZE3_COLS = 9;
    final int MAZE3_ENTERROW = 4;
    final int MAZE3_EXITROW = 3;
    final char[][] MAZE3 = {
        {'#','#','#','#','#','#','#','#','#'},
        {'#','.','#','.','#','.','.','.','#'},
        {'#','.','.','.','#','.','#','#','#'},
        {'#','#','#','.','#','.','#','.','.'},
        {'.','.','.','.','.','.','#','.','#'},
        {'#','#','.','#','.','#','#','.','#'},
        {'#','.','.','#','.','#','.','.','#'},
        {'#','#','.','#','.','#','.','.','#'},
        {'#','#','#','#','#','#','#','#','#'}};

      // Sample maze 4
      final int MAZE4_ROWS = 12;
      final int MAZE4_COLS = 12;
      final int MAZE4_ENTERROW = 2;
      final int MAZE4_EXITROW = 4;
      final char[][] MAZE4 = {
          {'#','#','#','#','#','#','#','#','#','#','#','#'},
          {'#','.','.','.','#','.','.','.','.','.','.','#'},
          {'.','.','#','.','#','.','#','#','#','#','.','#'},
          {'#','#','#','.','#','.','.','.','.','#','.','#'},
          {'#','.','.','.','.','#','#','#','.','#','.','.'},
          {'#','#','#','#','.','#','.','#','.','#','.','#'},
          {'#','.','.','#','.','#','.','#','.','#','.','#'},
          {'#','#','.','#','.','#','.','#','.','#','.','#'},
          {'#','.','.','.','.','.','.','.','.','#','.','#'},
          {'#','#','#','#','#','#','.','#','#','#','.','#'},
          {'#','.','.','.','.','.','.','#','.','.','.','#'},
          {'#','#','#','#','#','#','#','#','#','#','#','#'}};

    // Private data members for processing maze
    private char maze [][]; // the maze array to traverse
    private int rows;       // the number of rows in this maze
    private int cols;       // the number of columns in this maze
    private int enterRow;   // the entry row for this maze; start column is 0
    private int exitRow;    // the exit row for this maze; end column is cols-1
    private int steps;      // number of steps in the solvable path

    public Maze(int mazeNum)
    {
    	switch (mazeNum) {
    		case 1:
    			maze = MAZE1;
    			rows = MAZE1_ROWS;
    			cols = MAZE1_COLS;
    			enterRow = MAZE1_ENTERROW;
    			exitRow = MAZE1_EXITROW;
    			break;
    		case 2:
    			maze = MAZE2;
    			rows = MAZE2_ROWS;
    			cols = MAZE2_COLS;
    			enterRow = MAZE2_ENTERROW;
    			exitRow = MAZE2_EXITROW;
    			break;
    		case 3:
    			maze = MAZE3;
    			rows = MAZE3_ROWS;
    			cols = MAZE3_COLS;
    			enterRow = MAZE3_ENTERROW;
    			exitRow = MAZE3_EXITROW;
    			break;
    		case 4:
    			maze = MAZE4;
    			rows = MAZE4_ROWS;
    			cols = MAZE4_COLS;
    			enterRow = MAZE4_ENTERROW;
    			exitRow = MAZE4_EXITROW;
    			break;
    		default:
    			System.out.println("Maze not found.");
    			break;
    	}
    }
    
    public void printMaze()
    {
    	System.out.println("This maze is size: " + rows + " x " + cols);
    	
    	for (int x = 0; x < maze.length; x++)
    	{
    		for (int y = 0; y < maze[x].length; y++)
    		{
    			System.out.printf("%3c",maze[x][y]);
    		}
    		System.out.println("\n");
    	}
    }
    
    public void traverseMaze()
    {    	        
    	if (findPath (enterRow, 0))
    	{
    		System.out.println("*** SOLVED ***\n");
    		System.out.println("AFTER TRAVERSAL:");
    		printMaze();
    		System.out.printf("Steps in Path: %d", steps);
    	}
    	else
    	{
    		System.out.println("*** NOT SOLVED ***");
    	}
    }
    
    public boolean findPath(int row, int col)
    {
    	if(row >= rows || col >= cols || row < 0 || col < 0)
    	{
    		return false;
    	}
    	
    	if(col == cols-1 && maze[row][col] == '.')
    	{
    		steps++;
    		maze[row][col] = 'O';
    		return true;
    	}
    	
    	if(maze[row][col] == '#' || maze[row][col] == 'O')
    	{
    		return false;
    	}
    	
    	maze[row][col] = 'O';
    	if(findPath(row, col+1) == true)
    	{
    		steps++;
    		return true;
    	}
    	
    	if(findPath(row-1, col) == true)
    	{	
    		steps++;
    		return true;
    	}
    	if(findPath(row, col-1) == true)
    	{
    		steps++;
    		return true;
    	}
    	
    	if(findPath(row+1, col) == true)
    	{
    		steps++;
    		return true;
    	}
    	
  		maze[row][col] = '.';
        		
       	return false;
    }
}
