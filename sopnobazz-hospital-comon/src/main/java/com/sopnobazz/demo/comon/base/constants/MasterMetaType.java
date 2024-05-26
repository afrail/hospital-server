package com.sopnobazz.demo.comon.base.constants;

import lombok.Getter;
import lombok.ToString;

/**
 * @version 1.0.0
 * @Project hospital-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 23, 2022
 */


@Getter
@ToString
public enum MasterMetaType {
    ADDRESS_TYPE,
    ORDER_STATUS_TYPE,
    PAYMENT_GATEWAY_TYPE,
    PAYMENT_STATUS_TYPE,
    QUANTITY_ADJUSTMENT_TYPE,
    POLICY_TEMPLATE,
    SALES_CHANNEL,
    STORE_MENU_TYPE,
    UNIT,
    NOTIFICATION_ORDER,
    NOTIFICATION_CUSTOMER
}
