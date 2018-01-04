

//Filename: IntKeyed.java
//Class defining an Interface implementing the 'IntKeyed' property of classes.
//This interface defines an integer attribute that will be used as the key value of a class.

//interface requirements:
//The class implementing the hasIntKey interface, must have an integer
//attribute that will hold the key value of the object
public interface IntKeyed 
{
    // Method: getKey()
    // Returns the value stored in the attribute key
    public abstract int getKey();

    // Allows the key value to be set based on the value passed in the parameter
    // If k > 0, set key = k and return true; else do not change key and return false
    public abstract boolean setKey(int k);
}

