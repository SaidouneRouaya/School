package Entities;


import Util.utilities;

import javax.persistence.*;
import javax.swing.text.Utilities;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Transactional
@Table(name = "staff")

public class Staff  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_staff")
    private int id;

    @Column(name="name")
    private String name="";

    @Column(name="familyname")
    private String familyname="";

    @Column(name="phone")
    private int phoneNumber;

    @Column(name="job")
    private String job;

    @Column(name="employment_date")
    @Temporal(TemporalType.DATE)
    private Date employmentDate;

    @Column(name="salary")
    private long salary;



    @Column(name="deleted")
    private boolean deleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
    private Set<PaymentStaff> paymentStaffSet;


    public Staff() {
    }

    public Staff(String name, String familyname, int phoneNumber, String job, String employmentDate, long salary) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.employmentDate = utilities.formatDate(employmentDate);
        this.salary = salary;

        this.paymentStaffSet= new HashSet<>();
    }
    public void updateStaff(Staff newStaff ) {
        if(newStaff.getName()!=null) this.name = newStaff.getName();
        if(newStaff.getFamilyname()!=null)  this.familyname = newStaff.getFamilyname();
        if(newStaff.getEmploymentDate()!=null) this.employmentDate= newStaff.getEmploymentDate();
        if(newStaff.getPhoneNumber()!=0) this.phoneNumber= newStaff.getPhoneNumber();
        if(newStaff.getSalary()!=0) this.salary= newStaff.getSalary();
        if(newStaff.getJob()!=null)  this.job= newStaff.getJob();

    }

    public Set<PaymentStaff> getPaymentStaffSet() {
        return paymentStaffSet;
    }

    public void setPaymentStaffSet(Set<PaymentStaff> paymentStaffSet) {
        this.paymentStaffSet = paymentStaffSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}