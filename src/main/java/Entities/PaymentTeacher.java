package Entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Transactional
@Table(name = "payment_teacher")
public class PaymentTeacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private int id;


    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private float amount;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "module")
    private String module;

    @Column(name = "payment_type")
    private String paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_teacher", nullable=false)
    private Teacher teacherPayed;

    public int getId() {
        return id;
    }

    public PaymentTeacher() {
    }

    public PaymentTeacher(Date date, float amount, String receiver, String module, String paymentType, Teacher teacher) {
        this.date = date;
        this.amount = amount;
        this.receiver = receiver;
        this.module = module;
        this.paymentType = paymentType;
        this.teacherPayed = teacher;
    }

    public void updatePaymentTeacher(PaymentTeacher paymentTeacher) {
        this.date = paymentTeacher.getDate();
        this.amount = paymentTeacher.getAmount();
        this.receiver = paymentTeacher.getReceiver();
        this.module= paymentTeacher.getModule();
        this.paymentType= paymentTeacher.getPaymentType();
        this.teacherPayed=paymentTeacher.getTeacher();

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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Teacher getTeacher() {
        return teacherPayed;
    }

    public void setTeacher(Teacher teacher) {
        this.teacherPayed = teacher;
    }




}
