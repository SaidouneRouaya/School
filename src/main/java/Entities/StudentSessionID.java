package Entities;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class StudentSessionID implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_student", nullable=false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_session", nullable=false)
    private SessionOfGroup session;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SessionOfGroup getSession() {
        return session;
    }

    public void setSession(SessionOfGroup session) {
        this.session = session;
    }

    public StudentSessionID() {
    }

    public StudentSessionID(Student student, SessionOfGroup session) {
        this.student = student;
        this.session = session;
    }

}
