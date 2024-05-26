package com.sopnobazz.demo.comon.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version 1.0.0
 * @Since July 05, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SYA_MENU_ITEM_URL")
public class MenuItemUrl extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "MENU_ITEM_ID", nullable = false)
    private MenuItem menuItem;

    @Column(name = "BASE_URL", nullable = false)
    private String baseURL;

    @Column(name = "IS_INSERT")
    private Boolean insert;

    @Column(name = "IS_EDIT")
    private Boolean edit;

    @Column(name = "IS_DELETE")
    private Boolean delete;

    @Column(name = "IS_VIEW")
    private Boolean view;


}
