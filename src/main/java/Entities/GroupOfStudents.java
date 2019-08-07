package Entities;

import Util.utilities;
import org.hibernate.Session;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;


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
    private int numberSessions;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_module", nullable=false)
    private Module module;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_teacher", nullable=false)
    private Teacher teacher;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groupsSet")
    private Set<Student> studentsSet ;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupOfStudents")
    private Set <SessionOfGroup> sessionSet;

    public GroupOfStudents() {
    }

    public GroupOfStudents(String name, Date startDate, Module module, Teacher teacher) {
        this.name = name;
        this.startDate = startDate;
        this.module = module;
        this.teacher = teacher;
    }

    public GroupOfStudents(String name, String startDate, String paymentType, Module module, Teacher teacher, Set<Student> studentsSet) {
        this.name = name;
        this.startDate = utilities.formatDate(startDate);
        this.paymentType = paymentType;
        this.module = module;
        this.teacher = teacher;
        this.studentsSet = studentsSet;
    }

    public GroupOfStudents(String name, Date startDate, String paymentType, int numberSessions, Module module, Teacher teacher, Set<Student> studentsSet) {
        this.name = name;
        this.startDate = startDate;
        this.paymentType = paymentType;
        this.numberSessions = numberSessions;
        this.module = module;
        this.teacher = teacher;
        this.studentsSet = studentsSet;
    }

    public GroupOfStudents(String name, String startDate, String paymentType, int numberSessions, String startTime, String endTime, Module module, Teacher teacher, Set<Student> studentsSet) {
        this.name = name;
        this.startDate = utilities.formatDate(startDate);
        this.paymentType = paymentType;
        this.numberSessions = numberSessions;
        this.startTime = utilities.formatTime(startTime);
        this.endTime = utilities.formatTime(endTime);
        this.module = module;
        this.teacher = teacher;
        this.studentsSet = studentsSet;
    }

    public GroupOfStudents(String name, String paymentType, int numberSessions) {
        this.name = name;
        this.paymentType = paymentType;
        this.numberSessions = numberSessions;
    }

    public void updateGroup (GroupOfStudents groupOfStudentsNew){

    this.name= groupOfStudentsNew.getName();
    this.numberSessions=groupOfStudentsNew.getNumberSessions();
    this.paymentType= groupOfStudentsNew.getPaymentType();
    }

    public Set<SessionOfGroup> getSessionSet() {
        return sessionSet;
    }

    public void setSessionSet(Set<SessionOfGroup> sessionSet) {
        this.sessionSet = sessionSet;
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

    public Set<Student> getStudentsSet() {
        return studentsSet;
    }

    public void setStudentsSet(Set<Student> studentsSet) {
        this.studentsSet = studentsSet;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GroupOfStudents) return this.id==((GroupOfStudents) obj).getId();
        else return false;
    }


    public boolean removeStudent(int id_student){

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
    }

    public String getTime(Date time) {

        String timeString= time.toString();
        return timeString.substring(11, 16);

    }

    public String getDate(Date time) {

        String timeString= time.toString();
        return timeString.substring(0, timeString.length()-10);

    }


}
