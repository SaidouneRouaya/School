package Entities;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;

@Entity
@Transactional
@Table(name = "student_session")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.student",
                joinColumns = @JoinColumn(name = "id_student")),
        @AssociationOverride(name = "primaryKey.session",
                joinColumns = @JoinColumn(name = "id_session")) })
public class StudentSession {

    // composite-id key
    @EmbeddedId
    private StudentSessionID primaryKey = new StudentSessionID();

    // additional fields
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Transient
    public Student getStudent() {
        return getPrimaryKey().getStudent();
    }

    @Transient
    public SessionOfGroup getSession() {
        return getPrimaryKey().getSession();
    }

    public StudentSessionID getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(StudentSessionID primaryKey) {
        this.primaryKey = primaryKey;
    }

    public StudentSession(StudentSessionID primaryKey, Date startDate) {
        this.primaryKey = primaryKey;
        this.startDate = startDate;
    }

    public StudentSession() {
    }


    @Transient
    public void setStudent(Student student) {
        getPrimaryKey().setStudent(student);
    }

    @Transient
    public void setSession(SessionOfGroup  session) {
        getPrimaryKey().setSession(session);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
