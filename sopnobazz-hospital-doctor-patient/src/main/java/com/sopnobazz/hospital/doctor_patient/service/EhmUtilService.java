/**
 * @Created_By : IBCS-Primax Software (bd) Ltd.
 * @Date       : 2022.10.15 12:40:41 PM
 * @Author     : Md. Hoshen Mahmud Khan
 * @Email      : saif_hmk@live.com
 * @Phone      : +8801672036757
 **/
package com.sopnobazz.hospital.doctor_patient.service;


import com.sopnobazz.hospital.doctor_patient.entity.MedicineMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EhmUtilService {
    @Autowired
    MedicineReceiveDetailsRepository medicineReceiveDetailsRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EhmUtilService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    UtilityService utilityService;

    /**
     * @RnD: using jdbcTemplate
     **/
    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM EHM_MEDICINE_RECEIVE_DETAILS";
        return jdbcTemplate.queryForList(sql);
    }

    public Map getLotWiseMedicineStockSummary(MedicineMaster medicine) {
        Map<String, Object> meta = new HashMap();


        return meta;
    }
}
