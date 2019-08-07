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


    @Column(name = "fees")
    private long fees;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
    private Set<GroupOfStudents> groupsSet;

   /* @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "module_session",
            joinColumns = {@JoinColumn(name = "id_module") },
            inverseJoinColumns = { @JoinColumn(name = "id_session") })
    private Set<Session> sessionsSet;
*/

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

    public Module(String name, long fees,  Set<Student> studentsSet) {
        this.name = name;
        this.fees = fees;
        this.studentsSet = studentsSet;
    }

    public void updateModule(Module newModule)  {

        this.name=newModule.getName();
        this.fees=newModule.getFees();

    }

    public Module(String name, long fees, Set<GroupOfStudents> groupsSet, Set<Student> studentsSet, Set<Teacher> moduleTeachersSet) {
        this.name = name;
        this.fees = fees;
        this.groupsSet = groupsSet;
        this.studentsSet = studentsSet;
        this.moduleTeachersSet = moduleTeachersSet;
    }

    public Module(String name, long fees,Set<Student> studentsSet, Set<Teacher> moduleTeachersSet) {
        this.name = name;
        this.fees = fees;
        this.studentsSet = studentsSet;
        this.moduleTeachersSet = moduleTeachersSet;
    }

    public Set<Teacher> getModuleTeachersSet() {
        return moduleTeachersSet;
    }

    public void setModuleTeachersSet(Set<Teacher> moduleTeachersSet) {
        this.moduleTeachersSet = moduleTeachersSet;
    }


   public Set<GroupOfStudents> getGroupsSet() {
        return groupsSet;
    }

    public void setGroupsSet(Set<GroupOfStudents> groupsSet) {
        this.groupsSet = groupsSet;
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