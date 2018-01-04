import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeList {
	private final int EMPLOYEES_MAX = 50;
	private int numEmployees = 0;
	Employee[] employees;

	public EmployeeList() {
		employees = new Employee[EMPLOYEES_MAX];
	}
	
	public int getIndex(int empNum)
	{
		int retVal = -1;
		for(int i = 0; i < numEmployees ; i++)
		{
			if(employees[i].getEmployeeNumber() == empNum)
			{
				retVal = i;
				break;
			}
		}
		return retVal;
	}
	public void listAll()
	{
		if(numEmployees <= 0)
		{
			System.out.println("No Employees");
		}
		for(int i = 0;i< numEmployees;i++)
		{
			System.out.println(employees[i]);
		}
	}
	public void listHourly()
	{
		for(Employee e:employees)
		{
			if(e instanceof HourlyEmployee)
			{
				System.out.println((HourlyEmployee)e);
			}
		}
	}
	public void listSalary()
	{
		for(Employee e:employees)
		{
			if(e instanceof SalaryEmployee)
			{
				System.out.println((SalaryEmployee)e);
			}
		}
	}
	public void listCommission()
	{
		for(Employee e:employees)
		{
			if(e instanceof CommissionEmployee)
			{
				System.out.println((CommissionEmployee)e);
			}
		}
		
	}
	public void increaseHours(int index,double amount)
	{		
		for(Employee e:employees)
			{
				if(e instanceof HourlyEmployee)
				{
					((HourlyEmployee) employees[index]).addWorkHours(amount);
				}
			}
	}
	public void increaseSales(int index,double amount)
	{
		for(Employee e:employees)
		{
			if(e instanceof CommissionEmployee)
			{
				((CommissionEmployee) employees[index]).increaseSales(amount);
			}
		}
	}
	public boolean addEmployee(int empType,String first,String last,char gen,int empNum,boolean full,double amount)
	{
		if(numEmployees >= EMPLOYEES_MAX)
		{
			System.out.println("The array is full!");
			return false;
		} else {
			if (empType == 1) {
				employees[numEmployees] = new HourlyEmployee(first, last, gen, empNum, full, amount);
				numEmployees++;
				
			} else if (empType == 2) {
				employees[numEmployees] = new SalaryEmployee(first, last, gen, empNum, full, amount);
				numEmployees++;
			} else if (empType == 3) {
				employees[numEmployees] = new CommissionEmployee(first, last, gen, empNum, full, amount);
				numEmployees++;
			}
			return true;
		}
	}
	public void removeEmployee(int index)
	{
		//employees = ArrayUtils.removeElement(employees, index);
		//ArrayList<Employee> empList;
		employees[index] = employees[numEmployees-1];
		employees[numEmployees-1] = null;
		//empList = (ArrayList<Employee>) Arrays.asList(employees);
		//empList.remove(index);
		numEmployees--;
		//employees = new Employee[empList.size()];
	}
	public void sortByEmpNumber()
	{
		boolean flag = true;
		Employee temp;
		
		while ( flag )
		{
			flag= false;    //set flag to false awaiting a possible swap
			for( int j=0;  j < numEmployees -1;  j++ )
			{
				if ( employees[ j ].employeeNum > employees[j+1].employeeNum )   // change to > for ascending sort
				{
					temp = employees[ j ];                //swap elements
					employees[ j ] = employees[ j+1 ];
					employees[ j+1 ] = temp;
					flag = true;              //shows a swap occurred  
				} 
			} 
		} 
	    
	}
	public void sortByLastName()
	{
		boolean flag = true;
		Employee temp;
		
		while ( flag )
		{
			flag = false;
			for ( int j = 0;  j < numEmployees -1;  j++ )
			{
				if (employees[j].getLastName().compareTo(employees[j+1].getLastName()) > 0)
				{                                             // ascending sort
					temp = employees [ j ];
					employees [ j ] = employees [ j+1 ];     // swapping
					employees [ j+1 ] = temp; 
					flag = true;
				} 
			} 
		} 
	}
	public double holidayBonuses()
	{
		double bonusAmount = 0.0;
		double totalBonus = 0.0;
		for(Employee e:employees)
		{
			DecimalFormat fmat = new DecimalFormat("$###,###,###.00");
			if(e instanceof HourlyEmployee)
			{
				System.out.println((HourlyEmployee)e);
				bonusAmount = ((HourlyEmployee)e).holidayBonus();
				totalBonus += bonusAmount;
				//fmat.format(totalBonus);
				System.out.println("Employee Bonus: "+ fmat.format(bonusAmount));
							
			}else if(e instanceof SalaryEmployee)
			{
				System.out.println((SalaryEmployee)e);
				bonusAmount = ((SalaryEmployee)e).holidayBonus();
				totalBonus += bonusAmount;
				System.out.println("Employee Bonus: "+ fmat.format(bonusAmount));
			}else if(e instanceof CommissionEmployee)
			{
				System.out.println((CommissionEmployee)e);
				bonusAmount = ((CommissionEmployee)e).holidayBonus();
				totalBonus += bonusAmount;
				System.out.println("Employee Bonus: "+ fmat.format(bonusAmount));
			}
		}
		return totalBonus;
	}
	public void annualRaises()
	{
		for(Employee e:employees)
		{
			if(e instanceof HourlyEmployee)
			{
				((HourlyEmployee)e).annualRaise();
			}else if(e instanceof SalaryEmployee)
			{
				((SalaryEmployee)e).annualRaise();
			}else if(e instanceof CommissionEmployee)
			{
				((CommissionEmployee)e).annualRaise();
			}
		}
	}
	
	public double calculatePayout()
	{
		double totalPayout = 0.0;
		
		for (Employee e:employees)
		{
			if(e instanceof HourlyEmployee)
			{
				totalPayout += ((HourlyEmployee) e).calculateWeeklyPay();
			}else if(e instanceof SalaryEmployee)
			{
				totalPayout += ((SalaryEmployee) e).calculateWeeklyPay();
			}else if(e instanceof CommissionEmployee)
			{
				totalPayout += ((CommissionEmployee) e).calculateWeeklyPay();
			}
		}
		
		return totalPayout;
	}
	
	public void resetAllWeeks()
	{
		for(Employee e:employees)
		{
			if(e instanceof HourlyEmployee)
			{
				((HourlyEmployee)e).resetWeek();;
			}else if(e instanceof SalaryEmployee)
			{
				((SalaryEmployee)e).resetWeek();;
			}else if(e instanceof CommissionEmployee)
			{
				((CommissionEmployee)e).resetWeek();
			}
		}
	}
}
