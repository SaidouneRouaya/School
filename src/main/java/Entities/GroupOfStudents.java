package Entities;

import Util.utilities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_module", nullable=false)
    private Module module;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_teacher", nullable=false)
    private Teacher teacher;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groupsSet")
    private Set<Student> studentsSet ;

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


}
