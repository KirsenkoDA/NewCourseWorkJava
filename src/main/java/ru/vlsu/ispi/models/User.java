package ru.vlsu.ispi.models;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "user_table")
@Data
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="email", unique = true)
    @NotEmpty(message = "Это поле не может быть пустым")
    @Email(message = "E-mail должен быть валидным")
    private String email;

    @Column(name="phone_number")
    @NotEmpty(message = "Это поле не может быть пустым")
    @Size(min = 11, max = 11, message = "Номер телефона должен содержать 11 символов")
    private String phoneNumber;

    @Column(name="name", nullable = false)
    @NotEmpty(message = "Это поле не может быть пустым")
    private String name;

    @Column(name="active")
    private boolean active;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "image_id")
//    private Image avatar;

    @Column(name="password", length = 1000)
    @NotEmpty(message = "Это поле не может быть пустым")
    @Size(min = 8, max = 1000, message = "Пароль должен содержать от 8 до 30 символов")
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
//
//    private LocalDateTime dateOfCreated;
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
//    private List<SalesTable> salesTables = new ArrayList<>();

//    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
//    private Cart cart;
//    @PrePersist
//    private void init(){
//        dateOfCreated=LocalDateTime.now();
//    }
    //Security

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}