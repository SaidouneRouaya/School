package DAO;


import Entities.*;
import Entities.Module;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class GroupOfStudentsImpl implements GroupOfStudentsDAO {

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
                Hibernate.initialize(group.getSessionSet());
                Hibernate.initialize(group.getPaymentStudentSet());
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
    public void updateGroup(int id, GroupOfStudents groupOfStudentsNew) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
           GroupOfStudents groupOfStudents = session.get(GroupOfStudents.class, id);

            groupOfStudents.updateGroup(groupOfStudentsNew);

            session.update(groupOfStudents);
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

            GroupOfStudents groupOfStudents = session.get(GroupOfStudents.class, id);

            groupOfStudents.setStudentsSet(studentList);

            session.update(groupOfStudents);

            System.out.println("update");

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
    @Override
    public void updateGroupSessionsList (int id, Set<SessionOfGroup> sessionsList) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            GroupOfStudents groupOfStudents = session.get(GroupOfStudents.class, id);
            groupOfStudents.setSessionSet(sessionsList);
            session.update(groupOfStudents);

            System.out.println("update");

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public GroupOfStudents getGroupById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        GroupOfStudents groupOfStudents = null;

        try {
            tx = session.beginTransaction();
            groupOfStudents =  session.get(GroupOfStudents.class, id);

            try{
                Hibernate.initialize(groupOfStudents.getModule());
                Hibernate.initialize(groupOfStudents.getTeacher());
                Hibernate.initialize(groupOfStudents.getStudentsSet());
                Hibernate.initialize(groupOfStudents.getSessionSet());
                Hibernate.initialize(groupOfStudents.getPaymentStudentSet());

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
        return groupOfStudents;

    }



    public List<Module> getModulesOfGroupsOfStudents ()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Module> results=null;


        try {
            tx = session.beginTransaction();

            results= session.createCriteria(GroupOfStudents.class)
                    .setProjection(Projections.projectionList().add(Projections.groupProperty("module"), "module"))
                   // .addOrder(Order.desc("module.name"))
                    .list();

            for (Module module: results){
                Hibernate.initialize(module.getModuleTeachersSet());

                Hibernate.initialize(module.getStudentsSet());
                Hibernate.initialize(module.getGroupsSet());
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

    public List<GroupOfStudents> getGroupsOfStudentsByModule (int  id_module)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<GroupOfStudents> results=null;


        try {
            tx = session.beginTransaction();

            results= session.createCriteria(GroupOfStudents.class)
                    .add(Restrictions.eq("module.id", id_module))
                    .list();

            for(GroupOfStudents groupOfStudents:results){

                Hibernate.initialize(groupOfStudents.getModule());
                Hibernate.initialize(groupOfStudents.getTeacher());
                Hibernate.initialize(groupOfStudents.getStudentsSet());
                Hibernate.initialize(groupOfStudents.getSessionSet());
                Hibernate.initialize(groupOfStudents.getPaymentStudentSet());
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


    @Override
    public SortedMap<String, List<GroupOfStudents>> getAllGroupsByModules()
    {
        List<Module> modules=getModulesOfGroupsOfStudents();

        SortedMap<String, List<GroupOfStudents>> results= new TreeMap<>();

        for (Module module:modules){
            try{

                results.put(module.getName(), getGroupsOfStudentsByModule(module.getId()));

            }catch (Exception e){

                e.printStackTrace();
            }
        }
        return results;
    }



}
