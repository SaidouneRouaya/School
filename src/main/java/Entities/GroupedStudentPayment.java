package Entities;

import java.util.Date;

public class GroupedStudentPayment {

    private Date date;

    public GroupedStudentPayment() {
    }

    public GroupedStudentPayment(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
