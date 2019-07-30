package Entities;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "session")

public class Session {
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
