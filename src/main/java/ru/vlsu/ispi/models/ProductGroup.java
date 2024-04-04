package ru.vlsu.ispi.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_group")
@Data
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_group_id")
    private Long id;
    @Column(name = "product_group_name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "productGroup")//(mappedBy)Группа связанная с товаром будет записана в foreign key  в таблице images
    private List<Product> product = new ArrayList<>();
}
