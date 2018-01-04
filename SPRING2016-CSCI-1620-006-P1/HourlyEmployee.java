import java.text.DecimalFormat;
public class HourlyEmployee extends Employee {
	private double wage;
	private double hoursWorked = 0;

	public HourlyEmployee(String first,String last,char gen,int empNum,boolean full,double hWage)
	{
		super(first,last,gen,empNum,full);
		this.wage = hWage; 
	}
	@override 
	public String toString()
	{
		DecimalFormat fmat = new DecimalFormat("$###,###,###.00");
		return super.toString()
				+ "\nWage: " + fmat.format(wage)
				+ String.format("\nHours Worked:%.2f",hoursWorked);
		
	}
	public void addWorkHours(double hours)
	{
		if (hours < 0)
		{
			System.out.println("Hours worked cannot be negative");
		}else{
			this.hoursWorked = hours;
		}
	}
	@override
	public double calculateWeeklyPay()
	{
		double weeklyPay = 0.0;
		if(hoursWorked <= 40)
		{
		weeklyPay = wage * hoursWorked;
		}else{
		
		weeklyPay = (wage*hoursWorked) + ((hoursWorked - 40)*1.5* wage);
		} 
		return weeklyPay;
	}
	@override
	public void annualRaise()
	{
		this.wage = wage + (wage * 0.05);
				
	}
	@override
	public double holidayBonus()
	{
		double holidayWeeklyPay = wage * 40;
		return holidayWeeklyPay;
	}
	@override
	public void resetWeek()
	{
		this.hoursWorked = 0;
	}
}
