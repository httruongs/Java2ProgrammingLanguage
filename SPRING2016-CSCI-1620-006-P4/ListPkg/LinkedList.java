package ListPkg;
import ExceptionPkg.*;


public class LinkedList<T>
{
	private ListNode<T> firstNode;
	private ListNode<T> lastNode;
	private int numElements;
	private String name;
	
	public LinkedList()
	{
		this("list");
		firstNode = null;
		lastNode = null;
		numElements = 0;
	}
	
	public LinkedList(String listName)
	{
		name = listName;
		firstNode = null;
		lastNode = null;
		numElements = 0;
	}
	
	public void insertAtFront(T item)
	{
		ListNode<T>newNode;
		if (isEmpty()) //firstNode and lastNode refer to same object
		{
			newNode = new ListNode<T>(item, null);
			firstNode = lastNode = newNode;
		}else{  //firstNode refers to new node
			newNode = new ListNode<T>(item, firstNode);
			firstNode = newNode;
		}
		numElements++;
	}

	public void insertAtBack(T item)
	{
		if (isEmpty()) //firstNode and lastNode refer to same object
		{
			firstNode = lastNode = new ListNode<T>(item);
		}else{ //lastNode's nextNode refers to new node
			ListNode<T> temp = new ListNode<T>(item);
			lastNode.setNext(temp);
			lastNode = lastNode.getNext();
			lastNode.setNext(null);
		}
		numElements++;
		
	}
	
	public T removeFromFront() throws EmptyListException
	{
		if(isEmpty()) throw new EmptyListException(name);  //throw exception if List is empty
		T removedItem = firstNode.getData();  //retrieve data being removed
		
		//upgrade references firstNode and lastNode
		if(firstNode == lastNode)
		{
			firstNode = lastNode = null;
		}else{
			firstNode = firstNode.getNext();
		}
		numElements--;
		return removedItem; //return removed node data
	}
	
	public T removeFromBack() throws EmptyListException
	{
		if(isEmpty()) throw new EmptyListException(name); //throw exception if List is empty
		T removedItem = lastNode.getData();
		
		//upgrade references firstNode and lastNode
		if(firstNode == lastNode)
		{
			firstNode = lastNode = null;
		}else{  // locate new last node, search for new last node
			ListNode<T> current = firstNode;
			//System.out.println(lastNode.getData());
			
			//loop while current node does not refer to lastNode
			while (current.getNext() != lastNode)
			{
				
				current = current.getNext();
				
			}
			lastNode = current; //current is new lastNode
			lastNode.setNext(null);
		}
		numElements--;
		return removedItem;  //return removed node data
	}
	
	public void removeItemAt(int index) throws EmptyListException, IndexOutOfBoundsException
	{
		if(isEmpty()) {
			throw new EmptyListException(name);
		}
		
		if (index >= numElements || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(firstNode == lastNode) {
			firstNode = lastNode = null;
		}else if( index == 0){
			removeFromFront();
		}else if(index == numElements -1){
			removeFromBack();
		}else {
			ListNode<T> current = (ListNode<T>) firstNode;
			for(int i = 0; i < numElements-1; i ++)
			{
				//if(current.getData().equals(getItemAt(index))) {
				if(i == index-1)
				{
					current.setNext(current.getNext().getNext());
					numElements--;
					break;
				}
				current = current.getNext();
			}
		}
	}
	
	public T getItemAt(int index) throws EmptyListException, IndexOutOfBoundsException
	{
		if (isEmpty()) {
			throw new EmptyListException(name);
		}
		
		if (index >= numElements || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		ListNode<T> current = firstNode;
		
		for (int i = 0; i <= numElements - 1; i++) {
			if(current != null) {
				//T theData = current.getData();
				if(i == index) {
					return (T) current.getData();
				} else {
					current = current.getNext();
				}
			}
		}

		return null;	
	}
	
	public boolean findAndRemove(T item) throws EmptyListException
	{
		boolean found = false;
		if(!isEmpty())
		{
			//if item to remove is at the front
			//remove front item
			if(firstNode.getData().equals(item))
			{
				removeFromFront();
				found = true;
			//else look ahead to next node
			//search ahead to find the item, if item found in next node, remove the next node
			}else{
				ListNode<T>current = firstNode;
				for(int i = 0; i < numElements -1; i++)
				{
				if(current.getData() !=null && !found)
				{
					if(current.getData().equals(item))
					{
						found = true;
						//current.setNext(current.getNext().getNext());//remove
					}else{
						current = current.getNext();
					}
				}
				}
				if(found){ //current points to node prior to node to remove
					if(current.getNext() == lastNode)
						lastNode = current;
					else
						current.setNext(current.getNext().getNext());
						//current nextNode = current.nextNode.nextNode;
						numElements--;
				}
				
				}
		}
		else {
				throw new EmptyListException(name);
			
		}
		return found;
	}
	
	public int findItemPos(T item)
	{
		//find an Object in the list
		boolean found = false;
		if(!isEmpty()){
			
			ListNode<T> current = (ListNode<T>) firstNode;
			for(int i = 0; i <numElements; i++)
			{
				if(current != null && !found)
				{
					T theData = current.getData();
					if(theData.equals(item))
					{
						found = true;
						//System.out.println(i);
						return i;
						
					} else {
						current = current.getNext();
						//return -1;
					}
				}
			}
		}

		return -1;
	}
	
	public int lengthIs()
	{
		return numElements;
	}
	
	public void clear()
	{
		numElements = 0;
		firstNode = lastNode = null;
	}
	
	public void print()
	{
		if(isEmpty())
		{
			System.out.println("The list'Integer List' is Empty");
			return;
		}
		
		System.out.printf("The list 'Integer List' contains: ");
		ListNode<T> current = firstNode;
		
		//While not at end of list, output current node's data
		while(current != null)
		{
			System.out.printf("%s ", current.getData());
			current = current.getNext();
		}
		System.out.println("\n");
	}
	
	public boolean isEmpty()
	{
		return firstNode == null; //return true if list is empty
	}
}
