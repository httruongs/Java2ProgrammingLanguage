
import java.util.Scanner;

public class Student {
	private final int MAX_COURSES = 10;
	private String studLast;
	private String studFirst;
	private int studNumber;
	private int courseCount;
	private Course[] courses; 
	
	
	public Student() {
		courses = new Course[MAX_COURSES];
	}
	public void inputStudentInfo()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the student First Name: ");
		studFirst = in.next();
		System.out.println("Enter the student Last Name: ");
		this.studLast = in.next();
		System.out.println("Enter the student number: ");
		this.studNumber = in.nextInt();
		
		do
		{
			System.out.println("Enter the number of courses to be input (1-10): ");
			this.courseCount = in.nextInt(); 
			if((this.courseCount > this.MAX_COURSES)||(this.courseCount < 0))
			{
				System.out.println("Invalid number of course input - try again");
			}
		}while(this.courseCount <= 0 && this.courseCount > this.MAX_COURSES);
		//in.close();	
			
			
		for (int i = 0; i < this.courseCount; i++)
		{
			courses[i] = new Course();
			courses[i].inputCourseInfo();
		}
	
	}
	
	public float calcGPA()
	{
		float totalCreditHours = 0.0f; 
		float totalGradePoints = 0.0f;
		for (int i = 0; i < this.courseCount; i++)
		{
			totalCreditHours += courses[i].getCrsHours();
			totalGradePoints += (courses[i].getCrsHours() * courses[i].calcGradePoints() ); 
		}
		return totalGradePoints / totalCreditHours;
	}
	public void printStudent()
	{
		System.out.println("Student: Name: " + this.studLast + ", "+ this.studFirst +" "+ "ID: "+ this.studNumber );
		for(int i = 0; i < this.courseCount; i++)
		{
			System.out.println(courses[i].toString());
			
		}
	}
}
