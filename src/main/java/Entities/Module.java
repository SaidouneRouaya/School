package Entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.security.acl.Group;
import java.util.HashSet;
import java.util.Iterator;
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "modulesSet")
    private Set<Student>  studentsSet ;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "teacherModulesSet")
    private Set<Teacher>  moduleTeachersSet ;

    public Module() {
    }

    public Module(String name, long fees) {
        this.name = name;
        this.fees = fees;
        this.groupsSet= new HashSet<>();
        this.moduleTeachersSet = new HashSet<>();

        this.studentsSet= new HashSet<>();
    }
 public Module(String name, long fees,  Teacher teacher) {
        this.name = name;
        this.fees = fees;
        this.groupsSet= new HashSet<>();
        this.moduleTeachersSet = new HashSet<>();
     this.moduleTeachersSet.add(teacher);
        this.studentsSet= new HashSet<>();
    }

    public Module(String name, long fees,  Set<Student> studentsSet) {
        this.name = name;
        this.fees = fees;
        this.studentsSet = studentsSet;
        this.groupsSet= new HashSet<>();
        this.moduleTeachersSet = new HashSet<>();
        this.studentsSet= new HashSet<>();
    }


    public void updateModule(Module newModule)  {

        this.moduleTeachersSet = newModule.getModuleTeachersSet();
        this.studentsSet= newModule.getStudentsSet();
        this.groupsSet= newModule.getGroupsSet();
        this.name=newModule.getName();
        this.fees=newModule.getFees();

    }

    public Module(String name, long fees, Set<GroupOfStudents> groupsSet, Set<Student> studentsSet, Set<Teacher> moduleTeachersSet) {
        this.name = name;
        this.fees = fees;
        this.groupsSet = groupsSet;
        this.studentsSet = studentsSet;
        this.moduleTeachersSet = moduleTeachersSet;
        this.groupsSet= new HashSet<>();
        this.moduleTeachersSet = new HashSet<>();
        this.studentsSet= new HashSet<>();
    }

    public Module(String name, long fees,Set<Student> studentsSet, Set<Teacher> moduleTeachersSet) {
        this.name = name;
        this.fees = fees;
        this.studentsSet = studentsSet;
        this.moduleTeachersSet = moduleTeachersSet;
        this.groupsSet= new HashSet<>();
        this.moduleTeachersSet = new HashSet<>();
        this.studentsSet= new HashSet<>();
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

    public boolean removeTeacher (Teacher teacher){

        Iterator<Teacher> it= moduleTeachersSet.iterator();
        boolean bool= false;

        while (it.hasNext() && !bool)
        {
            Teacher teacher1= it.next();
            if(bool= (teacher1.getId()==teacher.getId())){

                System.out.println("remove teacher from module");
                this.getModuleTeachersSet().remove(teacher);
            }
        }
        return bool;
    }
    public boolean removeStudent (Student student){

        Iterator<Student> it= studentsSet.iterator();
        boolean bool= false;

        while (it.hasNext() && !bool)
        {
            Student student1= it.next();
            if(bool= (student1.getId()==student.getId())){


                this.getStudentsSet().remove(student);
            }
        }
        return bool;
    }
 public boolean removeGroup (GroupOfStudents groupOfStudents){

        Iterator<GroupOfStudents> it= groupsSet.iterator();
        boolean bool= false;

        while (it.hasNext() && !bool)
        {
            GroupOfStudents group= it.next();
            if(bool= (group.getId()==groupOfStudents.getId())){


                this.getGroupsSet().remove(group);
            }
        }
        return bool;
    }


}