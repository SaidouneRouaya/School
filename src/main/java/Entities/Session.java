package Entities;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Transactional
@Table(name = "session")

public class Session  implements Serializable {
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sessionsSet")
    private Set<Module> modulesSet ;


    public Session() {
    }

    public Session(String timing, String date) {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date parsedDate=null;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date parsedTime=null;

        try{
            parsedDate = format.parse(date);
            parsedTime=timeFormat.parse(timing);

        }catch(Exception e){ e.printStackTrace();}


        this.date = parsedDate;
    }

    public Set<Module> getModulesSet() {
        return modulesSet;
    }

    public GroupOfStudents getGroupOfStudents() {
        return groupOfStudents;
    }

    public void setGroupOfStudents(GroupOfStudents groupOfStudents) {
        this.groupOfStudents = groupOfStudents;
    }

    public void setModulesSet(Set<Module> modulesSet) {
        this.modulesSet = modulesSet;
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
}
