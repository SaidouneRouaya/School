package Entities;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@Entity
@Transactional
@Table(name = "profile")
public class Profile  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profile")
    private int id;

    @Column(name="name")
    private String name="";

    @Column(name="familyname")
    private String familyname="";

    @Column(name="type")
    private String type="";

    @Column(name="picture")
    @Lob
    private byte[] picture;

    @Column(name= "username")
    private String username ;

    @Column(name="password")
    private String password;

    public Profile() {
    }

    public Profile(String name, String familyname, String type) {
        this.name = name;
        this.familyname = familyname;
        this.type = type;
    }

    public Profile(String name, String familyname, String type, byte[] picture) {
        this.name = name;
        this.familyname = familyname;
        this.type = type;
        this.picture = picture;
    }

    public Profile(String name, String familyname, String type, byte[] picture, String username, String password) {
        this.name = name;
        this.familyname = familyname;
        this.type = type;
        this.picture = picture;
        this.username = username;
        this.password= password;
    }

    public void updateProfile(Profile newProfile){
        this.name = newProfile.getName();
        this.familyname = newProfile.getFamilyname();
        this.type = newProfile.getType();
        this.picture = newProfile.getPicture();
        this.username = newProfile.getUsername();
        this.password= newProfile.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
