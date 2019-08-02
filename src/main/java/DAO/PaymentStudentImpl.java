package DAO;


import Entities.PaymentStudent;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PaymentStudentImpl implements PaymentStudentDAO {

    public void init() {
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

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return paymentStudent;
    }

}
