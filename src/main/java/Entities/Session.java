package Entities;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Column(name = "timing")
    @Temporal(TemporalType.TIME)
    private Date timing;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;


  /*  @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_group", nullable=false)
    private Group group;
*/
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

        this.timing = parsedTime;
        this.date = parsedDate;
    }

    public Set<Module> getModulesSet() {
        return modulesSet;
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

    public Date getTiming() {
        return timing;
    }

    public void setTiming(Date timing) {
        this.timing = timing;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
