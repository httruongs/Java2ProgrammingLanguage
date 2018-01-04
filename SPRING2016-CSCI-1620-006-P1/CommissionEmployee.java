import java.text.DecimalFormat;

public class CommissionEmployee extends Employee{
	private double sales;
	private double rate;
	
	public CommissionEmployee(String first,String last,char gen,int empNum,boolean full,double cRate) {
		super(first,last,gen,empNum,full);
		this.rate = cRate/100.0;		
		this.sales = 0;	
		
	}
	@override
	public String toString()
	{
		DecimalFormat fmat = new DecimalFormat("$###,###,###.00");
		return super.toString()
				+String.format("\nRate: %.2f%%",rate*100)
				+ "\nSales: " + fmat.format(sales);
	}
	public void increaseSales(double amt)
	{
		if (amt >=0)
		{
		this.sales = amt;
		}else {
			System.out.println("Invalid sales amount entered - sales cannot be negative");
		}
	}
	@override
	public double calculateWeeklyPay()
	{
		double weeklyPay = sales * rate;
		return weeklyPay;
	}
	@override
	public void annualRaise()
	{
		this.rate = rate + 0.04;
		
	}
	@override
	public double holidayBonus()	
	{
		return 0.0;
	}@override
	public void resetWeek()
	{
		this.sales = 0.0;
	}
}

