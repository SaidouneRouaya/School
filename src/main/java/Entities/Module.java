package Entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Set;

@Entity
@Transactional
@Table(name = "module")


public class Module implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_module")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sessions_number")
    private int numberSessions;

    @Column(name = "fees")
    private long fees;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
    private Set<Group> groupsSet;*/

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "module_session",
            joinColumns = {@JoinColumn(name = "id_module") },
            inverseJoinColumns = { @JoinColumn(name = "id_session") })
    private Set<Session> sessionsSet;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "modulesSet")
    private Set<Student>  studentsSet ;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "teacherModulesSet")
    private Set<Teacher>  moduleTeachersSet ;

    public Module() {
    }

    public Module(String name, long fees) {
        this.name = name;
        this.fees = fees;
    }

    public Module(String name, long fees, Set<Session> sessionsSet, Set<Student> studentsSet) {
        this.name = name;
        this.fees = fees;
       // this.groupsSet = groupsSet;
        this.sessionsSet = sessionsSet;
        this.studentsSet = studentsSet;
    }

    public Module(String name, int numberSessions, long fees,  Set<Session> sessionsSet, Set<Student> studentsSet, Set<Teacher> moduleTeachersSet) {
        this.name = name;
        this.numberSessions = numberSessions;
        this.fees = fees;
    //    this.groupsSet = groupsSet;
        this.sessionsSet = sessionsSet;
        this.studentsSet = studentsSet;
        this.moduleTeachersSet = moduleTeachersSet;
    }

    public Set<Teacher> getModuleTeachersSet() {
        return moduleTeachersSet;
    }

    public void setModuleTeachersSet(Set<Teacher> moduleTeachersSet) {
        this.moduleTeachersSet = moduleTeachersSet;
    }

    public int getNumberSessions() {
        return numberSessions;
    }

    public void setNumberSessions(int numberSessions) {
        this.numberSessions = numberSessions;
    }

  /*  public Set<Group> getGroupsSet() {
        return groupsSet;
    }

    public void setGroupsSet(Set<Group> groupsSet) {
        this.groupsSet = groupsSet;
    }
*/
    public Set<Session> getSessionsSet() {
        return sessionsSet;
    }

    public void setSessionsSet(Set<Session> sessionsSet) {
        this.sessionsSet = sessionsSet;
    }

    public Set<Student> getStudentsSet() {
        return studentsSet;
    }

    public void setStudentsSet(Set<Student> studentsSet) {
        this.studentsSet = studentsSet;
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

    public long getFees() {
        return fees;
    }

    public void setFees(long fees) {
        this.fees = fees;
    }
}