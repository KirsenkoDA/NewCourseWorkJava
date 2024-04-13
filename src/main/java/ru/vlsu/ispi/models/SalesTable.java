package ru.vlsu.ispi.models;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="sales_table")
public class SalesTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Status status;
    @OneToMany(mappedBy = "salesTable", fetch = FetchType.EAGER)
    private List<SalesLine> salesLines = new ArrayList<>();
    private String address;

    @PrePersist
    private void init() {
        dateCreated = LocalDateTime.now();
    }
}