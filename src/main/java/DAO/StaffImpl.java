package DAO;

import Entities.Staff;
import Entities.Student;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StaffImpl implements StaffDAO {

    public void init(){

    }
    public void addStaff(Staff staff) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(staff);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();

        } finally {
            session.close();
            }
    }

    public List<Staff> getAllStaffs() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<Staff> staffs = null;


        try {
            tx = session.beginTransaction();

            staffs= session.createCriteria(Staff.class)
                    .add(Restrictions.eq("deleted", false))
                    .list();

            for (Staff staff:staffs){
                Hibernate.initialize(staff.getPaymentStaffSet());
            }

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
             }

        return staffs;
    }

    public void deleteStaff(int id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Staff staff = session.get(Staff.class, id);

            staff.setDeleted(true);
            session.update(staff);
            //session.delete(staff);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateStaff(int id, Staff staffIn) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Staff staff = session.get(Staff.class, id);

            staff.updateStaff(staffIn);
            session.update(staff);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public Staff getStaffByID(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Staff staff = null;

        try {
            tx = session.beginTransaction();
            staff =  session.get(Staff.class, id);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return staff;
    }


}
