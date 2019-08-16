package Entities;


import Util.utilities;
import org.hibernate.Session;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@DynamicUpdate
@Transactional
@Table(name = "student")

public class Student  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private int id;

    @Column(name="name")
    private String name="";

    @Column(name="familyname")
    private String familyname="";

    @Column(name="phone_number1")
    private int phoneNumber1;

    @Column(name="phone_number2")
    private int phoneNumber2;

    @Column(name="type")
    private String type;

    @Column(name="subscription_date")
    @Temporal(TemporalType.DATE)
    private Date subscriptionDate;

    @Column(name="discount")
    private long discount=0;

    @Column(name="picture")
    @Lob
    private byte[] picture;


   /*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_session",
            joinColumns = {@JoinColumn(name = "id_student") },
            inverseJoinColumns = { @JoinColumn(name = "id_group") })
    private Set<GroupOfStudents> groupsSet;
*/

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "primaryKey.student")
    @OrderBy("startDate Asc")
    private Set<StudentSession> studentSessionsSet;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_module",
            joinColumns = {@JoinColumn(name = "id_student") },
            inverseJoinColumns = { @JoinColumn(name = "id_module") })
    private Set<Module> modulesSet;


    //represents sessions when student was present
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_seance",
            joinColumns = {@JoinColumn(name = "id_student") },
            inverseJoinColumns = { @JoinColumn(name = "id_seance") })
    private Set<Seance> seancesSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentPay")
    private Set<PaymentStudent> paymentSet;


    public Student() {
    }

    public Student(String name, String familyname, int phoneNumber1, int phoneNumber2, String type, String subscriptionDate, long disocunt,  byte[] picture) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.type = type;

        this.subscriptionDate = utilities.formatDate(subscriptionDate);
        if(disocunt!=0) this.discount=disocunt;
       // this.picture = uploadImage(picture);
        this.picture = picture;
        this.studentSessionsSet = new HashSet<>();
        this.modulesSet = new HashSet<>();
        this.seancesSet = new HashSet<>();
        this.paymentSet = new HashSet<>();
    }
    public Student(String name, String familyname, int phoneNumber1, int phoneNumber2, String type, String subscriptionDate, byte[] picture) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.type = type;
        this.subscriptionDate = utilities.formatDate(subscriptionDate);
        //this.picture = uploadImage(picture);
        this.picture = picture;
        this.studentSessionsSet = new HashSet<>();
        this.modulesSet = new HashSet<>();
        this.seancesSet = new HashSet<>();
        this.paymentSet = new HashSet<>();
    }

    public Student(String name, String familyname, int phoneNumber1, int phoneNumber2, String type, String subscriptionDate, long discount, byte[] picture,
                   Set<StudentSession> studentSessionsSet, Set<Module> modulesSet, Set<Seance> seancesSet, Set<PaymentStudent> paymentSet) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.type = type;
        this.subscriptionDate = utilities.formatDate(subscriptionDate);
        this.discount = discount;
        this.picture = picture;
        this.studentSessionsSet = studentSessionsSet;
        this.modulesSet = modulesSet;
        this.seancesSet = seancesSet;
        this.paymentSet = paymentSet;
    }
   public Student(String name, String familyname, int phoneNumber1, int phoneNumber2, String type, String subscriptionDate, byte[] picture,
                  Set<StudentSession> studentSessionsSet, Set<Module> modulesSet, Set<Seance> seancesSet, Set<PaymentStudent> paymentSet) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.type = type;
        this.subscriptionDate = utilities.formatDate(subscriptionDate);

        this.picture = picture;
        this.studentSessionsSet = studentSessionsSet;
        this.modulesSet = modulesSet;
        this.seancesSet = seancesSet;
        this.paymentSet = paymentSet;
    }

    public void updateStudent(Student newStudent ) {
        this.name = newStudent.getName();
        this.familyname = newStudent.getFamilyname();
        this.phoneNumber1 = newStudent.getPhoneNumber1();
        this.phoneNumber2 = newStudent.getPhoneNumber2();
        this.type = newStudent.getType();
        this.subscriptionDate = newStudent.getSubscriptionDate();
        this.discount=newStudent.getDiscount();
        this.picture = newStudent.getPicture();
        this.paymentSet= newStudent.getPaymentSet();
        this.studentSessionsSet= newStudent.getStudentSessionsSet();
        this.modulesSet= newStudent.getModulesSet();
        this.seancesSet =newStudent.getSeancesSet();


    }

    public Set<StudentSession> getStudentSessionsSet() {
        return studentSessionsSet;
    }

    public void setStudentSessionsSet(Set<StudentSession> studentSessionsSet) {
        this.studentSessionsSet = studentSessionsSet;
    }

    public Set<Module> getModulesSet() {
        return modulesSet;
    }

    public Set<Seance> getSeancesSet() {
        return seancesSet;
    }

    public void setSeancesSet(Set<Seance> sessionsSet) {
        this.seancesSet = sessionsSet;
    }

    public void setModulesSet(Set<Module> modulesSet) {
        this.modulesSet = modulesSet;
    }

    public Set<PaymentStudent> getPaymentSet() {
        return paymentSet;
    }

    public void setPaymentSet(Set<PaymentStudent> paymentSet) {
        this.paymentSet = paymentSet;
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

    public int getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(int phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public int getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(int phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public  byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public void formatDate()
    {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            this.setSubscriptionDate(format.parse(format.format(this.getSubscriptionDate())));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public byte[] uploadImage(String picture){

        byte[] imageInBytes=null;

        try {
            File imagePath = new File(picture); //here we given fully specified image path.

            imageInBytes = new byte[(int)imagePath.length()]; //image convert in byte form

            FileInputStream inputStream = new FileInputStream(imagePath);  //input stream object create to read the file

            inputStream.read(imageInBytes); // here inputstream object read the file
            inputStream.close();

        }catch (IOException ex){
            ex.printStackTrace();
        }
        return imageInBytes;
    }

    public void loadImage(){



        try{

            File imageFile = new File("profilePicture"); // we can put any name of file (just name of new file created).
            FileOutputStream outputStream = new FileOutputStream(imageFile); // it will create new file (same location of class)
            outputStream.write(this.picture); // image write in "myImage.jpg" file
            outputStream.close(); // close the output stream

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }



    public boolean removeSeance(int id_session){

        Iterator<Seance> it= this.seancesSet.iterator();
        boolean bool= false;

        while (it.hasNext() && !bool)
        {
            Seance seance= it.next();
            if(bool= (seance.getId()==id_session)){
                this.getSeancesSet().remove(seance);
            }
        }
        return bool;
    }

    public boolean removePayment(int id_payment){

        Iterator<PaymentStudent> it= this.paymentSet.iterator();
        boolean bool= false;

        while (it.hasNext() && !bool)
        {
            PaymentStudent paymentStudent= it.next();

            if(bool= (paymentStudent.getId()==id_payment)){
                this.getPaymentSet().remove(paymentStudent);
            }
        }
        return bool;
    }

    public boolean wasPresentinSession(Seance session){

        System.out.println(this.getSeancesSet().contains(session));
        return this.getSeancesSet().contains(session);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student)  return this.id== ((Student) obj).getId();
        else return false;
    }

    public boolean payedForSession (GroupOfStudents group, Date date){

        if (this.paymentSet.isEmpty()) return false;
        for (PaymentStudent paymentStudent: this.paymentSet){
            if  (paymentStudent.containsGroup(group) &&
                    (paymentStudent
                            .getDate()
                            .before(date))) return true;
            break;
        }

        return false;
    }


    public boolean present(Seance session){

        if (this.seancesSet.isEmpty()) return false;
        for (Seance seance : this.seancesSet){
            if ( seance.getId()==session
                    .getId() ) return true;
            break;
        }

        return false;

    }


    public GroupOfStudents getGroupOfModule( Module module){

        for (StudentSession aStudentSessionsSet : studentSessionsSet) {

            System.out.println(module.getId());
            GroupOfStudents g = aStudentSessionsSet.getSession().getGroupOfStudents();
            System.out.println(g.getModule().getId());

            if (g.getModule().getId() == module.getId()) {
                System.out.println("egalité");
                return g;

            }

        }
        return new GroupOfStudents();
    }


    public StudentSession getLatestSession (){

        Iterator<StudentSession> it= studentSessionsSet.iterator();

       if (it.hasNext()) return it.next();
       else return new StudentSession();

    }

}