package DAO;

import Entities.Student;
import Entities.Teacher;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherImpl implements TeacherDAO{

    public void init(){

    }

    public void addTeacher(Teacher teacher) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(teacher);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    public List<Teacher> getAllTeachers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Teacher> teachers = null;

        try {
            tx = session.beginTransaction();
            teachers = session.createQuery("from Teacher ").list();
            for (Teacher teacher: teachers){

                //Hibernate.initialize(teacher.getGroupsSet());
                Hibernate.initialize(teacher.getPaymentsSet());
                Hibernate.initialize(teacher.getTeacherModulesSet());
            }

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return teachers;
    }

    public void deleteTeacher(int id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Teacher teacher = session.get(Teacher.class, id);
            session.delete(teacher);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateTeacher(int id, Teacher teacherIn) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Teacher teacher = session.get(Teacher.class, id);
            teacher.updateTeacher(teacherIn);
            session.update(teacher);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public Teacher getTeacherByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Teacher teacher = null;

        try {
            tx = session.beginTransaction();
            teacher =  session.get(Teacher.class, id);

             Hibernate.initialize(teacher.getTeacherModulesSet());
             Hibernate.initialize(teacher.getPaymentsSet());
          //  Hibernate.initialize(teacher.getGroupsSet());

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return teacher;
    }

}
