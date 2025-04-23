package com.luciana.challenge_factorIT.entities;

import com.luciana.challenge_factorIT.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name= "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 25)
    private String name;
    @Column(nullable = false, length = 25)
    private String surname;
    @Column(nullable = false, unique = true, length = 8)
    private String dni;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean deleted;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @OneToMany(mappedBy = "user",  fetch = FetchType.LAZY)
    private List<Cart> listCart;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Vip vip;
    public UserEntity() {
    }

    public UserEntity(String name, String surname, String dni, String username, String password, boolean deleted, LocalDateTime deletedAt, Role role) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.username = username;
        this.password = password;
        this.deleted = deleted;
        this.deletedAt = deletedAt;
        this.role = role;
    }

    public UserEntity(String name, String surname, String dni, String username, String password, Role role, List<Cart> listCart, Vip vip) {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.username = username;
        this.password = password;
        //this.password = encodePassword(password); // Se encripta en el setter
        this.deleted = false;
        this.deletedAt = null;
        this.role = role;
        this.listCart = (listCart != null) ? listCart : new ArrayList<>();
        this.vip = vip;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
/*
    // Setter para password con encriptación
    public void setPassword(String password) {
        this.password = encodePassword(password);
    }

    // Método para encriptar la contraseña
    private String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password); // Devuelve la contraseña encriptada
    }*/

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Cart> getListCart() {
        return listCart;
    }

    public void setListCart(List<Cart> listCart) {
        this.listCart = listCart;
    }

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

}
