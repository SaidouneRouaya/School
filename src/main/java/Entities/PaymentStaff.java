package Entities;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

import java.util.Date;

@Entity
@Transactional
@Table(name = "payment_staff")
public class PaymentStaff implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private Long ammount;

    @Column(name = "receiver")
    private String receiver;



}
