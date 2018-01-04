

//YOUR HEADER HERE 
//
//YOU MUST ADD THE NECESSARY CODE TO PROCESS EACH REQUESTED MENU OPTION
//
//Filename: LibraryManager.java
//This file contains a class to create three lists: a list of books owned, a list of 
//books in stock at the library and a list of library members.
//The library members may check out books available in stock - up to to their specified 
//maximum 
//This program uses the custom MyArrayList generic class to store information

import java.util.Scanner;
import java.awt.List;
import java.util.InputMismatchException;

public class LibraryManager
{
	public static void main(String[] args) {
		// Declare and instantiate the library lists
		MyArrayList<Book> onLoanBooks; // List of books owned by the library
		try {
			onLoanBooks = new MyArrayList<Book>(5);     // List of books available for checkout
		}
		catch (Exception e) {
			System.out.println ("Size of list is invalid - Creating default list");
			onLoanBooks = new MyArrayList<Book>();
		}

		MyArrayList<Book> availBooks;
		try {
			availBooks = new MyArrayList<Book>(10);     // List of books available for checkout
		}
		catch (Exception e) {
			System.out.println ("Size of list is invalid - Creating default list");
			availBooks = new MyArrayList<Book>();
		}

		MyArrayList<Member> members;
		try {
			members = new MyArrayList<Member>(5);// List of all members of the library
		}
		catch (Exception e) {
			System.out.println ("Size of list is invalid - Creating default list");
			members = new MyArrayList<Member>();
		}

		//Declare input Scanner object for user input
		Scanner input = new Scanner (System.in);
		
		// initialize the lists with sample values to facilliate testing
		initializeBooks(availBooks);
		initializeMembers(members);

		// Print the list of all books, all books on loan (none) and all members
		System.out.println ("---------------------------------------------------------");
		System.out.println ("All Available Books: \n" + availBooks);		
		if(availBooks.lengthIs() == 0){
			System.out.println("*** No Item ***");
		}
		System.out.println ("---------------------------------------------------------");
		System.out.println ("All Books on Loan: \n" + onLoanBooks);
		if(onLoanBooks.lengthIs() == 0){
			System.out.println("*** No Item ***");
		}
		System.out.println ("---------------------------------------------------------");
		System.out.println ("All Members: \n\n" + members);
		System.out.println ("---------------------------------------------------------");
		
		// Create the array of Strings containing the main menu options (Quit - option 0)
		// Create the mainMenu object
		String opts[] = {"Quit","Check Out Book", "Return Book", "List All Available Books", 
		"List All Books Checked Out"};
		Menu mainMenu = new Menu(opts);

		// create various temporary variables - Add any other variables you might need
		int isbn = 0, memNum = 0;   //variables to hold user inputs
		Book theBook = null;       // temporary object for a book found in a list
		Member libMem = null;      // temporary object for a member found in the list
		boolean goodInput;  // for use in allowing the user to correct invalid inputs

		// while user wants to continue
		// run the menu
		// process the user's requested option
		int opt = 0;
		boolean promptReenterMemNum, promptReenterISBN, validISBN;
		
		do {
			opt = mainMenu.runMenu();
			switch (opt) {
				case 1: //check out book
					
					// if no books available
					if(availBooks == null) { 
						System.out.println("There is no book on available list");//    inform the user
					} else {
						// display all current members
						System.out.println("All Members: ");
						System.out.println(members.toString());		
					}
					
					System.out.println("Enter a member number: "); //    get a member number from the user
					
					goodInput = false;
					promptReenterMemNum = false;
					
					while(!goodInput)
					{
						try {
							if (promptReenterMemNum) {
								System.out.println("Invalid member number. Enter a member number:");
							}
							memNum = input.nextInt();
							
							// find the member in the member list
							// display all books available for checkout
							if(members.findItemByKey(memNum) != null) {
								libMem = members.findItemByKey(memNum);
								System.out.println("Choose a book for checkout:");
								System.out.print("All books: \n" +  availBooks);
							} else {
								goodInput = false;
								promptReenterMemNum = true;
								continue;
							}
						} catch(InputMismatchException ie) {
							promptReenterMemNum = false;
							System.out.println("Invalid member number chosen - try again.");
							System.out.print("Enter a member number: ");
							input.next(); 
							continue;
						}

						goodInput = true;
					}
					
					// read user's choice for book to check out
					//    - allow user to correct input if value input is not an integer
					// if the desired book is available
					//           checkout the book and remove it from the available books
					//           add the book to the list of books on loan
					//           if unable to check out the book report an error
					System.out.println("Enter the ISBN for the desired book: "); //    get a member number from the user
					
					goodInput = false;
					validISBN = false;
					
					while(!goodInput)
					{
						try {
							isbn = input.nextInt();
							
							if (availBooks.findItemByKey(isbn) != null) {
								validISBN = true;
								theBook = availBooks.findItemByKey(isbn);
							} else {
								goodInput = false;
								System.out.println("Book not found in this library");
							}
						}catch(InputMismatchException ie) {
							System.out.println("Invalid isbn number - Try Again.");
							System.out.print("Enter the ISBN for the desired book: ");
							input.next(); 
							continue;
						}

						goodInput = true;
					}
					
					if (validISBN) {
						if (libMem.checkedOut.lengthIs() < 3) {
							if (availBooks.findItem(theBook)) {
								libMem.checkOut(theBook, availBooks, onLoanBooks);
								availBooks.removeItem(theBook);
								System.out.println("Book checked out successfully.");
								System.out.println("This Member: \n" + members.findItemByKey(memNum));
							} else {
								System.out.println("Unable to checkout the book.");
							}
						} else {
							System.out.println("Unable to check out book - too many books on loan.");
						}
					}

					break;
			
				case 2:
					// return book
					// display all current members
					System.out.println(members.toString());
					// ask the user for a member number
					System.out.println("Enter a member number: ");
					
					goodInput = false;
					promptReenterMemNum = false;
					while(!goodInput)
					{
						try {
							if (promptReenterMemNum) {
								System.out.println("Invalid member number. Enter a member number:");
							}
							
							memNum = input.nextInt();
							
							if(members.findItemByKey(memNum) != null) {
								libMem = members.findItemByKey(memNum);
								System.out.println("All Books On Loan to this Member:"); // display all books checked out by this member
								System.out.println("Books on Loan to Member: " + libMem.lastName() + ", " + libMem.getFirstName() + members.findItemByKey(memNum));
							} else {
								goodInput = false;
								promptReenterMemNum = true;
								continue;
							}
							
						} catch(InputMismatchException ie) {
							promptReenterMemNum = false;
							System.out.println("Invalid member number chosen - try again.");
							System.out.print("Enter a member number: ");
							input.next(); 
							continue;
						}

						goodInput = true;
					}
					
					// prompt for and read user's choice for book to return
					System.out.println("Enter a book number to return: ");
					
					goodInput = false;
					promptReenterISBN = false;
					validISBN = false;
					
					while(!goodInput)
					{
						try {
							if (promptReenterISBN == true) {
								System.out.println("Invalid ISBN. Enter isbn: ");
							}
							
							isbn = input.nextInt();
							
							// find the book in the books-on-loan list
							if (onLoanBooks.findItemByKey(isbn) != null) {
								theBook = onLoanBooks.findItemByKey(isbn);
							} else {
								goodInput = false;
								promptReenterISBN = true;
								continue;
							}
						} catch(InputMismatchException ie) {
							promptReenterISBN = false;
							System.out.println("Invalid isbn number - Try Again.");
							System.out.print("Enter the ISBN for the desired book: ");
							input.next(); 
							continue;
						}
						
						goodInput = true;
					}
					//	- allow user to correct input if value input is not an integer
					
					// if the desired book is found in the books-on-loan list
					if (onLoanBooks.findItem(theBook)) {
						onLoanBooks.removeItem(theBook);
						availBooks.addItem(theBook); //      return the book
						libMem.checkedOut.removeItem(theBook); // remove the item from the member's book list
						System.out.println("Book returned successfully.");
						System.out.println("This Member: \n" + members.findItemByKey(memNum));
					} else {
						//	if unable to return the book report an error
						System.out.println("Unable to return the book.");
					}

					break;
				case 3: //List all available books and the total number of books available
					System.out.println(availBooks);						
					System.out.println("There is/are " + availBooks.lengthIs() + " available.");
					
					break;
				case 4: // List all books checked out and the total number of books on loan
					System.out.println(onLoanBooks);
					System.out.println("There is/are " + onLoanBooks.lengthIs() + " on loan.");
					
					break;
				default:
					System.out.println ("Thank you - Have a nice day!");
					break;
			
			}
		} while (opt > 0);
		
	}
	
	// The following two methods are provided to facilliate testing
	// NO CHANGES ARE NEEDED TO THIS METHOD
	// Method: initialzieBooks
	// Method to initialize the books in stock and the books owned
	// Initially all books are in stock for lending
	public static void initializeBooks(MyArrayList<Book> availBooks){
		//Populate books and stock MyArrayLists
		try {
			availBooks.addItem(new Book("Ender's Game", "Card, Orson Scott", 1000));
			availBooks.addItem(new Book("Breakfast of Champions", "Vonnegut, Kurt", 2000));
			availBooks.addItem(new Book("The Alphabet of Manliness", "Maddox", 3000));
			availBooks.addItem(new Book("A Condeferacy of Dunces", "Toole, John Kennedy", 4000));
			availBooks.addItem(new Book("Dune", "Herbert, Frank", 5000));
			availBooks.addItem(new Book("History of Western Philosophy", "Russell, Bertrand", 6000));
			availBooks.addItem(new Book("Choke", "Palahniuk, Chuck", 7000));
			availBooks.addItem(new Book("Me Talk Pretty One Day", "Sedaris, David", 8000));
			availBooks.addItem(new Book("House of Leaves", "Danielewski, Mark", 9000));
			availBooks.addItem(new Book("Eats, Shoots, & Leaves", "Truss, Lynne", 10000));
		}
		catch (Exception e) {
			System.out.println ("Error Creating Book: " + e);
		}
	}
	
	// NO CHANGES ARE NEEDED TO THIS METHOD
	// Method: initializeMembers (MyArrayList<Member> members)
	// Populate members MyArrayList
	public static void initializeMembers(MyArrayList<Member> members){
		try {
			members.addItem(new Member(11111,"Parker", "Peter"));
			members.addItem(new Member(22222,"Spector", "Marc"));
			members.addItem(new Member(33333,"Curry", "Arthur"));
			members.addItem(new Member(44444,"Stark", "Tony"));
			members.addItem(new Member(55555,"Queen", "Oliver"));

			// Adding this member will cause the 'enlarge' method to be called
			members.addItem(new Member(66666,"Smith", "Mary"));
		}
		catch (Exception e) {
			System.out.println ("Error creating member: " + e);
		}
	}//end main
}