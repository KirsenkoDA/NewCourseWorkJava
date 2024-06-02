//package ru.vlsu.ispi.models;
//
//import lombok.Data;
//import ru.vlsu.ispi.services.ImageService;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="image_table")
//@Data
//public class Image {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="id")
//    private Long id;
//    @Column(name="url")
//    private String url;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Product product;
//
//}
