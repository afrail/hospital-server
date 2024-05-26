package com.sopnobazz.demo.comon.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * @version 1.0.0
 * @Project hospital-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 23, 2022
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseDto implements IdHolderRequestBodyDto {

    @JsonIgnore
    private UUID createdBy;

    @JsonIgnore
    private Date createdOn;

    @JsonIgnore
    private UUID updatedBy;

    @JsonIgnore
    private Date updatedOn;

    @JsonIgnore
    private Boolean isDeleted;
}
