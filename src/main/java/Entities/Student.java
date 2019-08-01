package Entities;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

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


  /*  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_group",
            joinColumns = {@JoinColumn(name = "id_student") },
            inverseJoinColumns = { @JoinColumn(name = "id_group") })
    private Set<Group> groupsSet;
*/

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_module",
            joinColumns = {@JoinColumn(name = "id_student") },
            inverseJoinColumns = { @JoinColumn(name = "id_module") })
    private Set<Module> modulesSet;

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
        /*SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date parsedSubscriptionDate=null;
        try{ parsedSubscriptionDate = format.parse(subscriptionDate);}catch(Exception e){ this.formatDate(); e.printStackTrace(); }*/

        this.subscriptionDate = subscriptionDate(subscriptionDate);
        if(disocunt!=0) this.discount=disocunt;
       // this.picture = uploadImage(picture);
        this.picture = picture;
    }
    public Student(String name, String familyname, int phoneNumber1, int phoneNumber2, String type, String subscriptionDate, byte[] picture) {
        this.name = name;
        this.familyname = familyname;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.type = type;
        this.subscriptionDate = subscriptionDate(subscriptionDate);
        //this.picture = uploadImage(picture);
        this.picture = picture;
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
    }



    public Set<Module> getModulesSet() {
        return modulesSet;
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

    public Date subscriptionDate(String date){
        Map<Pattern, DateFormat> dateFormatPatterns = new HashMap<Pattern, DateFormat>();
        dateFormatPatterns.put(Pattern.compile("^((0|1)\\d{1})-((0|1|2)\\d{1})-((20)\\d{2})"), new SimpleDateFormat("MM-dd-yyyy"));
        dateFormatPatterns.put(Pattern.compile("^((0|1)\\d{1})/((0|1|2)\\d{1})/((20)\\d{2})"), new SimpleDateFormat("MM/dd/yyyy"));

        dateFormatPatterns.put(Pattern.compile("^((0|1|2)\\d{1})-((0|1)\\d{1})-((20)\\d{2})"), new SimpleDateFormat("dd-MM-yyyy"));
        dateFormatPatterns.put(Pattern.compile("^((0|1|2)\\d{1})/((0|1)\\d{1})/((20)\\d{2})"), new SimpleDateFormat("dd/MM/yyyy"));

        dateFormatPatterns.put(Pattern.compile("^((20)\\d{2})-((0|1)\\d{1})-((0|1|2)\\d{1})"), new SimpleDateFormat("yyyy-MM-dd"));
        dateFormatPatterns.put(Pattern.compile("^((20)\\d{2})/((0|1)\\d{1})/((0|1|2)\\d{1})"), new SimpleDateFormat("yyyy/MM/dd"));

        Date finalDate=null;
        try{
            DateFormat finalFormat = new SimpleDateFormat("MM/dd/yyyy");

            for (Pattern pattern : dateFormatPatterns.keySet()) {
                if (pattern.matcher(date).matches()) {
                    Date dateFormatted = dateFormatPatterns.get(pattern).parse(date);

                    finalDate  = finalFormat.parse(finalFormat.format(dateFormatted));
                    System.out.println(dateFormatted);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return finalDate;
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

}