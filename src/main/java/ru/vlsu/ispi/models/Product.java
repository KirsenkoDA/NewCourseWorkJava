package ru.vlsu.ispi.models;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(mappedBy = "product")
    private List<Cart> carts;

    @Column(name = "product_price")
    @Min(value=0, message = "Значение цены может быть только положительным")
    private float price;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductGroup productGroup;

    @Column(name = "preview_image_id")
    private String previewImage;

    @Column(name = "quantity")
    private int quantity;
//    public void updateImageFromProduct(Image image, int index)
//    {
//        image.setProduct(this);
//        images.set(index, image);
//    }
//    public void addImageToProduct(Image image)//Метод добавления foreign key в таблицы
//    {
//        image.setProduct(this);
//        images.add(image);
//    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
