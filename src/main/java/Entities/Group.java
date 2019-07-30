package Entities;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private int id;

    @Column(name="name")
    private String name="";

    @Column(name="start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

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
}
