package DAO;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentStaffImpl implements PaymentStaffDAO {

    public void init() {
    }

    @Override
    public void addPaymentStaff(Entities.PaymentStaff paymentStaff) {

    }

    @Override
    public List<Entities.PaymentStaff> getAllPaymentStaffs() {
        return null;
    }

    @Override
    public void deletePaymentStaff(int id) {

    }

    @Override
    public void updatePaymentStaff(int id) {

    }
}