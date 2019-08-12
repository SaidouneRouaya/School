package DAO;

import Entities.*;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class PaymentStaffImpl implements PaymentStaffDAO {

    public void init() {
    }

    @Override
    public void addPaymentStaff(PaymentStaff paymentStaff) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(paymentStaff);
            tx.commit();


        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<PaymentStaff> getAllPaymentStaffs() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<PaymentStaff> paymentStaffs = null;

        try {
            tx = session.beginTransaction();
            paymentStaffs = session.createQuery("from PaymentStaff ").list();

            for (PaymentStaff paymentStaff:paymentStaffs){

                Hibernate.initialize(paymentStaff.getStaff());
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return paymentStaffs;
    }

    @Override
    public void deletePaymentStaff(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            PaymentStaff paymentStaff= session.get(PaymentStaff.class, id);
            session.delete(paymentStaff);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public void updatePaymentStaff(int id, PaymentStaff newPaymentStaff) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            PaymentStaff paymentStaff= session.get(PaymentStaff.class, id);
            paymentStaff.updatePaymentStaff(newPaymentStaff);
            session.update(paymentStaff);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public PaymentStaff getPayementStaffByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        PaymentStaff paymentStaff= null;

        try {
            tx = session.beginTransaction();
            paymentStaff= session.get(PaymentStaff.class, id);
            Hibernate.initialize(paymentStaff.getStaff());
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return paymentStaff;
    }


    public List<Timestamp> getPaymentStaffByDate ()
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Timestamp> results=null;
        //  List<Timestamp> resultsTemp=null;

        try {
            tx = session.beginTransaction();

            results= session.createCriteria(PaymentStaff.class)
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

    public List<PaymentStaff> getPaymentStaffsByDate (Timestamp date)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<PaymentStaff> results=null;
        //  List<Timestamp> resultsTemp=null;

        try {
            tx = session.beginTransaction();

            results= session.createCriteria(PaymentStaff.class)
                    .add(Restrictions.eq("date", date)).list();


            float total=0L;
            for(PaymentStaff paymentStaff:results){

                total+=paymentStaff.getAmmount();

                Hibernate.initialize(paymentStaff.getStaff());
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
    public SortedMap<String, List<PaymentStaff>> getPaymentStaffSorted()
    {
        List<Timestamp> dates=getPaymentStaffByDate();
        SortedMap<String, List<PaymentStaff>> results =new TreeMap<>();

        for (Timestamp timestamp:dates){

            String time=timestamp.toString();
            time= time.substring(0, time.length()-10);

            results.put(time, getPaymentStaffsByDate(timestamp));
        }

        return results;
    }


    public List<PaymentStaff> getPaymentsByStaff(int id_staff)
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<PaymentStaff> results=null;

        try {
            tx = session.beginTransaction();

            results= session.createCriteria(PaymentStaff.class)
                    .add(Restrictions.eq("staff.id", id_staff))
                    .list();


            for(PaymentStaff paymentStaff:results){

                Hibernate.initialize(paymentStaff.getStaff());
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




}