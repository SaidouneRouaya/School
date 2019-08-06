package DAO;

import Entities.PaymentStaff;
import Entities.PaymentStudent;
import Entities.PaymentTeacher;
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
public class PaymentTeacherImpl implements PaymentTeacherDAO {

    public void init() {
    }



    @Override
    public void addPaymentTeacher(PaymentTeacher paymentTeacher) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(paymentTeacher);
            tx.commit();


        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();

        }
    }
    @Override
    public List<PaymentTeacher> getAllPaymentTeachers() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<PaymentTeacher> paymentTeachers = null;

        try {
            tx = session.beginTransaction();
            paymentTeachers = session.createQuery("from PaymentTeacher ").list();

            for (PaymentTeacher paymentTeacher: paymentTeachers){

                Hibernate.initialize(paymentTeacher.getTeacher());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return paymentTeachers;
    }

    @Override
    public void deletePaymentTeacher(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            PaymentTeacher paymentTeacher= session.get(PaymentTeacher.class, id);
            session.delete(paymentTeacher);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


    @Override
    public void updatePaymentTeacher(int id, PaymentTeacher newPaymentTeacher) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            PaymentTeacher paymentTeacher= session.get(PaymentTeacher.class, id);
            paymentTeacher.updatePaymentTeacher(newPaymentTeacher);
            session.update(paymentTeacher);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public PaymentTeacher getPayementTeacherByID(int id) {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            PaymentTeacher paymentTeacher= null;

            try {
                tx = session.beginTransaction();
                paymentTeacher= session.get(PaymentTeacher.class, id);
                Hibernate.initialize(paymentTeacher.getTeacher());
                tx.commit();

            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
            return paymentTeacher;
        }



        public List<Timestamp> getPaymentTeacherByDate ()
        {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
            List<Timestamp> results=null;
            //  List<Timestamp> resultsTemp=null;

            try {
                tx = session.beginTransaction();

                results= session.createCriteria(PaymentTeacher.class)
                        .setProjection(Projections.projectionList().add(Projections.groupProperty("date"), "date"))
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

        public List<PaymentTeacher> getPaymentTeachersByDate (Timestamp date)
        {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = null;
        List<PaymentTeacher> results=null;
        //  List<Timestamp> resultsTemp=null;

        try {
            tx = session.beginTransaction();

            results= session.createCriteria(PaymentTeacher.class)
                    .add(Restrictions.eq("date", date)).list();


            Long total=0L;
            for(PaymentTeacher paymentTeacher:results){

                total+=paymentTeacher.getAmount();

                Hibernate.initialize(paymentTeacher.getTeacher());
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


    @Override
    public SortedMap <String, List<PaymentTeacher>> getPaymentTeacherSorted()
    {
        List<Timestamp> dates=getPaymentTeacherByDate();
        SortedMap<String, List<PaymentTeacher>> results =new TreeMap<>();

        for (Timestamp timestamp:dates){

         String time=timestamp.toString();
         time= time.substring(0, time.length()-10);

            results.put(time, getPaymentTeachersByDate(timestamp));
        }

        return results;
    }




}
