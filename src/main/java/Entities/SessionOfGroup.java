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
@Table(name = "session")
public class SessionOfGroup  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    private int id;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "number_seances")
    private int numberOfSeances;

    @OrderBy("date Asc")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sessionOfGroup")
    private Set<Seance> seancesSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_group", nullable=false)
    private GroupOfStudents groupOfStudents;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "primaryKey.session")
    private Set<StudentSession> studentSessionsSet;



    public SessionOfGroup() {
    }

    public SessionOfGroup(Date startDate, Set<Seance> seancesSet, GroupOfStudents groupOfStudents) {
        this.startDate = startDate;
        this.seancesSet = seancesSet;
        this.groupOfStudents = groupOfStudents;
    }

    public SessionOfGroup(String startDate, Set<Seance> seancesSet, GroupOfStudents groupOfStudents) {
        this.startDate = utilities.formatDate(startDate);
        this.seancesSet = seancesSet;
        this.groupOfStudents = groupOfStudents;
    }

    public SessionOfGroup(String startDate) {
        this.startDate = utilities.formatDate(startDate);
        this.seancesSet= new HashSet<>();
        this.studentSessionsSet= new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Set<Seance> getSeancesSet() {
        return seancesSet;
    }

    public void setSeancesSet(Set<Seance> seancesSet) {
        this.seancesSet = seancesSet;
    }

    public GroupOfStudents getGroupOfStudents() {
        return groupOfStudents;
    }

    public void setGroupOfStudents(GroupOfStudents groupOfStudents) {
        this.groupOfStudents = groupOfStudents;
    }

    public void updateSession(SessionOfGroup newSession){
        this.startDate=newSession.getStartDate();
        this.seancesSet= newSession.getSeancesSet();
        this.groupOfStudents=newSession.getGroupOfStudents();
        this.studentSessionsSet= newSession.getStudentSessionsSet();

    }

    public Set<StudentSession> getStudentSessionsSet() {
        return studentSessionsSet;
    }

    public void setStudentSessionsSet(Set<StudentSession> studentSessionsSet) {
        this.studentSessionsSet = studentSessionsSet;
    }

    public int getNumberOfSeances() {
        return numberOfSeances;
    }

    public void setNumberOfSeances(int numberOfSeances) {
        this.numberOfSeances = numberOfSeances;
    }
}
