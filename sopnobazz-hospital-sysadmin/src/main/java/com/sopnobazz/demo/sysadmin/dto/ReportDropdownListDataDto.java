package com.sopnobazz.demo.sysadmin.dto;

import java.io.Serializable;


/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

//@Data
//@EqualsAndHashCode(callSuper = true)
public class ReportDropdownListDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer report_dropdown_id;
    private String report_dropdown_name;
    private String report_dropdown_bangla_name;


    public ReportDropdownListDataDto(Integer report_dropdown_id, String report_dropdown_name, String report_dropdown_bangla_name) {
        super();
        this.report_dropdown_id = report_dropdown_id;
        this.report_dropdown_name = report_dropdown_name;
        this.report_dropdown_bangla_name = report_dropdown_bangla_name;
    }


    public Integer getReport_dropdown_id() {
        return report_dropdown_id;
    }


    public void setReport_dropdown_id(Integer report_dropdown_id) {
        this.report_dropdown_id = report_dropdown_id;
    }


    public String getReport_dropdown_name() {
        return report_dropdown_name;
    }


    public void setReport_dropdown_name(String report_dropdown_name) {
        this.report_dropdown_name = report_dropdown_name;
    }


    public String getReport_dropdown_bangla_name() {
        return report_dropdown_bangla_name;
    }


    public void setReport_dropdown_bangla_name(String report_dropdown_bangla_name) {
        this.report_dropdown_bangla_name = report_dropdown_bangla_name;
    }


}
