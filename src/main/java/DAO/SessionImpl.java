package DAO;

import Entities.*;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class SessionImpl implements SessionDAO {
    public void init(){

    }

    @Override
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

    @Override
    public List<SessionOfGroup> getAllSessions() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<SessionOfGroup> sessions = null;

        try {
            tx = session.beginTransaction();
            sessions = session.createQuery("from SessionOfGroup ").list();
            for (SessionOfGroup session1:sessions){
                Hibernate.initialize(session1.getGroupOfStudents());
                Hibernate.initialize(session1.getSeancesSet());
                Hibernate.initialize(session1.getStudentSessionsSet());
                for (StudentSession studentSession: session1.getStudentSessionsSet()){
                    Hibernate.initialize(studentSession.getStudent());
                    Hibernate.initialize(studentSession.getSession());
                }

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

    @Override
    public SessionOfGroup getSessionByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
       SessionOfGroup sessionOfGroup = null;

        try {
            tx = session.beginTransaction();
            sessionOfGroup =  session.get(SessionOfGroup.class, id);

            try{

                Hibernate.initialize(sessionOfGroup.getSeancesSet());
                Hibernate.initialize(sessionOfGroup.getGroupOfStudents());

                Hibernate.initialize(sessionOfGroup.getStudentSessionsSet());
                for (StudentSession studentSession: sessionOfGroup.getStudentSessionsSet()){
                    Hibernate.initialize(studentSession.getStudent());
                    Hibernate.initialize(studentSession.getSession());
                }

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

    @Override
    public void deleteSession(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            SessionOfGroup sessionOfGroup = session.get(SessionOfGroup.class, id);
            session.delete(sessionOfGroup);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateSession(int id, SessionOfGroup sessionOfGroup) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            SessionOfGroup sessionOfGroup1 = session.get(SessionOfGroup.class, id);

            sessionOfGroup1.updateSession(sessionOfGroup);

            session.update(sessionOfGroup1);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
   public List<SessionOfGroup> getSessionByGroup (int id_group){

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<SessionOfGroup> results=null;

        try {
            tx = session.beginTransaction();

            results= session.createCriteria(SessionOfGroup.class)
                    .createAlias("groupOfStudents", "group")
                    .add(Restrictions.eq("group.id", id_group))
                    .list();

            for (SessionOfGroup sessionOfGroup:results){

                Hibernate.initialize(sessionOfGroup.getSeancesSet());
                Hibernate.initialize(sessionOfGroup.getGroupOfStudents());

                Hibernate.initialize(sessionOfGroup.getStudentSessionsSet());
                for (StudentSession studentSession: sessionOfGroup.getStudentSessionsSet()){
                    Hibernate.initialize(studentSession.getStudent());
                    Hibernate.initialize(studentSession.getSession());
                }
            }


            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return results;
    }


}
