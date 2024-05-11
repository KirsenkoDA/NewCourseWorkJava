package ru.vlsu.ispi.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ru.vlsu.ispi.models.Role;

import javax.validation.constraints.Size;
import java.util.Set;

public class UserUpdatePADTO {
    Long id;
    @NotEmpty(message = "Это поле не может быть пустым")
    String name;
    @NotEmpty(message = "Это поле не может быть пустым")
    @Email(message = "E-mail должен быть валидным")
    String email;
    @NotEmpty(message = "Это поле не может быть пустым")
    @Size(min = 11, max = 11, message = "Номер телефона должен содержать 11 символов")
    String phoneNumber;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
