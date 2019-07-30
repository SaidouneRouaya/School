package Entities;

import javax.persistence.*;



@Entity
@Table(name = "module")

public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_module")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "fees")
    private long fees;

    public Module() {
    }

    public Module(String name, long fees) {
        this.name = name;
        this.fees = fees;
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

    public long getFees() {
        return fees;
    }

    public void setFees(long fees) {
        this.fees = fees;
    }
}