package Entities;


import Util.utilities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

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
    private float amount;

    @Column(name = "total")
    private float total;

    @Column(name = "discount")
    private float discount;

    @Column(name = "module")
    private String module;

    @Column(name = "receiver")
    private String receiver;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_student")
    private Student studentPay;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "paymentstudent_group",
            joinColumns = {@JoinColumn(name = "id_payment_st") },
            inverseJoinColumns = { @JoinColumn(name = "id_group") })
    private Set<GroupOfStudents> groupPay;




    public PaymentStudent() {
    }

    public PaymentStudent(String date, float amount,float discount, float total,String module, String receiver, Student studentPay/*,Set<GroupOfStudents> groupPay*/ ) {


        this.date = utilities.formatDate(date);
        this.amount = amount;
        this.total=total;
        this.module = module;
        this.receiver = receiver;
        this.discount= discount;
        this.studentPay = studentPay;
       // this.groupPay= groupPay;
    }
    public PaymentStudent(Date date, float amount,float discount, float total,String module, String receiver, Student studentPay ,Set<GroupOfStudents> groupPay) {


        this.date =date;
        this.amount = amount;
        this.total=total;
        this.module = module;
        this.receiver = receiver;
        this.discount= discount;
        this.studentPay = studentPay;
        this.groupPay= groupPay;
    }



    public void updatePaymentStudent(PaymentStudent paymentStudent) {
        this.date = paymentStudent.getDate();
        this.amount = paymentStudent.getAmount();
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float ammount) {
        this.amount = ammount;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Set<GroupOfStudents> getGroupPay() {
        return groupPay;
    }

    public void setGroupPay(Set<GroupOfStudents> groupPay) {
        this.groupPay = groupPay;
    }


    public boolean containsGroup (GroupOfStudents group){

      Iterator<GroupOfStudents> it= groupPay.iterator();

        while (it.hasNext()){
            GroupOfStudents g=it.next();

            return (g.getId()==group.getId()) ;

        }
        return false;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
