
public class TestMaze {

	public static void main(String[] args) {
		String[] option = {"Quit","Maze 1","Maze 2","Maze 3","Maze 4"};
		Menu menu = new Menu(option);	
		System.out.println("\t*** Select a Maze ***");
		int mazeNum  = menu.runMenu();
		
		while (mazeNum != 0)
		{			
			Maze maze = new Maze(mazeNum);
			System.out.println("BEFORE TRAVERSAL:");
			maze.printMaze();
			maze.traverseMaze();
			System.out.println("\n");
			System.out.println("\t*** Select a Maze ***");
			mazeNum = menu.runMenu();
		}
	}

}
