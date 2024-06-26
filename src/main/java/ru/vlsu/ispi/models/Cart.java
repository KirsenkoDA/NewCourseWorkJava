package ru.vlsu.ispi.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="cart_table")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private int quantity;

    private float price;

    private float amount;
}
