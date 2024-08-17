/**
 * @Created_By : IBCS-Primax Software (bd) Ltd.
 * @Date       : 2022.10.15 12:40:41 PM
 * @Author     : Md. Hoshen Mahmud Khan
 * @Email      : saif_hmk@live.com
 * @Phone      : +8801672036757
 **/
package com.sopnobazz.demo.doctor_patient.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EhmUtilService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EhmUtilService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    /**
     * @RnD: using jdbcTemplate
     **/
    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM EHM_MEDICINE_RECEIVE_DETAILS";
        return jdbcTemplate.queryForList(sql);
    }

}
