package com.sopnobazz.demo.comon.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version 1.0.0
 * @Since July 06, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SYA_USER_ROLE_PERMISSION")
public class UserRolePermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "USER_ROLE_ID")
    private UserRole userRole;

    @OneToOne
    @JoinColumn(name = "MENU_ITEM_URL_ID")
    private MenuItemUrl menuItemUrl;

    @OneToOne
    @JoinColumn(name = "MENU_ITEM_REPORT_ID")
    private MenuItem menuItemReport;

    @Column(name = "IS_INSERT")
    private Boolean insert;

    @Column(name = "IS_EDIT")
    private Boolean edit;

    @Column(name = "IS_DELETE")
    private Boolean delete;

    @Column(name = "IS_VIEW")
    private Boolean view;


}
