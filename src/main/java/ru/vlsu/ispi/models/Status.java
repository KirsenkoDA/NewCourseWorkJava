package ru.vlsu.ispi.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="status_table")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private List<SalesTable> salesTables = new ArrayList<>();
}