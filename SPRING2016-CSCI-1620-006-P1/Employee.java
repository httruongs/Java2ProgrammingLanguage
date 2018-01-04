
public abstract class Employee {

	protected String firstName;
	protected String lastName;
	protected boolean fulltime = true;
	protected char gender = 'F';
	protected int employeeNum;

	public Employee(String first,String last,char gen,int empNum,boolean full) {
		this.firstName = first;
		this.lastName = last;
		this.fulltime = full;
		this.gender = gen;
		this.employeeNum = empNum;
		
	}
	public int getEmployeeNumber()
	{
		return employeeNum;
	}
	public void setEmployeeNum(int empNum)
	{
		if (empNum > 0)
		{
			this.employeeNum = empNum;
		}
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String toString()
	{
		if(this.gender != 'M' || this.gender != 'F')
		{
			this.gender = 'F';
		}
		return "Number: "+this.employeeNum+" "+"Name: "+this.lastName+", "+this.firstName+" "+
		"Gender: "+this.gender+" "+"Status: "+ ((this.fulltime)?"Full Time":"Part Time");
	}
	public abstract double calculateWeeklyPay();
	
	public abstract void annualRaise();
	
	public abstract double holidayBonus();
	
	public abstract void resetWeek();
	
	
}
