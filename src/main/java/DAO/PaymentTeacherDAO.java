package DAO;

import Entities.PaymentStaff;
import Entities.PaymentStudent;
import Entities.PaymentTeacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface PaymentTeacherDAO {

    public Map<String, Float> totalsByDate=new HashMap<>();
    public void addPaymentTeacher(PaymentTeacher paymentTeacher);
    public List<PaymentTeacher> getAllPaymentTeachers();
    public void deletePaymentTeacher(int id);
    public void updatePaymentTeacher(int id, PaymentTeacher paymentTeacher);
    public PaymentTeacher getPayementTeacherByID(int id);
    public List<PaymentTeacher> getPaymentsByTreacher(int id_teacher);
    SortedMap <String, List<PaymentTeacher>> getPaymentTeacherSorted();
}
