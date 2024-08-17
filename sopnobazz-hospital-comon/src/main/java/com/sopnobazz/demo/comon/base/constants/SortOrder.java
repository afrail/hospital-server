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
public enum SortOrder {
    ASC("asc"),
    DESC("desc");

    private final String value;

    SortOrder(String value) {
        this.value = value;
    }
}
