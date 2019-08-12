package DAO;

import Entities.SessionOfGroup;
import Entities.Student;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class SessionImpl implements SessionDAO{

    public void init(){

    }

    public void addSession(SessionOfGroup sessionn) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(sessionn);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    public List<SessionOfGroup> getAllSessions() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<SessionOfGroup> sessions = null;

        try {
            tx = session.beginTransaction();
            sessions = session.createQuery("from SessionOfGroup ").list();
            for (SessionOfGroup session1:sessions){
                Hibernate.initialize(session1.getStudentsSet());
                Hibernate.initialize(session1.getGroupOfStudents());
            }

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
       // Collections.sort(sessions);

        return sessions;
    }




    public void deleteSession(int id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            SessionOfGroup sessionn = session.get(SessionOfGroup.class, id);
            session.delete(sessionn);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateSession(int id, String type) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            SessionOfGroup sessionn = session.get(SessionOfGroup.class, id);

            //TODO

            session.update(sessionn);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


    public SessionOfGroup getSessionByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        SessionOfGroup sessionOfGroup = null;

        try {
            tx = session.beginTransaction();
            sessionOfGroup =  session.get(SessionOfGroup.class, id);

            try{

                Hibernate.initialize(sessionOfGroup.getStudentsSet());
                Hibernate.initialize(sessionOfGroup.getGroupOfStudents());

            } catch( SQLGrammarException ex){
                System.out.println( "exception in hibernate initialize");
                ex.printStackTrace();
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return sessionOfGroup;
    }


    public void updateSessionStudents(int id, Set<Student> studentList) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            SessionOfGroup sessionOfGroup = session.get(SessionOfGroup.class, id);
            sessionOfGroup.setStudentsSet(studentList);

            session.update(sessionOfGroup);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }



}
