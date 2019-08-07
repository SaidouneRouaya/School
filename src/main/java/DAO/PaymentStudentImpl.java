package DAO;


import Entities.PaymentStudent;
import Entities.Student;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;


@Component
public class PaymentStudentImpl implements PaymentStudentDAO {

    public void init() {
    }

    public Map<String,Long> getTotalsByDate() {
        return totalsByDate;
    }


    @Override
    public void addPaymentStudent(PaymentStudent paymentStudent) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(paymentStudent);
            tx.commit();


        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();

        }
    }
    @Override
    public List<PaymentStudent> getAllPaymentStudents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<PaymentStudent> paymentStudents = null;

        try {
            tx = session.beginTransaction();
            paymentStudents = session.createQuery("from PaymentStudent ").list();

            for(PaymentStudent paymentStudent:paymentStudents){

                Hibernate.initialize(paymentStudent.getStudentPay());
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return paymentStudents;
    }

    @Override
    public void deletePaymentStudent(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            PaymentStudent paymentStudent= session.get(PaymentStudent.class, id);
            session.delete(paymentStudent);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


    @Override
    public void updatePaymentStudent(int id, PaymentStudent newPaymentStudent) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            PaymentStudent paymentStudent= session.get(PaymentStudent.class, id);
            paymentStudent.updatePaymentStudent(newPaymentStudent);
            session.update(paymentStudent);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public PaymentStudent getPayementStudentByID(int id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        PaymentStudent paymentStudent= null;

        try {
            tx = session.beginTransaction();
            paymentStudent= session.get(PaymentStudent.class, id);
            Hibernate.initialize(paymentStudent.getStudentPay());
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return paymentStudent;
    }


    public List<Timestamp> getPaymentStudentByDate ()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Timestamp> results=null;
      //  List<Timestamp> resultsTemp=null;

        try {
            tx = session.beginTransaction();

            results= session.createCriteria(PaymentStudent.class).
                    setProjection(Projections.projectionList().add(Projections.groupProperty("date"), "date"))
                    .addOrder(Order.desc("date"))
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

    public List<PaymentStudent> getPaymentStudentsByDate (Timestamp date)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<PaymentStudent> results=null;


        try {
            tx = session.beginTransaction();

            results= session.createCriteria(PaymentStudent.class)
                    .add(Restrictions.eq("date", date)).list();


            Long total=0L;
            for(PaymentStudent paymentStudent:results){

                total+=paymentStudent.getAmmount();

                Hibernate.initialize(paymentStudent.getStudentPay());
            }

            tx.commit();
            String time=date.toString();
            time= time.substring(0, time.length()-10);

            totalsByDate.put(time, total);


        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return results;
    }

    public List<PaymentStudent> getPaymentsByStudent(int id_student)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<PaymentStudent> results=null;

        try {
            tx = session.beginTransaction();

            results= session.createCriteria(PaymentStudent.class)
                    .add(Restrictions.eq("studentPay.id", id_student))
                    .list();


            for(PaymentStudent paymentStudent:results){

                Hibernate.initialize(paymentStudent.getStudentPay());
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
    public HashMap<String, List<PaymentStudent>> getPaymentStudentSorted(){

        List<Timestamp> dates=getPaymentStudentByDate();
        HashMap<String, List<PaymentStudent>> results =new HashMap<>();

        for (Timestamp timestamp:dates){
            String time=timestamp.toString();
            time= time.substring(0, time.length()-10);

            results.put(time, getPaymentStudentsByDate(timestamp));
        }

        return results;
    }


}
