package com.sopnobazz.demo.comon.dto;


import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0.0
 * @Since June 17, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */
@Setter
@Getter
@Component
public class ReportValidationCriteriaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    Integer id;
    Integer size;
    String[] extensions;

    public ReportValidationCriteriaDto() {
    }

    public ReportValidationCriteriaDto(Integer id, Integer size, String[] extensions) {
        super();
        this.id = id;
        this.size = size;
        this.extensions = extensions;
    }
}
