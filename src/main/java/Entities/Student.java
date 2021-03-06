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

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name="subscription_date")
    @Temporal(TemporalType.DATE)
    private Date subscriptionDate;

    @Column(name="discount")
    private long discount=0;



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

    public Student(String name, String familyname, int phoneNumber1, int phoneNumber2, String type, String subscriptionDate, long disocunt) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.type = type;

        this.subscriptionDate = utilities.formatDate(subscriptionDate);
        if(disocunt!=0) this.discount=disocunt;

        this.studentSessionsSet = new HashSet<>();
        this.modulesSet = new HashSet<>();
        this.seancesSet = new HashSet<>();
        this.paymentSet = new HashSet<>();
    }
    public Student(String name, String familyname, int phoneNumber1, int phoneNumber2, String type, String subscriptionDate) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.type = type;
        this.subscriptionDate = utilities.formatDate(subscriptionDate);

        this.studentSessionsSet = new HashSet<>();
        this.modulesSet = new HashSet<>();
        this.seancesSet = new HashSet<>();
        this.paymentSet = new HashSet<>();
    }

    public Student(String name, String familyname, int phoneNumber1, int phoneNumber2, String type, String subscriptionDate, long discount,
                   Set<StudentSession> studentSessionsSet, Set<Module> modulesSet, Set<Seance> seancesSet, Set<PaymentStudent> paymentSet) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.type = type;
        this.subscriptionDate = utilities.formatDate(subscriptionDate);
        this.discount = discount;

        this.studentSessionsSet = studentSessionsSet;
        this.modulesSet = modulesSet;
        this.seancesSet = seancesSet;
        this.paymentSet = paymentSet;
    }
   public Student(String name, String familyname, int phoneNumber1, int phoneNumber2, String type, String subscriptionDate,
                  Set<StudentSession> studentSessionsSet, Set<Module> modulesSet, Set<Seance> seancesSet, Set<PaymentStudent> paymentSet) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.type = type;
        this.subscriptionDate = utilities.formatDate(subscriptionDate);


        this.studentSessionsSet = studentSessionsSet;
        this.modulesSet = modulesSet;
        this.seancesSet = seancesSet;
        this.paymentSet = paymentSet;
    }

    public void updateStudent(Student newStudent ) {

        if (newStudent.getName()!=null) this.name = newStudent.getName();
        if (newStudent.getFamilyname()!=null) this.familyname = newStudent.getFamilyname();
        if (newStudent.getPhoneNumber1()!=0) this.phoneNumber1 = newStudent.getPhoneNumber1();
        if (newStudent.getPhoneNumber2()!=0) this.phoneNumber2 = newStudent.getPhoneNumber2();
        if (newStudent.getType()!=null)this.type = newStudent.getType();
        if (newStudent.getSubscriptionDate()!=null) this.subscriptionDate = newStudent.getSubscriptionDate();
        if (newStudent.getDiscount()!=0) this.discount=newStudent.getDiscount();

        if (newStudent.getPaymentSet()!=null) this.paymentSet= newStudent.getPaymentSet();
        if (newStudent.getStudentSessionsSet()!=null) this.studentSessionsSet= newStudent.getStudentSessionsSet();
        if (newStudent.getModulesSet()!=null)this.modulesSet= newStudent.getModulesSet();
        if (newStudent.getSeancesSet()!=null)this.seancesSet =newStudent.getSeancesSet();


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



    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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



    public boolean removeSeance(int id_seance){

        Iterator<Seance> it= this.seancesSet.iterator();
        boolean bool= false;

        while (it.hasNext() && !bool)
        {
            Seance seance= it.next();
            if(bool= (seance.getId()==id_seance)){
                this.getSeancesSet().remove(seance);
            }
        }
        return bool;
    }



    public boolean removeModule(int id_module){

        Iterator<Module> it= this.modulesSet.iterator();
        boolean bool= false;

        while (it.hasNext() && !bool)
        {
            Module module= it.next();

            if(bool= (module.getId()==id_module)){
                this.getModulesSet().remove(module);
            }
        }
        return bool;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student)  return this.id== ((Student) obj).getId();
        else return false;
    }

    public boolean payedForSession (GroupOfStudents group, Date date){




        if (this.paymentSet.isEmpty()) return false;

        for (PaymentStudent paymentStudent: this.paymentSet){

            if  (paymentStudent.containsGroup(group) && paymentStudent.getDate().before(date)) return true;
            break;
        }

        return false;
    }


    public boolean present(Seance seancee){

        if (this.seancesSet.isEmpty()) return false;
        if (seancee.getDate()==null) return false;


        Iterator<Seance> it = this.seancesSet.iterator();

        Seance seance= it.next();
        while (seance.getId()!=seancee.getId() && it.hasNext() ){
            seance= it.next();
            System.out.println("test seance "+seance.getId());
        }

        return seance.getId() == seancee.getId();

    }


    public GroupOfStudents getGroupOfModule( Module module){

        for (StudentSession aStudentSessionsSet : studentSessionsSet) {

            GroupOfStudents g = aStudentSessionsSet.getSession().getGroupOfStudents();
          //  System.out.println(g.getModule().getId());

            if (g.getModule()!=null && g.getModule().getId() == module.getId()) {
                System.out.println("egalité");
                return g;

            }

        }
        return null;
    }


    public StudentSession getLatestSession (){

        Iterator<StudentSession> it= studentSessionsSet.iterator();

       if (it.hasNext()) return it.next();
       else return new StudentSession();

    }
    public StudentSession getSessionByID (SessionOfGroup session){

        Iterator<StudentSession> it= studentSessionsSet.iterator();

       if (it.hasNext()) {
           StudentSession s=it.next();

           if (s.getSession().getId()==session.getId())  return s ;
           else return null;
       }
       else return null;

    }

}