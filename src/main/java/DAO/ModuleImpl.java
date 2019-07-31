package DAO;

import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import Entities.Module;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModuleImpl implements  ModuleDAO{

    public void init() {
    }

    public void addModule(Module module) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(module);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();

        } finally {
            session.close();
        }
    }

    public List<Module> getAllModules() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Module> modules = null;

        try {
            tx = session.beginTransaction();
            modules = session.createQuery("from Module ").list();

            for (Module module:modules){

                Hibernate.initialize(module.getModuleTeachersSet());
                Hibernate.initialize(module.getSessionsSet());
                Hibernate.initialize(module.getStudentsSet());
                // Hibernate.initialize(module.getGroupsSet());

            }


            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return modules;
    }

    public void deleteModule(int id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Module module = session.get(Module.class, id);
            session.delete(module);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateModule(int id, String type) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Module module = session.get(Module.class, id);

            //TODO

            session.update(module);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
