package com.transactionvalidator.transaction_validator.utils.enums;

public enum RoleEnum {
    USER_DEFAULT(0),
    ADMIN(1);

    private final long id;

    RoleEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
