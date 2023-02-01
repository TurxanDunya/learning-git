package org.example;

import org.example.dao.StudentDao;
import org.example.entity.Student;

public class Main {
    public static void main(String[] args) {
//        Student student = new Student();
//        student.setId(1);
//        student.setName("Samir");
//        student.setSurname("Memmedli");
//        student.setAge(65);

        StudentDao studentDao = new StudentDao();
        Student student = studentDao.getById(1);
        System.out.println(student);
    }
}
