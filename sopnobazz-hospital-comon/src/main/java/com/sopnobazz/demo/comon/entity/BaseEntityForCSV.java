/**
 *
 */
package com.sopnobazz.demo.comon.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 * @Since Sep 22, 2021
 * @version 1.0.0
 */

@Data
@MappedSuperclass
public class BaseEntityForCSV implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "ACTIVE", columnDefinition = "boolean default true")
    private Boolean active = true;

    @Column(name = "ENTRY_USER", updatable = false, nullable = false)
    private Integer entryUser;

    @Column(name = "ENTRY_DATE", updatable = false, nullable = false)
    private Date entryDate;

    @Column(name = "UPDATE_USER", insertable = false)
    private Integer updateUser;

    @Column(name = "UPDATE_DATE", insertable = false)
    private Date updateDate;

    @Column(name = "ENTRY_APP_USER_CODE", length = 50)
    private String entryAppUserCode;

    @Column(name = "UPDATE_APP_USER_CODE", length = 50)
    private String updateAppUserCode;

}
