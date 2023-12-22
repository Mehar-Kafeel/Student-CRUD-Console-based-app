package com.spring.orm.dao;

import com.spring.orm.entities.Student;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/* Student Data operation  using hibernate template
It includes CRUD operations */
public class StudentDao {

    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    public StudentDao() {
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    //inserting a student in student_details table
    @Transactional
    public int insert(Student student) {
        return (Integer)hibernateTemplate.save(student);
    }

    //get the single student based on id
    public Student getStudent(int studentId) {
        return hibernateTemplate.get(Student.class, studentId);
    }

    //get all students
    public List<Student> getAllStudents() {
      return this.hibernateTemplate.loadAll(Student.class);
    }

    //deleting student using id
    @Transactional
    public void deleteStudent(int studentId) {
        Student student =this.hibernateTemplate.get(Student.class, studentId);
        this.hibernateTemplate.delete(student);
    }

    @Transactional
    //updating student
    public void updateStudent(Student student) {
        this.hibernateTemplate.update(student);
    }


}
