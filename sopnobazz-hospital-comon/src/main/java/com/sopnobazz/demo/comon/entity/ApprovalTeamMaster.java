package com.sopnobazz.demo.comon.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version 1.0.0
 * @Since July 25, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SYA_APPROVAL_TEAM_MASTER")
public class ApprovalTeamMaster extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "BANGLA_NAME", length = 100)
    private String banglaName;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    List<ApprovalTeamDetails> approvalTeamDetailList = new ArrayList<>();

    /* not use */
    @Column(name = "SERIAL_NO")
    private Integer serialNo;

    @ManyToOne
    @JoinColumn(name = "MODULE_ID")
    private MenuItem module;


}
