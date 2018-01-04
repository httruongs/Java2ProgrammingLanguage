package ListPkg;

import java.util.Scanner;

public class Stack<T> {
	private LinkedList<T> list;
	
	public Stack()
	{
		list = new LinkedList<>();
	}
	
	public Stack(String name)
	{
		list = new LinkedList<>(name);
	}
	
	public void push(T item)
	{
		list.insertAtFront(item);
	}
	
	public T pop()
	{
		T item = null;
		
		//If exception occurs, do not handle; allow client to handle
		try {
			item = list.removeFromFront();
			
		} catch (Exception e) {
		/*	Scanner scan = new Scanner(System.in);
		//	do {
			//	System.out.println("Your expression was invalid - Please try again");
				System.out.println("Enter an expression in infix notation for evaluation");
				String input = scan.nextLine();
			} while (false);
                        //scan.close();*/
            System.err.println("Error is caught");
		}
		
		return item;
	}
	
	public int lengthIs()
	{
		return list.lengthIs();
	}
	
	public T peek()
	{
		T item = list.removeFromFront();
		try {
			list.insertAtFront(item);
		} catch (Exception e) {
			// exception
		}
		return item;
	}
	
	public void print()
	{
		list.print();
	}
	
	public boolean isEmpty()
	{
		//list.isEmpty and return the status of the list to the client
		return list.isEmpty();
	}

}
