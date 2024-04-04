package ru.vlsu.ispi.dto.productGroupDAO;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class EditProductGroupDAO {
    Long id;
    @NotEmpty(message = "Это поле не может быть пустым")
    String name;
    @Length(max = 10000)
    private String svgIcon;
}
