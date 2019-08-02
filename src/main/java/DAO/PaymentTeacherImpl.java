package DAO;

import Entities.PaymentStaff;
import Entities.PaymentTeacher;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

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

                tx.commit();

            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
            return paymentTeacher;
        }

}
