package ru.vlsu.ispi.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "wallet")
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @OneToOne(mappedBy = "wallet")
    private User user;

    private int balance;
}
