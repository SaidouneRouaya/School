package DAO;

import Entities.PaymentStaff;
import Entities.PaymentStudent;
import Entities.PaymentTeacher;

import java.util.List;

public interface PaymentTeacherDAO {

    public void addPaymentTeacher(PaymentTeacher paymentTeacher);
    public List<PaymentTeacher> getAllPaymentTeachers();
    public void deletePaymentTeacher(int id);
    public void updatePaymentTeacher(int id, PaymentTeacher paymentTeacher);
    public PaymentTeacher getPayementTeacherByID(int id);
}
