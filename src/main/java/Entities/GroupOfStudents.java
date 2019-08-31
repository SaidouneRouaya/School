package Entities;

import Util.utilities;

import javax.persistence.*;
import javax.swing.text.html.HTMLDocument;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;


@Entity
@Transactional
@Table(name = "group_students")
public class GroupOfStudents implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "number_sessions")
    private int numberSessions=0;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(name = "fees")
    private float fees;

    @Column(name= "deleted")
    private  boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_module")
    private Module module;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_teacher")
    private Teacher teacher;


    @OrderBy("startDate desc")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupOfStudents")
    private Set <SessionOfGroup> sessionOfGroupsSet;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groupPay")
    private Set <PaymentStudent> paymentStudentSet;



    public GroupOfStudents() {
    }

    public GroupOfStudents(String name, Date startDate,  float fees, Module module, Teacher teacher) {
        this.name = name;
        this.startDate = startDate;
        this.fees= fees;
        this.module = module;
        this.teacher = teacher;

        this.sessionOfGroupsSet = new HashSet<>();

    }

    public GroupOfStudents(String name, String startDate, String paymentType, float fees,  Module module, Teacher teacher) {
        this.name = name;
        this.startDate = utilities.formatDate(startDate);
        this.paymentType = paymentType;
        this.fees= fees;
        this.module = module;
        this.teacher = teacher;

        this.sessionOfGroupsSet = new HashSet<>();
    }

    public GroupOfStudents(String name, Date startDate, String paymentType, int numberSessions, float fees,   Module module, Teacher teacher) {
        this.name = name;
        this.startDate = startDate;
        this.paymentType = paymentType;
        this.numberSessions = numberSessions;
        this.fees= fees;
        this.module = module;
        this.teacher = teacher;

        this.sessionOfGroupsSet = new HashSet<>();
    }

    public GroupOfStudents(String name, String startDate, String paymentType, String startTime, String endTime, float fees, Module module, Teacher teacher) {
        this.name = name;
        this.startDate = utilities.formatDate(startDate);
        this.paymentType = paymentType;
        this.numberSessions =1;
        this.startTime = utilities.formatTime(startTime);
        this.endTime = utilities.formatTime(endTime);
        this.module = module;
        this.teacher = teacher;

        this.sessionOfGroupsSet = new HashSet<>();
        this.fees= fees;
    }

    public GroupOfStudents(String name, String paymentType, int numberSessions) {
        this.name = name;
        this.paymentType = paymentType;
        this.numberSessions = numberSessions;

        this.sessionOfGroupsSet = new HashSet<>();
    }
    public GroupOfStudents(String name, String paymentType, int numberSessions, Teacher teacher) {
        this.name = name;
        this.paymentType = paymentType;
        this.numberSessions = numberSessions;
        this.teacher = teacher;
        this.sessionOfGroupsSet = new HashSet<>();
    }
    public GroupOfStudents(String name, String paymentType, int numberSessions, Teacher teacher, Module module) {
        this.name = name;
        this.paymentType = paymentType;
        this.numberSessions = numberSessions;
        this.teacher = teacher;
        this.module=module;
        this.sessionOfGroupsSet = new HashSet<>();
    }

    public void updateGroup(GroupOfStudents groupOfStudentsNew) {

        if(groupOfStudentsNew.getTeacher()!=null){ this.teacher = groupOfStudentsNew.getTeacher(); }
        if(groupOfStudentsNew.getModule()!=null) this.module= groupOfStudentsNew.getModule();
        if(groupOfStudentsNew.getName()!=null) this.name = groupOfStudentsNew.getName();
        if(groupOfStudentsNew.getNumberSessions()!=0) this.numberSessions = groupOfStudentsNew.getNumberSessions();
        if(groupOfStudentsNew.getPaymentType ()!=null) this.paymentType = groupOfStudentsNew.getPaymentType();
        if(groupOfStudentsNew.getPaymentStudentSet ()!=null) this.paymentStudentSet = groupOfStudentsNew.getPaymentStudentSet();
    }

    public Set<SessionOfGroup> getSessionOfGroupsSet() {
        return sessionOfGroupsSet;
    }

    public void setSessionOfGroupsSet(Set<SessionOfGroup> sessionOfGroupsSet) {
        this.sessionOfGroupsSet = sessionOfGroupsSet;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getNumberSessions() {
        return numberSessions;
    }

    public void setNumberSessions(int numberSessions) {
        this.numberSessions = numberSessions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<PaymentStudent> getPaymentStudentSet() {
        return paymentStudentSet;
    }

    public void setPaymentStudentSet(Set<PaymentStudent> paymentStudentSet) {
        this.paymentStudentSet = paymentStudentSet;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GroupOfStudents) return this.id==((GroupOfStudents) obj).getId();
        else return false;
    }


    /*public boolean removeStudent(int id_student){

        Iterator<Student> it= this.studentsSet.iterator();
        boolean bool= false;

        while (it.hasNext() && !bool)
        {
            Student student= it.next();

            if(bool= (student.getId()==id_student)){
                this.getStudentsSet().remove(student);
            }
        }
        return bool;
    }*/

    public String getTime(Date time) {

        String timeString= time.toString();
        return timeString.substring(11, 16);

    }

    public String getDate(Date time) {

        String timeString= time.toString();
        return timeString.substring(0, timeString.length()-10);

    }

    public float getFees() {
        return fees;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }

    public SessionOfGroup getLatestSession (){

        Iterator<SessionOfGroup> it= sessionOfGroupsSet.iterator();
        return it.next();


    }


}
