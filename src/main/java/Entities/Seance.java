package Entities;


import Util.utilities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Transactional
@Table(name = "seance")
public class Seance implements Serializable, Comparable<Seance>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seance")
    private int id;


    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_session", nullable=false)
    private SessionOfGroup sessionOfGroup;

    // represents students present in this seance

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "seancesSet")
    private Set<Student> studentsSet ;


    public Seance() {
    }

    public Seance(String date, SessionOfGroup sessionOfGroup) {

        this.date = utilities.formatDate(date);
        this.sessionOfGroup=sessionOfGroup;
        this.studentsSet= new HashSet<>();
    }



    public SessionOfGroup getSessionOfGroup() {
        return sessionOfGroup;
    }

    public void setSessionOfGroup(SessionOfGroup sessionOfGroup) {
        this.sessionOfGroup = sessionOfGroup;
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
    public int compareTo(Seance o) {

         return this.date.before(o.getDate()) ? 1:0;
    }


    public void updateSeance(Seance newSeance){

        if(newSeance.getDate()!=null) this.date=newSeance.getDate();
        if(newSeance.getSessionOfGroup()!=null) this.sessionOfGroup= newSeance.getSessionOfGroup();
        if(newSeance.getStudentsSet()!=null) this.studentsSet=newSeance.getStudentsSet();
    }

    public boolean removeStudent (Student student){

        Iterator<Student> it= this.studentsSet.iterator();
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

}
