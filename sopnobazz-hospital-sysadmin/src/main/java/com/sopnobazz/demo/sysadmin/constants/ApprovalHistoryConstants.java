package com.sopnobazz.demo.sysadmin.constants;


/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface ApprovalHistoryConstants {

    String PENDING_ID = "P";
    String SUBMIT_ID = "S";
    String FORWARD_ID = "F";
    String BACK_ID = "B";
    String APPROVED_ID = "A";
    String NOTIFY_ID = "N";

    /* table */
    String TABLE_LEAVE_APPLICATION = "HRM_EMPLOYEE_LEAVE_INFORMATION";
    String TABLE_ACC_EMPLOYEE_ID_REQUEST = "ACC_EMPLOYEE_ID_REQUEST_MASTER";
    String TABLE_ICT_ITEM_INDENT_MASTER = "ICT_ITEM_INDENT_MASTER";
    String TABLE_COMMON_NOTE = "COMMON_NOTE_SHEET";
    String TABLE_GATE_PASS = "CLR_GATE_PASS_APPLICATION_MASTER";
    String TABLE_DRW_FILE_ACCESS_REQUEST = "DRW_FILE_ACCESS_REQUEST_MASTER";
    String TABLE_BUDGET_DEMAND = "BUDGET_DEMAND_MASTER";
    String TABLE_RAW_RATION_INDENT_MASTER = "RAW_RATION_INDENT_MASTER";

    /* module */
    Integer OFFICE_BOF_SECURITY = 910;

}
