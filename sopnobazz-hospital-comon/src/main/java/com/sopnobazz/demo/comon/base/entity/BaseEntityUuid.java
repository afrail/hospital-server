/**
 *
 */
package com.sopnobazz.demo.comon.base.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @Project hospital-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 23, 2022
 * @version 1.0.0
 */

@Component
@MappedSuperclass
@Data
public class BaseEntityUuid {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false, updatable = false)
    private UUID entryUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @CreatedDate
    @JsonIgnore
    private Date entryDate;

    private UUID updateUser;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @JsonIgnore
    private Date updateDate;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private Boolean isDeleted;

}
