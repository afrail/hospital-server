package com.sopnobazz.demo.comon.base.constants;

import lombok.Getter;
import lombok.ToString;

/**
 * @version 1.0.0
 * @Project demo-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 23, 2022
 */


@Getter
@ToString
public enum CoreStatus {
    DRAFT("Draft"),
    PUBLISHED("Published"),
    ARCHIVED("Archived"),
    PENDING("Pending"),
    PAID("Paid"),
    UNPAID("Unpaid"),
    FAILED("Failed"),
    CANCEL("Cancel"),
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    UNLIMITED("Unlimited");

    public final String value;

    CoreStatus(String value) {
        this.value = value;
    }
}
