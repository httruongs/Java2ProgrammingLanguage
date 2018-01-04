import java.text.DecimalFormat;

public class SalaryEmployee extends Employee{
	private double salary;

	public SalaryEmployee(String first,String last,char gen,int empNum,boolean full,double sal)
	{
		super(first,last,gen,empNum,full);
		this.salary = sal;
	}
	@override
	public String toString()
	{
		DecimalFormat fmat = new DecimalFormat("$###,###,###.00");
		return super.toString()
				+ "\nSalary: " + fmat.format(salary);
					
	}
	@override
	public double calculateWeeklyPay()
	{
		double weeklyPay = salary / 52.0;
		return weeklyPay;
	}
	@override
	public void annualRaise()
	{
		this.salary = salary + (salary * 0.05);
	}
	@override
	public double holidayBonus()
	{
		double holidayWeeklyPay = salary * 0.05;
		return holidayWeeklyPay;
	}
	@override
	public void resetWeek()
	{
		
	}
}
