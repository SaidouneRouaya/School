package Entities;


import Util.utilities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Transactional
@Table(name = "session")
public class SessionOfGroup  implements Serializable, Comparable<SessionOfGroup>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    private int id;


    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_group", nullable=false)
    private GroupOfStudents groupOfStudents;

    // represents students present in this session

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sessionsSet")
    private Set<Student> studentsSet ;

    public SessionOfGroup() {
    }

    public SessionOfGroup( String date, GroupOfStudents groupOfStudents) {

        this.date = utilities.formatDate(date);
        this.groupOfStudents=groupOfStudents;
        this.studentsSet= new HashSet<>();
    }



    public GroupOfStudents getGroupOfStudents() {
        return groupOfStudents;
    }

    public void setGroupOfStudents(GroupOfStudents groupOfStudents) {
        this.groupOfStudents = groupOfStudents;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Student> getStudentsSet() {
        return studentsSet;
    }

    public void setStudentsSet(Set<Student> studentsSet) {
        this.studentsSet = studentsSet;
    }

    @Override
    public int compareTo(SessionOfGroup o) {

         return this.date.before(o.getDate()) ? 1:0;
    }
}
