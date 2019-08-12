package DAO;

import Entities.Module;
import Entities.Profile;
import Entities.Student;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;

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
            session.close();
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
            session.close();
        }
    }

    public void updateProfile(int id, Profile newProfile) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Profile profile = session.get(Profile.class, id);
            profile.updateProfile(newProfile);
            session.update(profile);
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
    public Profile getProfileByID (int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Profile profile= null;

        try {
            tx = session.beginTransaction();
            profile =  session.get(Profile.class, id);


            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return profile;
    }

    @Override
    public  List<Profile>  getProfileByEmail (String email, String password) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
       List<Profile> profiles= null;

        try {
            tx = session.beginTransaction();
            profiles= session.createCriteria(Profile.class)
                    .add(Restrictions.and(Restrictions.eq("username", email),
                            Restrictions.eq("password", password)))
                    .list();

            tx.commit();

            System.out.println("get by email ");
            System.out.println(profiles.size());


        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return profiles;
    }


}
