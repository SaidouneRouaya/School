package Entities;


import Util.utilities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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


    @Column(name="picture")
    @Lob
    private byte[] picture;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private Set<GroupOfStudents> groupsSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacherPayed")
    private Set<PaymentTeacher> paymentTeacherSet;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_module",
            joinColumns = {@JoinColumn(name = "id_teacher") },
            inverseJoinColumns = { @JoinColumn(name = "id_module") })
    private Set<Module> teacherModulesSet;


    public Teacher() {
    }

    public Teacher(String name, String familyname, int phoneNumber, String employmentDate, byte[] picture) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber = phoneNumber;
        this.groupsSet= new HashSet<>();
        this.employmentDate = utilities.formatDate(employmentDate);
        this.picture = picture;
    }

    public Teacher(String name, String familyname, int phoneNumber, String employmentDate, byte[] picture,  Set<PaymentTeacher> paymentsSet, Set<Module> teacherModulesSet) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber = phoneNumber;
        this.employmentDate = utilities.formatDate(employmentDate);

        this.picture = picture;
        this.groupsSet= new HashSet<>();
        this.paymentTeacherSet= paymentsSet;
        this.teacherModulesSet = teacherModulesSet;
    }

    public void updateTeacher(Teacher newTeacher ) {
        this.name = newTeacher.getName();
        this.familyname = newTeacher.getFamilyname();
        this.employmentDate= newTeacher.getEmploymentDate();
        this.phoneNumber= newTeacher.getPhoneNumber();

        this.picture = newTeacher.getPicture();
        this.groupsSet= new HashSet<>();
    }

    public Teacher(String name, String familyname, int phoneNumber, String employmentDate, byte[] picture, Set<GroupOfStudents> groupsSet, Set<PaymentTeacher> paymentTeacherSet, Set<Module> teacherModulesSet) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber = phoneNumber;
        this.employmentDate =utilities.formatDate(employmentDate);

        this.picture = picture;
        this.groupsSet = groupsSet;
        this.paymentTeacherSet = paymentTeacherSet;
        this.teacherModulesSet = teacherModulesSet;
    }

    public Set<GroupOfStudents> getGroupsSet() {
        return groupsSet;
    }

    public void setGroupsSet(Set<GroupOfStudents> groupsSet) {
        this.groupsSet = groupsSet;
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

    @Override
    public boolean equals(Object obj) {
        return this.id== ((Teacher) obj).getId();
    }
}