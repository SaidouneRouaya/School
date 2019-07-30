package DAO;

import Entities.Student;

import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StudentImpl implements StudentDAO {

    public StudentImpl() {
    }

    public void init() {
    }

    public void addStudent(Student student) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Student> students = null;

        try {
            tx = session.beginTransaction();
            students = session.createQuery("from Student ").list();

            tx.commit();

          /*  for (Student student:students){
                student.formatDate();
            }*/

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return students;
    }

    public void deleteStudent(int id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.delete(student);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateStudent(int id,Student st) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, id);
            student.updateStudent(st);
            session.update(student);
            System.out.println("student update date: "+student.getSubscriptionDate());

            System.out.println("add done");
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public Student getStudentByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Student student = null;

        try {
            tx = session.beginTransaction();
            student =  session.get(Student.class, id);

            System.out.println("student get by id date: "+student.getSubscriptionDate());
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return student;
    }


}