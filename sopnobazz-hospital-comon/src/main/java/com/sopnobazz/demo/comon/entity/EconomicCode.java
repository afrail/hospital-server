package com.sopnobazz.demo.comon.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @version 2.0.0
 * @Since November 17, 2021
 * @Author Rokon Uddin 560
 */


@Data
@Entity
@Table(name = "BUDGET_ECONOMIC_CODE")
public class EconomicCode extends BaseEntity {


//	private static final long serialVersionUID = -6551732614353013886L;

    @Column(name = "ECONOMIC_CODE", unique = true)
    private String economicCode;

    @Column(name = "NAME", length = 200)
    private String name;

    @Column(name = "NAME_BANGLA", length = 200)
    private String nameBangla;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private EconomicCode parent;

    @ManyToOne
    @JoinColumn(name = "SECOND_PARENT_ID")
    private EconomicCode secondParent;

    @ManyToOne
    @JoinColumn(name = "THIRD_PARENT_ID")
    private EconomicCode thirdParent;

    @Column(name = "IS_PARENT")
    private boolean parentCode;


    @Column(name = "IS_SUPPLIER")
    private boolean supplierIs;

    @Column(name = "IS_EMPLOYEE")
    private boolean employeeIs;

    @Column(name = "IS_ARMY_AMOUNT")
    private boolean armyAmountIs;

    @Column(name = "ECONOMIC_TYPE") // , columnDefinition="1. Asset, 2.Liability, 3.Income, 4.Expance"
    private Integer economicType;

    @Column(name = "CODE_TYPE", columnDefinition = "boolean default true")
    private boolean codeType = true; // true for economic code and false for accounts


}
