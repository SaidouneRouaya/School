package Entities;


import Util.utilities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Transactional
@Table(name = "teachers")

public class Teacher  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_teacher")
    private int id;

    @Column(name="name")
    private String name="";

    @Column(name="familyname")
    private String familyname="";

    @Column(name="phone")
    private int phoneNumber;

    @Column(name="employment_date")
    @Temporal(TemporalType.DATE)
    private Date employmentDate;

    @Column(name="salary")
    private long salary;

    @Column(name="picture")
    @Lob
    private byte[] picture;

   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private Set<Group> groupsSet;*/

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacherPayed")
    private Set<PaymentTeacher> paymentTeacherSet;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_module",
            joinColumns = {@JoinColumn(name = "id_teacher") },
            inverseJoinColumns = { @JoinColumn(name = "id_module") })
    private Set<Module> teacherModulesSet;


    public Teacher() {
    }

    public Teacher(String name, String familyname, int phoneNumber, String employmentDate, long salary, byte[] picture) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber = phoneNumber;

        this.employmentDate = utilities.formatDate(employmentDate);

        this.salary = salary;
        this.picture = picture;
    }

    public Teacher(String name, String familyname, int phoneNumber, String employmentDate, long salary, byte[] picture,  Set<PaymentTeacher> paymentsSet, Set<Module> teacherModulesSet) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber = phoneNumber;
        this.employmentDate = utilities.formatDate(employmentDate);
        this.salary = salary;
        this.picture = picture;

        this.paymentTeacherSet= paymentsSet;
        this.teacherModulesSet = teacherModulesSet;
    }

    public void updateTeacher(Teacher newTeacher ) {
        this.name = newTeacher.getName();
        this.familyname = newTeacher.getFamilyname();
        this.employmentDate= newTeacher.getEmploymentDate();
        this.phoneNumber= newTeacher.getPhoneNumber();
        this.salary= newTeacher.getSalary();
        this.picture = newTeacher.getPicture();
    }




    public Set<Module> getTeacherModulesSet() {
        return teacherModulesSet;
    }

    public void setTeacherModulesSet(Set<Module> teacherModulesSet) {
        this.teacherModulesSet = teacherModulesSet;
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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Set<PaymentTeacher> getPaymentTeacherSet() {
        return paymentTeacherSet;
    }

    public void setPaymentTeacherSet(Set<PaymentTeacher> paymentTeacherSet) {
        this.paymentTeacherSet = paymentTeacherSet;
    }
}