package ru.vlsu.ispi.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="sales_line")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    private String name;
}