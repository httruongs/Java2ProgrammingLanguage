
import java.text.DecimalFormat;
import java.util.Scanner;

public class Course {
	private String crsId;
	private int crsNum;
	private int crsSection;
	private float crsHours;
	private String crsGrade;
	
	public Course(){
	}
	
	public String getCrsId() 
	{
		return crsId;
	}
	
	public int getCrsNum()
	{
		return crsNum;
	}
	
	public int getCrsSection()
	{
		return crsSection;
	}
	public float getCrsHours()
	{ 
		return crsHours;
	}
	public String getCrsGrade()
	{
		return crsGrade;
	}
	public void inputCourseInfo()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the Course ID: ");
		this.crsId = in.nextLine().toUpperCase();
		
		int counter = 0;
		do {
			
			String msg = counter == 0 ? "Enter the Course Number" : "Invalid course number input - Value must be greater than 0";
			System.out.println(msg + ": ");
		    this.crsNum = in.nextInt();
		    counter = counter + 1;
		} while (this.crsNum <= 0);
		
		
		int count = 0;
		do {
			
			String msg = count == 0 ? "Enter the Section Number" : "Invalid course section number input - "
					+ "Value must be greater than or equal to 0";
			System.out.println(msg + ": ");
		    this.crsSection = in.nextInt();
		    count++;
		} while (this.crsSection < 0);
		
		int number = 0;
		do {
			
			String msg = number == 0 ? "Enter the Credit Hours" : "Invalid number of credit hours input-"
					+ " Value must be greater than or equal";
			System.out.println(msg + ": ");
		    this.crsHours = in.nextInt();
		    number++;
		} while (this.crsHours <= 0);
		
		
		System.out.println("Enter crsGrade: ");
		this.crsGrade = in.next().toUpperCase();
		//in.close();
	}
	public float calcGradePoints()
	{
		float gradePoint = 0.0f;
		if (crsGrade.equals("A+") || crsGrade.equals("A"))
		{
			gradePoint = 4.0f; 
		}
		else if(crsGrade.equals("A-"))
		{
			gradePoint = 3.67f;	
		}
		else if(crsGrade.equals("B+"))
		{
			gradePoint = 3.33f;
		}
		else if(crsGrade.equals("B"))
		{
			gradePoint = 3.0f;
		}
		else if(crsGrade.equals("B-"))
		{
			gradePoint = 2.67f;
		}
		else if(crsGrade.equals("C+"))
		{
			gradePoint = 2.33f;
		}
		else if(crsGrade.equals("C"))
		{
			gradePoint = 2.0f;
		}
		else if(crsGrade.equals("C-"))
		{
			gradePoint = 1.67f;
		}
		else if(crsGrade.equals("D+"))
		{
			gradePoint = 1.33f;
		}
		else if(crsGrade.equals("D"))
		{
			gradePoint = 1.0f;
		}
		else if(crsGrade.equals("D-"))
		{
			gradePoint = 0.67f;
		}
		else //if(crsGrade.equals("F"))
		{
			gradePoint = 0.0f;
		}
		return gradePoint;
	}	
	public String toString()
	{
		DecimalFormat crsSectionFormat = new DecimalFormat("000");
		DecimalFormat crsHourFormat = new DecimalFormat(".00");
		
		String output = "Course: " + this.crsId + " " + this.crsNum + "-";
			output += crsSectionFormat.format(this.crsSection);
			output += " Hours: " + crsHourFormat.format(this.crsHours);
			output += " Grade Earned: " + this.crsGrade;
		return output;
	}
}
