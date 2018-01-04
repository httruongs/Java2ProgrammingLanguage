
@SuppressWarnings("unchecked")

public class MyArrayList<E extends IntKeyed> {
	private final int DEFCAP = 50;
	private int origCap;
	private int numElements;
	private E[] list;

	public MyArrayList()
	{
		origCap = DEFCAP;
		list = (E[]) new IntKeyed[origCap];
	}
	public MyArrayList(int size)throws Exception
	{
		try{
			if(size >= 0)
			{
				origCap = size;
				list = (E[]) new IntKeyed[size];
			}
		}
		catch(Exception e){
			System.out.println("The list size is invalid!");
		}
	}
	
		
	public void addItem(E item)
	{
		if(origCap == numElements)          // if array is full
		{
			enlarge();
		}
		list[numElements] = item;
		numElements++;
	}
	public boolean removeItem(E item)
	{
		int position = findItemPos(item);
		if(position >= 0)
		{
			list[position] = list[numElements -1];		
			list[numElements -1] = null;
			numElements --;
			return true;
		}
		return false;
	}
	public boolean findItem(E item)
	{
		int position = findItemPos(item);
		for(int i =0; i < list.length; i++)
		{
			if(list[position].equals(list[i]))
			{
				return true;
			}
		}
		return false;
		
	}
	public <E> E findItemByKey(int key)
	{
		for(int i = 0; i <numElements; i++)
		{
			
			if(key==list[i].getKey()){
				return (E) list[i];	
			}
			
		}
		
		return null;
	}

	public boolean isEmpty()
	{
		if(list.length == 0)
		{
			return true;
		}
		return false;
	}
	public int lengthIs()
	{
		return numElements;
		
	}
	public void clear()
	{
		this.numElements = 0;
		list = (E[]) new IntKeyed[origCap];
	}
	//Return a string copy of all items currently stored in array.
	public String toString()
	{
		String variable = ""; 
		for(int i= 0; i <numElements; i++)
		{
			if(numElements != 0)
			{
				variable += list[i].toString();
			}else{
				System.out.println("*** NO ITEM ***");
			}
		}
		return variable;
	}
	public boolean equals(MyArrayList<E> otherList)
	{
		boolean areEqual = true;
		if((otherList == null) || (list.length != otherList.list.length));
		{
			areEqual = false;
		}
		if(otherList == this) //comparing an object to itself
		{
			areEqual = true;
		}else{				//compare all items in the lists for equality
			int x = 0;
			while (x < list.length && areEqual) 
			{
				if(! list[x].equals(otherList.list[x]))
				{
					areEqual = false;
				}
				x++;
			}
		}
		return areEqual;
	}
	public void enlarge()
	{
		E[] larger;
		larger = (E[]) new IntKeyed [list.length + origCap];
		//Copy all items from existing array 'list' to new array 'larger'
		
		//larger = list.clone();
		for ( int x = 0; x < list.length; x++)
		{
			larger[x] = list[x];
				
		}
		list = larger;
	}
	public int findItemPos(E item)
	{
		if (item != null) {
			for(int i = 0; i < numElements; i++)
			{
				if(item.getKey() == list[i].getKey())
				{
					return i;
				}
			}
		}
		
		return -1;
	}
}
	



