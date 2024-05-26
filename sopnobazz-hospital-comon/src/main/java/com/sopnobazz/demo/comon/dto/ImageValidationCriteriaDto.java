package com.sopnobazz.demo.comon.dto;


import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * @version 1.0.0
 * @Since June 17, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */
@Setter
@Getter
@Component
public class ImageValidationCriteriaDto implements Serializable {
    private static final long serialVersionUID = 1L;

    Integer id;
    Integer size;
    Integer height;
    Integer width;
    String[] extensions;

    public ImageValidationCriteriaDto() {
    }

    public ImageValidationCriteriaDto(Integer id, Integer size, Integer height, Integer width, String[] extensions) {
        super();
        this.id = id;
        this.size = size;
        this.height = height;
        this.width = width;
        this.extensions = extensions;
    }
}
