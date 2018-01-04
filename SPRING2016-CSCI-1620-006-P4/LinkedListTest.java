// Filename: LinkedListTest.java

//
// This file contains a main method to create an object of type LinkedList and
// test all the methods defined for the LinkedList class.

// Import the LinkedList and EmptyListException classes from their appropriate packages
import ListPkg.LinkedList;
import ExceptionPkg.EmptyListException;

import java.util.Scanner;

public class LinkedListTest
{
    public static void main(String[] args)
    {

        // Create the linked list object 'myList' to hold Integer objects and print the list
        LinkedList<Integer> myList = new LinkedList<Integer>("Integer List");

        System.out.println();
        myList.print();
        System.out.println();

        // Add five items to the front of the list and print the list
        System.out.println("Adding five Integers to the front of the list");
        for(int x = 0; x < 5; x++)
        {
            myList.insertAtFront(x);
        }
        printLengthAndList(myList);

        // Add five items to the back of the list and print the list
        System.out.println("Adding five Integers to the back of the list");
        for(int x = 0; x < 5; x++)
        {
            myList.insertAtBack(x);
        }
        printLengthAndList(myList);

        // remove an item from the front and from the back of the list
        // print the list
        System.out.println("Removing an item from the front of the list");
        try
        {
            myList.removeFromFront();
        }
        catch(EmptyListException ELE)
        {
            System.out.print(ELE.getMessage());
        }
        printLengthAndList(myList);
        System.out.println("Removing an item from the back of the list");
        try
        {
            myList.removeFromBack();
        }
        catch(EmptyListException ELE)
        {
            System.out.print(ELE.getMessage());
        }
        printLengthAndList(myList);

        // Try to remove the Integer value 4 from the list
        // catch the EmptyListException if thrown
        // display the list
        System.out.println ("Attempt to remove a value not in the list: 4");
        try
        {
            if(myList.findAndRemove(4))
            {
                System.out.println("4 removed");
            }
            else
            {
                System.out.println("4 not removed");
            }
        }
        catch(EmptyListException ELE)
        {
            System.out.println(ELE.getMessage());
        }
        printLengthAndList(myList);

        // Try to remove the Integer value 0 from the list
        // catch the EmptyListException if thrown
        // display the list
        System.out.println ("Attempt to remove a value in the list: ");
        try
        {
            if(myList.findAndRemove(0))
            {
                System.out.println("0 removed");
            }
            else
            {
                System.out.println("0 not removed");
            }
        }
        catch(EmptyListException ELE)
        {
            System.out.println(ELE.getMessage());
        }
        printLengthAndList(myList);

        // Try to find position of an item not in the list
        System.out.println("Attempt to find the position of an item not in the list - 5");
        int position = myList.findItemPos(5);
        if(position!=-1)
        {
            System.out.printf("Value of 5 found at %d\n", position);
        }
        else
        {
            System.out.println("Value of 5 not found");
        }

        // Try to find position of an item in the list
        System.out.println("\nAttempt to find the position of an item in the list - 0");
        position = myList.findItemPos(0);
        if(position!=-1)
        {
            System.out.printf("Value of 0 found at position %d in the list\n", position);
        }
        else
        {
            System.out.println("Value of 0 not found");
        }
 
        // Remove the item at 'position' in the list
        System.out.printf("\nValue at position %d being removed\n", position);
        myList.removeItemAt(position);
        printLengthAndList(myList);

        // Remove the first element in the list - item at position 0
        System.out.println("Removing element at position 0");
        myList.removeItemAt(0);
        printLengthAndList(myList);

        // Remove the first element in the list - item at the front
        System.out.println("Removing from front");
        myList.removeFromFront();
        printLengthAndList(myList);

        // get the object at position 2 in the list
        System.out.println("Getting value at position 2");
        Integer temp = myList.getItemAt (2);
        System.out.printf("Value at position is %d\n", temp);

        // Test the 'getItemAt' method for the IndexOutOfBoundsException
        System.out.println("\nBegin Testing IndexOutOfBoundsException");
        try
        {
            myList.getItemAt(4);
        }
        catch(IndexOutOfBoundsException IOBE)
        {
            System.out.println("\tgetItemAt (index too large): " + IOBE);
        }
        try
        {
            myList.getItemAt(-1);
        }
        catch(IndexOutOfBoundsException IOBE)
        {
            System.out.println("\tgetItemAt (index too small): " + IOBE);
        }


        // Test the 'remove' method for the IndexOutOfBoundsException
        try
        {
            myList.removeItemAt(4);
        }
        catch(IndexOutOfBoundsException IOBE)
        {
            System.out.println("\tremoveItemAt (index too large): " + IOBE);
        }

        try
        {
            myList.removeItemAt(-1);
        }
        catch(IndexOutOfBoundsException IOBE)
        {
            System.out.println("\tremoveItemAt (index too small): " + IOBE);
        }

        System.out.println("End of IndexOutOfBoundsException test\n");

        // Clear list 
        System.out.println("If list not empty - Clear the list");
        if(!myList.isEmpty())
        {
            myList.clear();
        }
        printLengthAndList(myList);

        System.out.println("\nBegin Testing EmptyListException");

        // test removeFromFront, removeFromBack, findAndRemove, removeItemAt and getItemAt with 
        // the empty list
        try
        {
            myList.removeFromFront();
        }
        catch(EmptyListException ELE)
        {
            System.out.println("\tremoveFromFront: " + ELE.getMessage());
        }

        try
        {
            myList.removeFromBack();
        }
        catch(EmptyListException ELE)
        {
            System.out.println("\tremoveFromBack: " + ELE.getMessage());
        }

        try
        {
            myList.findAndRemove(0);
        }
        catch(EmptyListException ELE)
        {
            System.out.println("\tfindAndRemove: " + ELE.getMessage());
        }

        try
        {
            myList.removeItemAt(0);
        }
        catch(EmptyListException ELE)
        {
            System.out.println("\tremoveItemAt: " + ELE.getMessage());
        }

        try
        {
            myList.getItemAt(0);
        }
        catch(EmptyListException ELE)
        {
            System.out.println("\tgetItemAt: " + ELE.getMessage());
        }

        System.out.println("End of EmptyListException test\n");
    }

    // Method: printLengthAndList (LinkedList<Integer> list)
    // Method to print both the length of the list and the
    // contents of the list to the screen
    public static void printLengthAndList(LinkedList<Integer> list)
    {
        System.out.printf("Length is: %d\n", list.lengthIs());
        list.print();
    }
}
