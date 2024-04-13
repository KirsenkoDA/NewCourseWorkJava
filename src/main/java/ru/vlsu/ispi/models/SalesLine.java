package ru.vlsu.ispi.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="sales_line")
public class SalesLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private SalesTable salesTable;
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    private int quantity;
    private float price;

}