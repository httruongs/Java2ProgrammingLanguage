// Filename:    EmployeeDriver.java
// Defines the EmployeeDriver class containing the main method to drive the EmployeeList
// class. EmployeeList manages a list of Employees

import java.util.Scanner;

public class EmployeeDriver
{
    public static void main(String args[])
    {
        // variables to hold user input values
        int mainInput = -1; //Input for main menu
        int empType = -1;   //Input for employee type 
        int subInput1 = -1; //Input for submenu
        int subInput2 = -1; //Input for sub-submenu
        int empNum;			 //Input for employee number

        int index;          //Index of a specific Employee object in the list

        Scanner in = new Scanner(System.in);

        EmployeeList empMgr = new EmployeeList(); //The EmployeList object

        // options for the main menu
        String mainMenuOptions [] = {
            "Quit",
            "Add Employee",
            "Process Employees by Type Submenu",
            "Remove Employee",
            "Sort Employees",
            "Calculate Weekly Payout",
            "Calculate Bonus",
            "Annual Raises",
            "Reset Week"};
        // options for the 'process employee by type' menu
        String empTypeMenuOptions [] = {
            "Back",
            "Hourly Employees",
            "Salary Employees",
            "Commission Employees"};

        // create the main menu and employee Menu objects
        Menu mainMenu = new Menu (mainMenuOptions);
        Menu empTypeMenu = new Menu (empTypeMenuOptions);

        //Main control loop, keep coming back to the
        //Main menu after each selection is finished
        do {
            // Display all current employees in the list
            empMgr.listAll();

            //Run the main menu to display options, read, validate and return the user's choice
            System.out.println("Main Menu");
            mainInput = mainMenu.runMenu();

            //Perform the Main Menu action requested by the user
            switch(mainInput) {
                //Add Employee main menu option
                case 1:
                    // declare temporary variables for user input of employee values
                    String fName, lname;
                    char gender, f;
                    boolean ft = true;
                    double amount;

                    // get the employee type
                    do {
                        System.out.println("\n1. Hourly");
                        System.out.println("2. Salary");
                        System.out.println("3. Commission");
                        System.out.print ("Enter Choice: ");
                        empType = in.nextInt();
                        if (empType < 1 || empType > 3) {
                            System.out.println ("Invalid Choice. Try Again!");
                        }
                    }while (empType < 1 || empType > 3);

                    System.out.print("Enter Last Name: ");
                    lname = in.next();
                    System.out.print("Enter First Name: ");
                    fName = in.next();
                    System.out.print("Enter Gender: ");
                    gender = in.next().charAt(0);

                    // ask for employee number
                    // search employee list for existing employee with that number
                    // if number already exists, display an error message
                    int en_index = -1;
                    do {
                        System.out.print("Enter Employee Number: ");
                        empNum = in.nextInt();
                        // if empNum in range then check employee list to determine if 
                        // employee number already exists
                        if (empNum >= 1 && empNum <= 99999) {
                            en_index = empMgr.getIndex(empNum);
                            if (en_index != -1)
                                System.out.println ("Employee number already used - Choose a new number");
                        }
                        else
                            System.out.println ("Employee number must be between 1 and 99999 inclusive");

                    }while (en_index != -1 || (empNum < 1 || empNum > 99999));

                    // determine if employee is a full time employee
                    System.out.print("Full Time? (y/n): ");
                    f = in.next().charAt(0);
                    if(f == 'n' || f == 'N') {
                        ft = false;
                    }

                    // Prompt the user for the specific information required for the employee type
                    if(empType == 1) {
                        System.out.print("Enter wage: ");
                    }
                    else if(empType == 2) {
                        System.out.print("Enter salary: ");
                    }
                    else {
                        System.out.print("Enter rate: ");
                    }
                    amount = in.nextDouble();

                    // add the employee to the list of employees
                    if (empMgr.addEmployee(empType, fName, lname ,  gender, empNum, ft, amount))
                    {
                     System.out.println ("Employee Added to List");}
                    else
                        System.out.println ("Employee NOT added to List");
                    break;

                //Process Employee by Type main menu option
                case 2:
                    do {
                        //Call emptypeMenu to Display 'process employee by type' menu options and reads, 
                        //validates and returns the user's choice
                        empType = empTypeMenu.runMenu();

                        switch(empType) {
                            case 1:  // hourly employee
                                // display hourly employees and allow hours to be updated
                                do {
                                    do {
                                        System.out.println();
                                        empMgr.listHourly();
                                        System.out.println("1. Add Hours");
                                        System.out.println("0. Back");
                                        System.out.print("\nEnter Choice: ");
                                        subInput2 = in.nextInt();
                                        if(subInput2 < 0 || subInput2 > 1) {
                                            System.out.println("Invalid Choice! Choose Again.");
                                        }
                                    }while(subInput2 < 0 || subInput2 > 1);

                                    if( subInput2 == 1) {
                                        double hours = 0.0;
                                        System.out.println("Employee Number: ");
                                        empNum = in.nextInt();
                                        index = empMgr.getIndex(empNum);
                                        if(index != -1) {
                                            System.out.print("Enter Hours: ");
                                            hours = in.nextDouble();
                                            empMgr.increaseHours(index, hours);
                                        }
                                        else {
                                            System.out.println("Employee not found!");
                                        }

                                    }
                                }while(subInput2!= 0);
                                break;

                            case 2: // salary employee
                                // display salaried employees
                                do {
                                    System.out.println();
                                    empMgr.listSalary();
                                    System.out.println("0. Back");
                                    System.out.print("\nEnter Choice: ");
                                    subInput2 = in.nextInt();
                                    if(subInput2 != 0) {
                                        System.out.println("Invalid Choice! Choose Again.");
                                    }
                                }while(subInput2 != 0);
                                break;

                            case 3: // commission employee
                                // display commission employees and allow sales to be updated
                                do {
                                    do {
                                        System.out.println();
                                        empMgr.listCommission();
                                        System.out.println("1. Add Sales");
                                        System.out.println("0. Back");
                                        System.out.print("\nEnter Choice: ");
                                        subInput2 = in.nextInt();
                                        if(subInput2 < 0 || subInput2 > 1) {
                                            System.out.println("Invalid Choice! Choose Again.");
                                        }
                                    }while(subInput2 < 0 || subInput2 > 1);

                                    if( subInput2 == 1) {
                                        double sales = 0.0;
                                        System.out.println("Employee Number: ");
                                        empNum = in.nextInt();
                                        index = empMgr.getIndex(empNum);
                                        if(index != -1) {
                                            System.out.print("Enter Sales: ");
                                            sales = in.nextDouble();
                                            empMgr.increaseSales(index, sales);
                                        }
                                        else {
                                            System.out.println("Employee not found!");
                                        }
                                    }
                                }while(subInput2 != 0);
                                break;
                        }
                    }while(empType != 0);
                    break;

                //Remove Employee main menu option
                case 3:
                    System.out.print("Enter Employee Number to Remove: ");
                    empNum = in.nextInt();
                    // if empNum exists in the list - remove it
                    index = empMgr.getIndex(empNum);
                    if(index != -1)
                        empMgr.removeEmployee(index);
                    else
                        System.out.println ("Employee not found in the list");
                    break;

                //Sort Employees main menu option
                case 4:
                    // determine the sort method prefered by the user
                    do {
                        System.out.println("\n1. Sort by Employee Number");
                        System.out.println("2. Sort by Name");
                        System.out.print("Enter Choice: ");
                        subInput1 = in.nextInt();
                        if(subInput1 < 1 || subInput1 > 2) {
                            System.out.println("Invalid Input! Choose again.");
                        }
                    }while(subInput1 < 1 || subInput1 > 2);

                    // if the user requests to sort by employee number
                    //   call sortByEmpNumber
                    // else
                    //   call sortbyLastName
                    if(subInput1 == 1) {
                        empMgr.sortByEmpNumber();
                    }
                    else {
                        empMgr.sortByLastName();
                    }
                    break;

                //Calculate Total Weekly Payout main menu option
                case 5:
                    System.out.println();
                    System.out.printf("Total weekly payout is $%.2f\n", empMgr.calculatePayout());
                    break;

                //Calculate Total Bonus paid to all employees main menu option
                case 6:
                    double bonus = empMgr.holidayBonuses();
                    System.out.printf("Total holiday bonus payout is $%,.2f\n", bonus);
                    break;

                //Apply Annual Raises for all employees main menu option
                case 7:
                    empMgr.annualRaises();
                    System.out.println("Annual Raises applied.");
                    break;

                //Reset the weeks sales and hours-worked values back to zero main menu option
                case 8:
                    empMgr.resetAllWeeks();
                    System.out.println("Weekly values reset.");
                    break;

                //Exit (Quit) main menu option
                case 0:
                    System.out.println("\nThank you for using the Employee List!\n");
            }
        }while(mainInput != 0);
    }

}
