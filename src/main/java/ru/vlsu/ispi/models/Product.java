package ru.vlsu.ispi.models;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Entity
@Table(name = "product_table")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 255, message = "Имя должно содержать от 2 до 255 символов")
    private String name;
    @Column(name = "product_discription")
    @NotEmpty(message = "Описание не должно быть пустым")
    @Size(min = 2, max = 1000, message = "Имя должно содержать от 2 до 30 символов")
    private String discription;
    @Column(name = "product_price")
    @Min(value=0, message = "Значение цены может быть только положительным")
    private float price;
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductGroup productGroup;
}
