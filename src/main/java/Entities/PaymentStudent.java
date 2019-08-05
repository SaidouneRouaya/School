package Entities;


import Util.utilities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;

@Entity
@Transactional
@Table(name = "payment_student")
public class PaymentStudent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_st")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private Long ammount;

    @Column(name = "module")
    private String module;

    @Column(name = "receiver")
    private String receiver;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_student")
    private Student studentPay;

    public PaymentStudent() {
    }

    public PaymentStudent(String date, Long ammount, String module, String receiver, Student studentPay) {
        this.date = utilities.formatDate(date);
        this.ammount = ammount;
        this.module = module;
        this.receiver = receiver;
        this.studentPay = studentPay;
    }



    public void updatePaymentStudent(PaymentStudent paymentStudent) {
        this.date = paymentStudent.getDate();
        this.ammount = paymentStudent.getAmmount();
        this.module = paymentStudent.getModule();
        this.receiver = paymentStudent.getReceiver();
        this.studentPay = paymentStudent.getStudentPay();
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
        return ammount;
    }

    public void setAmmount(Long ammount) {
        this.ammount = ammount;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Student getStudentPay() {
        return studentPay;
    }

    public void setStudentPay(Student studentPay) {
        this.studentPay = studentPay;
    }
}
