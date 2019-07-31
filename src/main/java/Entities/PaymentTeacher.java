package Entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Transactional
@Table(name = "payment_teacher")
public class PaymentTeacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private int id;


    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "module")
    private String module;

    @Column(name = "payment_type")
    private String paymentType;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "paymentsSet")
    private Set<Teacher> teachersSet ;



}
