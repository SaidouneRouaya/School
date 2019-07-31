package DAO;

import Entities.Module;
import Entities.Session;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionImpl implements SessionDAO{

    public void init(){

    }

    public void addSession(Session sessionn) {

        org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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

    public List<Session> getAllSessions() {
        org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Session> sessions = null;

        try {
            tx = session.beginTransaction();
            sessions = session.createQuery("from Session ").list();
            for (Session session1:sessions){

                Hibernate.initialize(session1.getModulesSet());
            }

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return sessions;
    }

    public void deleteSession(int id) {

        org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Session sessionn = session.get(Session.class, id);
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

        org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Session sessionn = session.get(Session.class, id);

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
}
