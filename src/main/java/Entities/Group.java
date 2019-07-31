package Entities;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity

@Table(name = "group")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private int id;

    @Column(name="name")
    private String name="";

    @Column(name="start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_module", nullable=false)
    private Module module;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_teacher", nullable=false)
    private Teacher teacher;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private Set<Session> sessionsList;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groupsSet")
    private Set<Student>  studentsSet ;


    public Group() {
    }

    public Group(String name, String startDate) {
        this.name = name;

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date parsedStartDate=null;
        try{ parsedStartDate = format.parse(startDate);}catch(Exception e){ e.printStackTrace();}
        this.startDate = parsedStartDate;
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

    public Set<Session> getSessionsList() {
        return sessionsList;
    }

    public void setSessionsList(Set<Session> sessionsList) {
        this.sessionsList = sessionsList;
    }

    public Set<Student> getStudentsSet() {
        return studentsSet;
    }

    public void setStudentsSet(Set<Student> studentsSet) {
        this.studentsSet = studentsSet;
    }
}
