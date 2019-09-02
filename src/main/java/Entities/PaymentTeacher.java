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

    @Column(name = "total")
    private float total;

    @Column(name = "amount")
    private float amount;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "module")
    private String groups;

    @Column(name = "payment_type")
    private String paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_teacher")
    private Teacher teacherPayed;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "paymentteacher_session",
            joinColumns = {@JoinColumn(name = "id_payment_tch") },
            inverseJoinColumns = { @JoinColumn(name = "id_session") })
    private Set<SessionOfGroup> sessionPay;




    public int getId() {
        return id;
    }

    public PaymentTeacher() {
    }

    public PaymentTeacher(Date date, float amount, float total,  String receiver, String module, String paymentType, Teacher teacher) {
        this.date = date;
        this.amount = amount;
        this.total=total;
        this.receiver = receiver;
        this.groups = module;
        this.paymentType = paymentType;
        this.teacherPayed = teacher;
    }

    public PaymentTeacher(Date date, float total, float amount, String receiver, String groups, String paymentType, Teacher teacherPayed, Set<SessionOfGroup> sessionPay) {
        this.date = date;
        this.total = total;
        this.amount = amount;
        this.receiver = receiver;
        this.groups = groups;
        this.paymentType = paymentType;
        this.teacherPayed = teacherPayed;
        this.sessionPay = sessionPay;
    }

    public void updatePaymentTeacher(PaymentTeacher paymentTeacher) {
        this.date = paymentTeacher.getDate();
        this.amount = paymentTeacher.getAmount();
        this.receiver = paymentTeacher.getReceiver();
        this.groups= paymentTeacher.getModule();
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
        return groups;
    }

    public void setModule(String module) {
        this.groups = module;
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

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Teacher getTeacherPayed() {
        return teacherPayed;
    }

    public void setTeacherPayed(Teacher teacherPayed) {
        this.teacherPayed = teacherPayed;
    }

    public Set<SessionOfGroup> getSessionPay() {
        return sessionPay;
    }

    public void setSessionPay(Set<SessionOfGroup> sessionPay) {
        this.sessionPay = sessionPay;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
