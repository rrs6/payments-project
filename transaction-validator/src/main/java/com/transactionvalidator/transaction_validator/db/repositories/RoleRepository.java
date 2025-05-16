package com.transactionvalidator.transaction_validator.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transactionvalidator.transaction_validator.db.entities.Role;
import java.util.List;
import java.util.Optional;

import com.transactionvalidator.transaction_validator.utils.enums.RoleEnum;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleEnum name);

}
