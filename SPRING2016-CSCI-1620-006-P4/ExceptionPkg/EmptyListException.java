// Filename: EmptyListException.java
// Defines the Exception - EmptyListException that can be thrown
// when an operation is requested that is not supported for an empty list
//
// This class will be stored in the ExceptionPkg package.

package ExceptionPkg;
public class EmptyListException extends RuntimeException
{
     // constructor with no parameter
       public EmptyListException ()
       {
               this ("List");
       }
       // constructor with .String. parameter
       public EmptyListException (String name) {
               // call super class constructor
               super (name + " is empty");
       }
}
