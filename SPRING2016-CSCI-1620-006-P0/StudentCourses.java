

import java.text.DecimalFormat;

public class StudentCourses {
        
	public static void main(String[] args)
	{
		Student student = new Student();
		student.inputStudentInfo();
		student.printStudent();
				
		DecimalFormat crscrsGradeFormat = new DecimalFormat(".00");
		System.out.println("\n" + "Student GPA = " + crscrsGradeFormat.format(student.calcGPA()));
	}
}
