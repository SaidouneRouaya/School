package Entities;


import Util.utilities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

import java.util.Date;

@Entity
@Transactional
@Table(name = "payment_staff")
public class PaymentStaff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "receiver")
    private String receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_staff", nullable=false)
    private Staff staff;

    public PaymentStaff(String date, Long amount, String receiver, Staff staff) {
        this.date = utilities.formatDate(date);
        this.amount = amount;
        this.receiver = receiver;
        this.staff = staff;
    }

    public PaymentStaff(Date date, Long amount, String receiver, Staff staff) {
        this.date = date;
        this.amount = amount;
        this.receiver = receiver;
        this.staff = staff;
    }

    public void updatePaymentStaff(PaymentStaff paymentStaff) {
        this.date = paymentStaff.getDate();
        this.amount = paymentStaff.getAmmount();
        this.receiver = paymentStaff.getReceiver();
        this.staff = paymentStaff.getStaff();
    }

    public PaymentStaff() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getAmmount() {
        return amount;
    }

    public void setAmmount(Long ammount) {
        this.amount = ammount;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
