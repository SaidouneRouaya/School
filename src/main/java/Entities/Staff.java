package Entities;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Transactional
@Table(name = "staff")

public class Staff  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_staff")
    private int id;

    @Column(name="name")
    private String name="";

    @Column(name="familyname")
    private String familyname="";

    @Column(name="phone")
    private int phoneNumber;

    @Column(name="job")
    private String job;

    @Column(name="employment_date")
    @Temporal(TemporalType.DATE)
    private Date employmentDate;

    @Column(name="salary")
    private long salary;

    @Column(name="picture")
    @Lob
    private byte[] picture;

    public Staff() {
    }

    public Staff(String name, String familyname, int phoneNumber, String job, String employmentDate, long salary, byte[] picture) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber = phoneNumber;
        this.job = job;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date parsedEmploymentDate=null;
        try{ parsedEmploymentDate = format.parse(employmentDate);}catch(Exception e){ e.printStackTrace();}
        this.employmentDate = parsedEmploymentDate;
        this.salary = salary;
        this.picture = picture;
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

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}