package com.sopnobazz.demo.comon.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * @version 2.0.0
 * @Since 3/23/2022
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "COMMON_MESSAGE_HISTORY")
public class CommonMessageHistory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "MODULE_ID", nullable = false)
    private MenuItem module;

    @Column(name = "TRANSACTION_ID", nullable = false)
    private Integer transactionId;

    @Column(name = "TRANSACTION_TABLE", nullable = false)
    private String transactionTable;

    @Column(name = "TRANSACTION_TYPE", nullable = false)
    private String transactionType;

    @Column(name = "MESSAGE", length = 4000)
    private String message;

    @Column(name = "ACTION", columnDefinition = "boolean default false")
    private Boolean action = false;

    @Column(name = "READ", columnDefinition = "boolean default false")
    private Boolean read = false;

    @Column(name = "READ_DATE")
    private Date readDate;

    @Column(name = "CLOSE", columnDefinition = "boolean default false")
    private Boolean close = false;

    @Column(name = "LINK", length = 200)
    private String link;

    @Column(name = "APP_USER_NAME", length = 200)
    private String appUserName;

}
