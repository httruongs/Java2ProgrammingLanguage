

public class Member implements IntKeyed{
	private final int MAX_BOOKS = 3;
	private int memId;
	private String firstName;
	private String lastName;
	MyArrayList<Book> checkedOut;
	
	public Member(int id, String last, String first) throws Exception
	{
		try{
			checkedOut = new MyArrayList<Book>(MAX_BOOKS);
		}catch(Exception e){
			System.out.printf("%s", e);
		}
		
		try{
			memId = id;
		}catch(Exception e){
			System.out.println("memID must be positive value. Try again");
		}
		lastName = last;
		firstName = first;
	}
	
	public String lastName()
	{
		return lastName;
	}
	
	public void setLastName(String l)
	{
		this.lastName = l;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String f)
	{
		this.firstName = f;
	}
	
	public int getKey()
	{
		return memId;
	}
	
	public boolean setKey(int key)
	{
		if(memId > 0)
		{
			this.memId = key;
			return true;
		}
		return false;
	}
	
	public boolean checkOut(Book book, MyArrayList<Book> availBooks, MyArrayList<Book> onLoanBooks )
	{
		if(checkedOut.lengthIs() < MAX_BOOKS)
		{
			checkedOut.addItem(book);
			onLoanBooks.addItem(book);
			
			availBooks.removeItem(book);
			return true;
		}
		
		return false;
	}
	
	public boolean returnBook(Book book, MyArrayList<Book> availBooks, MyArrayList<Book> onLoanBooks)
	{
		if(checkedOut.findItemByKey(MAX_BOOKS) == book)
		{
			checkedOut.removeItem(book);
			onLoanBooks.removeItem(book);
			availBooks.addItem(book);
			
			return true;
		}
		return false;
	}
	
	public void printCheckedOut()
	{
		checkedOut.toString();
	}
	
	public boolean equals(Object mem)
	{
		Member m;
		if(mem instanceof Member){
			m = (Member) mem;
			if(m.equals(mem) && m.equals(m) && mem.equals(mem))
			{
				if(firstName.equals(m.firstName) && lastName.equals(m.lastName))
				{
					return true;
				}
				return true;
			} else if(mem == null)
			{
				return false;
			}
		}
		return false;
	}
	
	public String toString()
	{
		if (checkedOut.lengthIs() == 0) {
			return String.format("Member Number: %d \tMember Name: %s, %s \n\tBooks On Loan: \t %s \n", memId, lastName, firstName, "*** No Item ***");
		} else {
			return String.format("Member Number: %d \tMember Name: %s, %s \n\tBooks On Loan: \n %s \n", memId, lastName, firstName, checkedOut.toString());
		}
	}
}
