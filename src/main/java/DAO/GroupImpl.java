package DAO;


import Entities.GroupOfStudents;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupImpl implements  GroupDAO{

    public void init(){}

    @Override
    public void addGroup(GroupOfStudents groupOfStudents) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(groupOfStudents);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    @Override
    public List<GroupOfStudents> getAllGroups() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<GroupOfStudents> groupOfStudents = null;

        try {
            tx = session.beginTransaction();
            groupOfStudents = session.createQuery("from GroupOfStudents ").list();

            for (GroupOfStudents group : groupOfStudents){
                Hibernate.initialize( group.getModule());
                Hibernate.initialize (group.getTeacher());
                Hibernate.initialize (group.getStudentsSet());
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return groupOfStudents;
    }

    @Override
    public void deleteGroup(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            GroupOfStudents groupOfStudents = session.get(GroupOfStudents.class, id);
            session.delete(groupOfStudents);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateGroup(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
           GroupOfStudents groupOfStudents = session.get(GroupOfStudents.class, id);

            //TODO

            session.update(groupOfStudents);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
