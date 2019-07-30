package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "module")

public class Module implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_module")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "fees")
    private long fees;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "module")
    private Set<Group> groupsSet;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "module_session",
            joinColumns = {@JoinColumn(name = "id_module") },
            inverseJoinColumns = { @JoinColumn(name = "id_session") })
    private Set<Session> sessionsSet;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "modulesSet")
    private Set<Student>  studentsSet ;

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