package DAO;

import Entities.PaymentStaff;
import Entities.Student;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

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

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return paymentStaff;
    }

}