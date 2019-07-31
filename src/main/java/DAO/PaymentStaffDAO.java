package DAO;

import Entities.Module;
import Entities.PaymentStaff;

import java.util.List;

public interface PaymentStaffDAO {

    public void addPaymentStaff(PaymentStaff paymentStaff);
    public List<PaymentStaff> getAllPaymentStaffs();
    public void deletePaymentStaff(int id);
    public void updatePaymentStaff(int id);
}
