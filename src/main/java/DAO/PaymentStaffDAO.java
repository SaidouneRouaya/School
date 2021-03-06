package DAO;

import Entities.PaymentStaff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface PaymentStaffDAO {

    public Map<String,Float> totalsByDate=new HashMap<>();
    public void addPaymentStaff(PaymentStaff paymentStaff);
    public List<PaymentStaff> getAllPaymentStaffs();
    public void deletePaymentStaff(int id);
    public void updatePaymentStaff(int id, PaymentStaff paymentStaff);
    public PaymentStaff getPayementStaffByID(int id);

    public List<PaymentStaff> getPaymentsByStaff(int id_staff);
    SortedMap<String, List<PaymentStaff>> getPaymentStaffSorted();
}
