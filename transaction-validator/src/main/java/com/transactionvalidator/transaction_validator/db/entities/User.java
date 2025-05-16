package com.transactionvalidator.transaction_validator.db.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name="users_roles", joinColumns= @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;

}
