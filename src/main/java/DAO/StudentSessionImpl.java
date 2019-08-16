package DAO;


import Entities.Seance;
import Entities.Student;
import Entities.StudentSession;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class StudentSessionImpl implements StudentSessionDAO {

    public void init(){

    }

    @Override
    public void addStudentSession(StudentSession studentSession) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(studentSession);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
    }


    @Override
    public List<StudentSession> getAllStudentSessions() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<StudentSession> studentSessions = null;

        try {
            tx = session.beginTransaction();
            studentSessions = session.createQuery("from StudentSession ").list();
            for (StudentSession session1:studentSessions){
                Hibernate.initialize(session1.getSession());
                Hibernate.initialize(session1.getStudent());
            }

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        // Collections.sort(sessions);

        return studentSessions;
    }



    @Override
    public void deleteStudentSession(StudentSession studentSession) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.delete(studentSession);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }





}
