package com.sopnobazz.demo.comon.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @version 1.0.0
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @Since July 25, 2021
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SYA_APPROVAL_TEAM_DETAILS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"APP_USER_ID", "APPROVAL_TEAM_MASTER_ID"})
})
public class ApprovalTeamDetails extends BaseEntity {


    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "APP_USER_ID", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "APPROVAL_TEAM_MASTER_ID")
    private ApprovalTeamMaster approvalTeamMaster;


}
