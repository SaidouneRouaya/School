package DAO;

import Entities.Module;
import Entities.Profile;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProfileImpl implements ProfileDAO {

    public void init(){
    }

    public void addProfile(Profile profile) {

        Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=null;
        try {
            tx= session.beginTransaction();
            session.save(profile);
            System.out.println("add done");
            tx.commit();

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Profile> getAllProfiles() {
        Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=null;
        List<Profile> profiles= null;

        try {
            tx= session.beginTransaction();
            profiles= session.createQuery("from Profile ").list();
            tx.commit();

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return profiles;
    }

    public void deleteProfile(int id) {

        Session session =  HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx=null;

        try {
            tx= session.beginTransaction();
            Profile profile =  session.get(Profile.class, id);
            session.delete(profile);
           tx.commit();

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            //session.close();
        }
    }

    public void updateProfile(int id, String type) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Profile profile = session.get(Profile.class, id);
            profile.setType(type);
            session.update(profile);
            System.out.println("add done");
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            //session.close();
        }

    }
}
