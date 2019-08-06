package DAO;

import Entities.PaymentStudent;
import Entities.Student;

import java.util.*;

public interface PaymentStudentDAO {


    public Map<String,Long> totalsByDate=new HashMap<>();

    public void addPaymentStudent(PaymentStudent paymentStudent);
    public List<PaymentStudent> getAllPaymentStudents();
    public void deletePaymentStudent(int id);
    public void updatePaymentStudent(int id, PaymentStudent paymentStudent);
    public PaymentStudent getPayementStudentByID(int id);
    //public  List<Timestamp> getPaymentStudentByDate();
    HashMap<String, List<PaymentStudent>> getPaymentStudentSorted();


}
