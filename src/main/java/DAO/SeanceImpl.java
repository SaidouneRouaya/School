package DAO;

import Entities.Seance;
import Entities.Student;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class SeanceImpl implements SeanceDAO {

    public void init(){

    }

    public void addSeance(Seance seance) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(seance);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    public List<Seance> getAllSeances() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Seance> sessions = null;

        try {
            tx = session.beginTransaction();
            sessions = session.createQuery("from Seance ").list();
            for (Seance session1:sessions){
                Hibernate.initialize(session1.getStudentsSet());
                Hibernate.initialize(session1.getSessionOfGroup());
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




    public void deleteSeance(int id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Seance sessionn = session.get(Seance.class, id);
            session.delete(sessionn);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateSeance(int id, Seance seance) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Seance sessionn = session.get(Seance.class, id);

            sessionn.updateSeance(seance);

            session.update(sessionn);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


    public Seance getSeanceByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Seance seance = null;

        try {
            tx = session.beginTransaction();
            seance =  session.get(Seance.class, id);

            try{

                Hibernate.initialize(seance.getStudentsSet());
                Hibernate.initialize(seance.getSessionOfGroup());

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
        return seance;
    }

    public List <Seance> getSeanceByDate(Date date, int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List <Seance> seance = null;

        try {
            tx = session.beginTransaction();
            seance =  session.createCriteria(Seance.class)

                    .add(Restrictions.eq("date", date))
                    .add(Restrictions.eq("sessionOfGroup.id", id))
                    .list();

            for (Seance seance1: seance){
                Hibernate.initialize(seance1.getStudentsSet());
                Hibernate.initialize(seance1.getSessionOfGroup());

            }

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return seance;
    }


    public void updateSessionStudents(int id, Set<Student> studentList) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Seance seance = session.get(Seance.class, id);
            seance.setStudentsSet(studentList);

            session.update(seance);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }



}
