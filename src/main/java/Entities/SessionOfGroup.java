package Entities;


import Util.utilities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Transactional
@Table(name = "session")
public class SessionOfGroup  implements Serializable {
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

   /* @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sessionsSet")
    private Set<Module> modulesSet ;
*/

    public SessionOfGroup() {
    }

    public SessionOfGroup( String date, GroupOfStudents groupOfStudents) {

        this.date = utilities.formatDate(date);
        this.groupOfStudents=groupOfStudents;
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
}
