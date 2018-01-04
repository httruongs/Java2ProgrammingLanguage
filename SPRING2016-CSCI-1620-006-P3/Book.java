

public class Book implements IntKeyed {
	private int isbn;
	private String title;
	private String author;
	
	public Book(String t, String a, int i) throws Exception
	{
		title = t;
		author = a;
		isbn = i;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String t)
	{
		this.title = t;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public void setAuthor(String s)
	{
		this.author = s;
	}
	
	public int getKey()  // return key value of Book required by IntKeyed
	{
		return isbn;
	}
	
	public boolean setKey(int key)
	{
		if(key > 0)
		{
			this.isbn = key;
			return true;
		} else{
			return false;
		}
	}
	
	public boolean equals(Object bk) //????
	{
		Book b;
		if(bk instanceof Book)
		{
			b = (Book) bk; 
			if(title.equals(b.title) && author.equals(b.author))
			{
				if(isbn == b.isbn)
				{
					return true;
				}
			}else if(bk == null)
			{
				return false;
			}
		}
		return false;
	}	
	

	public String toString()
	{
		return String.format("ISBN: %d\t Title: %4s\t%-30s\n" , isbn, title, "Author: " + author);
	
	}

}
