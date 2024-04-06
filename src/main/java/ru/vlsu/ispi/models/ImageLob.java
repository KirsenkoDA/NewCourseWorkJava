package ru.vlsu.ispi.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="image_lob")
@Data
public class ImageLob {
    @Id
    @Column(name="id")
    private Long id;
    @Lob
    private byte[] bytes;
}
