package ru.vlsu.ispi.dto.productDTO;

import lombok.Data;

@Data
public class EditProductDTO {
    //    @NotEmpty(message = "Имя не может быть пустым")
//    @Size(min = 2, max = 255, message = "Имя должно содержать от 2 до 255 символов")
    private Long id;
    private String name;

    //    @NotEmpty(message = "Описание не должно быть пустым")
//    @Size(min = 2, max = 1000, message = "Имя должно содержать от 2 до 30 символов")
    private String discription;

    //    @Min(value=0, message = "Значение цены может быть только положительным")
    private float price;

    private Long productGroupId;
    private int quantity;

    private String url1;
    private String url2;
    private String url3;
}
