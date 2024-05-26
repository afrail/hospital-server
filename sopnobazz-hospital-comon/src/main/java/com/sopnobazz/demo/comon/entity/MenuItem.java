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
 * @Since July 04, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SYA_MENU_ITEM")
public class MenuItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "BANGLA_NAME", length = 100, nullable = false)
    private String banglaName;

    @Column(name = "MENU_TYPE", nullable = false)
    private Integer menuType;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private MenuItem parent;

    @Column(name = "UNIQUE_MODEL_MENU_FILE", length = 100)
    private String uniqueModelMenuFile;

    @Column(name = "SERIAL_NO")
    private Integer serialNo;

    @Column(name = "URL")
    private String url;


}
