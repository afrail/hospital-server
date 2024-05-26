package com.sopnobazz.demo.comon.base.dto;

import java.util.UUID;


/**
 * @version 1.0.0
 * @Project hospital-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 23, 2022
 */


public interface IdHolderRequestBodyDto extends EmptyRequestBodyDto {

    UUID getId();

    void setId(UUID id);

}
