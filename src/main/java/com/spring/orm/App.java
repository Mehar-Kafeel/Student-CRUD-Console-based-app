package com.spring.orm;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


/*Console based app
This app let the user perform CRUD operation in
student_details table */
public class App 
{
    public static void main( String[] args )
    {
        //getting application context xml file
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        StudentDao studentDao = context.getBean("studentDao", StudentDao.class);

        //creating bufferedReader object to take input from user
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        System.out.println( "********** STUDENT CRUD APP *************************" );

        //condition for while loop
        boolean condition=true;
        while(condition) {
            System.out.println("PRESS 1 to add new student");
            System.out.println("PRESS 2 to display all students");
            System.out.println("PRESS 3 to view single student details");
            System.out.println("PRESS 4 to delete a student from records");
            System.out.println("PRESS 5  to update a student");
            System.out.println("PRESS 6  to exit");

            try {
                //taking input from user for options
                int input = Integer.parseInt(br.readLine());

                switch (input){
                    case 1:
                        //taking inputs from user: student id, name and city
                        System.out.println("Enter student id: ");
                        int studentId = Integer.parseInt(br.readLine());

                        System.out.println("Enter student name: ");
                        String studentName = br.readLine();

                        System.out.println("Enter student city: ");
                        String studentCity = br.readLine();

                        //creating student object
                        Student student = new Student(studentId, studentName, studentCity);

                        //inserting student
                        int id = studentDao.insert(student);
                        System.out.println(" student added with id: " + id);
                        System.out.println("*******************************");
                        break;
                    case 2:
                        //printing all the students
                        List<Student> students = studentDao.getAllStudents();
                        System.out.println("*******************************");

                        for(Student s : students) {
                            System.out.println("Id: " + s.getStudentId());
                            System.out.println("Name: " + s.getStudentName());
                            System.out.println("City: " + s.getStudentCity());
                            System.out.println("------------------------------");
                        }
                        System.out.println("*******************************");
                        break;
                    case 3:
                        //getting single student based on student id
                        System.out.println("Enter id of student:");
                        studentId = Integer.parseInt(br.readLine());

                        //getting student and printing
                        student = studentDao.getStudent(studentId);
                        System.out.println("Id: " + student.getStudentId());
                        System.out.println("Name: " + student.getStudentName());
                        System.out.println("City: " + student.getStudentCity());
                        System.out.println("------------------------------");
                        break;
                    case 4:
                        //deleting a student record based on id
                        System.out.println("Enter Student id to delete record: ");
                        studentId = Integer.parseInt(br.readLine());

                        //deleting student
                        studentDao.deleteStudent(studentId);
                        System.out.println("Record deleted of student with id: " + studentId);
                        System.out.println("------------------------------");
                        break;
                    case 5:
                        //updating student based on id
                        System.out.println("Enter student id to update student record: ");
                        studentId = Integer.parseInt(br.readLine());

                        //updating name
                        System.out.println("Enter name: ");
                        studentName = br.readLine();

                        //updating city
                        System.out.println("Enter city: ");
                        studentCity = br.readLine();

                        //creating student object
                        student = new Student(studentId, studentName, studentCity);

                        //updating student in db
                        studentDao.updateStudent(student);
                        System.out.println("Student record updated");
                        System.out.println("------------------------------");
                        break;
                    case 6:
                        condition = false;
                        System.out.println("********** Good Bye ******************");
                        break;

                }
            } catch (Exception ex) {
                System.out.println("Invalid input" + ex.getMessage());
            }
        }
    }
}
