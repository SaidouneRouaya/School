package DAO;

import Entities.PaymentStudent;

import java.util.List;

public interface PaymentStudentDAO {

    public void addPaymentStudent(PaymentStudent paymentStudent);
    public List<PaymentStudent> getAllPaymentStudents();
    public void deletePaymentStudent(int id);
    public void updatePaymentStudent(int id);
}
