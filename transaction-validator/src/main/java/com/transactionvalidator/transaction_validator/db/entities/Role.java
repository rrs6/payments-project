package com.transactionvalidator.transaction_validator.db.entities;

import com.transactionvalidator.transaction_validator.utils.enums.RoleEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="roles")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public Role(String role) {
        this.name = RoleEnum.valueOf(role);
    }
}
