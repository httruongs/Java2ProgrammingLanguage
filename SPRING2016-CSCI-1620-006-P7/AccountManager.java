import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.io.ObjectInputStream;

public class AccountManager {
    private AccountRecordSerializable[] accounts;
    private int numRecords;
    private final int MAX_RECORDS = 100;
    
    // constructor
    public AccountManager() {
        accounts = new AccountRecordSerializable[MAX_RECORDS];
        numRecords = 0;
    }
    
    //@SuppressWarnings("ConvertToTryWithResources")
    public void readRecords(String file) {
        File f = new File(file);
        ObjectInputStream input = null;
        AccountRecordSerializable record;

        try {
            input = new ObjectInputStream(new FileInputStream(f));
        } catch (IOException e) {
           System.err.println("No Existing Records");
        }

        if (f.exists()) {
            try {
                while (input != null) {
                    record = (AccountRecordSerializable) input.readObject();
                    //System.out.println(record);
                    accounts[numRecords] = record;
                    numRecords++;
                }
            } catch (EOFException e) {
                e.getMessage();
            } catch(ClassNotFoundException e) {
                System.err.println("Unable to create object.");
            } catch(IOException e) {
                System.out.println("Error during read from file");
            }
            
            try {
                if(input!= null) {
                    input.close();
                }
            } catch(IOException e) {
                System.err.println("Error closing file");
            }
        }
    }

    public void writeRecords(String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
        try {
            fos = new FileOutputStream(new File(file));
            oos = new ObjectOutputStream(fos);
	    
            for (int i = 0; i < numRecords; i++) {
                oos.writeObject(accounts[i]);
            }
        } catch(IOException e) {
            System.err.println("Error writing file");
        }
        
        try {
            if (oos!= null) {
                oos.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
    } 
    
    public void printRecords() {
        AccountRecordSerializable account;
            
        if (numRecords == 0){
            System.out.println("No existing records");
        } else {
            System.out.println("");
            System.out.printf("%-10s%-12s%-12s%10s\n", "Account", "First Name", "Last Name", "Balance");
            for (int i = 0; i < numRecords; i++) {
                account = accounts[i];
                System.out.format("%-10d%-12s%-12s%10.2f\n", account.getAccount(), account.getFirstName(), account.getLastName(), account.getBalance());
            }
        }
    }
    
    public void addRecord(int accountNumber, double balance) {
        Scanner s = new Scanner(System.in);
        AccountRecordSerializable account;
        String FirstName, LastName;

        if (balance > 0) {
            System.out.print("Enter First Name: ");
            FirstName = s.nextLine();
            System.out.print("Enter Last Name: ");
            LastName = s.nextLine();
            account = new AccountRecordSerializable(accountNumber, FirstName, LastName, balance);
            accounts[numRecords] = account;
            numRecords++;
            
            System.out.format("New account added:\n%-10d%-12s%-12s%10.2f\n", account.getAccount(), FirstName, LastName, account.getBalance());
        } else {
            System.err.println("Invalid initial balance.");
        }
    }
    
    public void sortRecords() {
        boolean flag = true;
        
        AccountRecordSerializable temp;
        
        while ( flag ) {
            flag = false;
            for (int j = 0; j < numRecords; j++) {
                if (accounts[j+1] != null && accounts[j].getAccount() > (accounts[j+1].getAccount())) { // ascending sort
                    temp = accounts [ j ];
                    accounts [j] = accounts [j+1];     // swapping
                    accounts [j+1] = temp; 
                    flag = true;
                } 
            } 
        }
    }

    public int findRecord(int accountNumber) {
        for (int i = 0; i < numRecords; i++) {
            if (accounts[i].getAccount() == accountNumber) {
                return i;
            }
        }
        return -1;
    }
    
    public void updateTransactions(String file) {
        int accountNumber;
        double adjustedBalance;
        
        try {
            Scanner input = new Scanner(new File(file));  
            
            while (input.hasNext()) {
                try {
                    accountNumber = input.nextInt();
                    adjustedBalance = input.nextDouble();
                    int index = findRecord(accountNumber);                   
                    
                    if (index < 0) {
                        // add new record
                        System.out.println("New account number " + accountNumber + " found!");
                        addRecord(accountNumber, adjustedBalance);
                    } else {
                        // update records
                        accounts[index].setBalance(accounts[index].getBalance()
                                                    + adjustedBalance);
                    }
                } catch (FormatterClosedException e) {
                    e.getMessage();
                }
            }
	    input.close();
            
            // sort records
            this.sortRecords();
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }
}
