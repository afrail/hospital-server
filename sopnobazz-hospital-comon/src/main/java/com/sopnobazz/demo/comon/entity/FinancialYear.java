package com.sopnobazz.demo.comon.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @version 2.0.0
 * @Since November 17, 2021
 * @Author Rokon Uddin - 560
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "BUDGET_FINANCAIL_YEAR")
public class FinancialYear extends BaseEntity {

    private static final long serialVersionUID = 3216408187347048010L;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "FINANCIAL_YEAR", length = 9, unique = true)
    private String financialYear;

    @Column(name = "IS_CLOSE")
    private boolean close;

}
