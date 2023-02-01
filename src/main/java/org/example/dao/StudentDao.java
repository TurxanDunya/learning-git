package org.example.dao;

import org.example.config.HibernateConfiguration;
import org.example.entity.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentDao {

    private Session session;

    public Student getById(Integer id) {
        Transaction transaction = null;
        Student student = null;

        try {
            session = HibernateConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            student = (Student) session.get(Student.class, id);
            transaction.commit();
//            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return student;
    }

    // getAllUsers

    public void save(Student student) {
        Transaction transaction = null;

        try {
            session = HibernateConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            System.out.println("Student has been saved successfully with id: " + student.getId());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void update(Student student) {
        Transaction transaction = null;

        try {
            session = HibernateConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("UPDATE Student u SET u.name= :name, u.surname= :surname, u.age= :age WHERE u.id= :id");
            query.setParameter("id", student.getId());
            query.setParameter("name", student.getName());
            query.setParameter("surname", student.getSurname());
            query.setParameter("age", student.getAge());
            query.executeUpdate();

            transaction.commit();
            System.out.println("Student has been updated successfully with id: " + student.getId());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void delete(Integer id) {
        Transaction transaction = null;
        Student student = null;

        try {
            session = HibernateConfiguration.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            student = (Student) session.get(Student.class, id);
            session.delete(student);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        System.out.println("Student has been removed successfully with id: " + student.getId());
    }

}
