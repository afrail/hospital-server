/**
 *
 */
package com.sopnobazz.demo.comon.service;

import java.util.Date;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @Since Nov 1, 2021
 * @version 1.0.0
 */
@AllArgsConstructor
@Service
public class CommonSmsBody {
    private final CommonUtils commonUtils;

    public String dutyPointRosterDeclearationDetailSms(Date DutyDate, String fromTime, String toTime, String dutyPoint) {
        String smsText = "You Are Assigned In Your Duty " +
                "\nDate : " + commonUtils.formateSqlDate(DutyDate) +
                "\nFrom : " + commonUtils.formateSqlTime(fromTime) +
                "\nTo : " + commonUtils.formateSqlTime(toTime) +
                "\nDuty Point : " + dutyPoint;
        return smsText;
    }

    public String birthdaySms() {
        String smsText = "Happy Birthday !! " +
                "\nDear Employee BOF ERP wish you be Happy." +
                "\nBest Wishes for your next year. ";
        return smsText;
    }

    public String marrigeDaySms() {
        String smsText = "Happy Marrige Day !! " +
                "\nDear Employee BOF ERP wish you be Happy." +
                "\nBest Wishes for your next year. ";
        return smsText;
    }

}
