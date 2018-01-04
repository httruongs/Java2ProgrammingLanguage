package ListPkg;

public class ListNode<T> {
	private T data;
	private ListNode<T> nextNode;
	
	public ListNode(T object)
	{
		data = object;
		nextNode = null;
	}
	
	public ListNode(T object, ListNode<T> node)
	{
		data = object;
		nextNode = node;
	}
	
	public void setData(T object)
	{
		data = object;
	}

	public T getData()
	{
		return data;
	}
	
	public void setNext(ListNode<T> next)
	{
		nextNode = next;
	}
	
	public ListNode<T> getNext()
	{
		return nextNode;
	}
}
