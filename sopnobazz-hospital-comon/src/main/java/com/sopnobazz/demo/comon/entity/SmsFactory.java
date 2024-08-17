package com.sopnobazz.demo.comon.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @version 1.0.0
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @Project ibcs-bof-erp
 */


@Data
@Entity
@Table(name = "COMMON_SMS_FACTORY")
public class SmsFactory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ENTRY_DATE", updatable = false, nullable = false)
    private Date entryDate;

    @ManyToOne
    @JoinColumn(name = "MODULE_ID", nullable = false)
    private MenuItem module;

    @Column(name = "TRANSACTION_ID", nullable = false)
    private Integer transactionId;

    @Column(name = "TRANSACTION_TABLE", length = 100, nullable = false)
    private String transactionTable;

    @Column(name = "MOBILE_NO", length = 30, nullable = false)
    private String mobileNo;

    @Column(name = "SMS_TEXT", nullable = false)
    private String smsText;

    // after response

    @Column(name = "STATUS", length = 1)
    private String status = "P";

    @Column(name = "SEND_DATE")
    private Date sendDate;

    @Column(name = "RESPONSE", length = 100)
    private String reponse;

}
