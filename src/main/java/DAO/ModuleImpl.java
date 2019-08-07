package DAO;

import Entities.GroupOfStudents;
import Entities.Student;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import Entities.Module;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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

                try{
                    Hibernate.initialize(module.getModuleTeachersSet());
                    Hibernate.initialize(module.getSessionsSet());
                    Hibernate.initialize(module.getStudentsSet());
                    Hibernate.initialize(module.getGroupsSet());

                } catch( SQLGrammarException ex){

                    System.out.println( "exception in hibernate initialize");
                ex.printStackTrace();

                }

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

    @Override
    public void updateModule(int id, Module newModule) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Module module = session.get(Module.class, id);

            module.updateModule(newModule);
            session.update(module);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public void updateGroupStudentsList (int id, Set<Student> studentList) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Module module = session.get(Module.class, id);

            module.setStudentsSet(studentList);

            session.update(module);


            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
    @Override
    public Module getModuleByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Module module = null;

        try {
            tx = session.beginTransaction();
            module =  session.get(Module.class, id);

            Hibernate.initialize(module.getModuleTeachersSet());
            Hibernate.initialize(module.getSessionsSet());
            Hibernate.initialize(module.getStudentsSet());
            Hibernate.initialize(module.getGroupsSet());

            tx.commit();


        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return module;
    }

}
