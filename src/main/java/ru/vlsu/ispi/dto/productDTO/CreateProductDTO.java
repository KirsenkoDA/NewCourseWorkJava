package ru.vlsu.ispi.dto.productDTO;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
@Data
public class CreateProductDTO {
//    @NotEmpty(message = "Имя не может быть пустым")
//    @Size(min = 2, max = 255, message = "Имя должно содержать от 2 до 255 символов")
    private String name;

//    @NotEmpty(message = "Описание не должно быть пустым")
//    @Size(min = 2, max = 1000, message = "Имя должно содержать от 2 до 30 символов")
    private String discription;

//    @Min(value=0, message = "Значение цены может быть только положительным")
    private float price;

    private Long productGroupId;

    private String url1;
    private String url2;
    private String url3;



}
