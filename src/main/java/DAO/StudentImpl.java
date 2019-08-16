package DAO;


import Entities.GroupOfStudents;
import Entities.Seance;
import Entities.Student;
import Entities.Module;

import Util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


@Component
public class StudentImpl implements StudentDAO {

    public StudentImpl() {
    }

    public void init() {
    }

    public void addStudent(Student student) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Student> students = null;

        try {
            tx = session.beginTransaction();
            students = session.createQuery("from Student ").list();
            for (Student student: students){

                Hibernate.initialize(student.getStudentSessionsSet());
                Hibernate.initialize(student.getModulesSet());
                Hibernate.initialize(student.getPaymentSet());
                Hibernate.initialize(student.getSeancesSet());

            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
          session.close();
        }
        return students;
    }

    public void deleteStudent(int id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.delete(student);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateStudent(int id,Student st) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, id);
            student.updateStudent(st);
            session.update(student);
            System.out.println("student update date: "+student.getSubscriptionDate());

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
         session.close();
        }

    }

    /*public void updateStudentGroups(int id, Set<GroupOfStudents> groups) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, id);

            student.setGroupsSet(groups);
            session.update(student);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
         session.close();
        }

    }*/

    public void updateStudentSessions (int id, Set<Seance> sessionOfGroupsSet) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, id);

            student.setSeancesSet(sessionOfGroupsSet);
            session.update(student);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
         session.close();
        }

    }

    public void updateStudentModules(int id, Set<Module> modules) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, id);

            student.setModulesSet(modules);
            session.update(student);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
         session.close();
        }

    }



    @Override
    public Student getStudentByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Student student = null;

        try {
            tx = session.beginTransaction();
            student =  session.get(Student.class, id);

            try{
                Hibernate.initialize(student.getStudentSessionsSet());
                Hibernate.initialize(student.getModulesSet());
                Hibernate.initialize(student.getPaymentSet());
                Hibernate.initialize(student.getSeancesSet());

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
        return student;
    }


    public List<Student> getStudentsByModule(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Student> results=null;

        try {
            tx = session.beginTransaction();

            results= session.createCriteria(Student.class)
                    .createAlias("modulesSet", "module")
                    .add(Restrictions.eq("module.id", id))
                    .list();

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
    public HashMap<Integer, List<Student>> getStudentsByModules(List<Module> modules){


        HashMap<Integer, List<Student>> results =new HashMap<>();

        for (Module module: modules){

            results.put(module.getId(), getStudentsByModule(module.getId()));
        }

        return results;
    }


  /*  public List<Student> getStudentsByGroup(int id_group){

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Student> results=null;

        try {
            tx = session.beginTransaction();

            results= session.createCriteria(Student.class)
                    .createAlias("groupsSet", "group")
                    .add(Restrictions.eq("group.id", id_group))
                    .list();

            for (Student student:results){
                Hibernate.initialize(student.getGroupsSet());
                Hibernate.initialize(student.getModulesSet());
                Hibernate.initialize(student.getPaymentSet());
                Hibernate.initialize(student.getSeancesSet());

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

*/


}