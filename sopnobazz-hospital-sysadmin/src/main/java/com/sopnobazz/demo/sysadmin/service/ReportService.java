package com.sopnobazz.demo.sysadmin.service;

import java.util.List;

import javax.annotation.PostConstruct;

import com.sopnobazz.demo.sysadmin.dto.ReportDropdownListDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Service
@AllArgsConstructor
public class ReportService {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(ReportService.class);

    //private final ReportMasterRepository repo;
    //private final CommonUtils commonUtils;
    //private final ReportUtils reportUtils;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {

    }


//	public <T> List<T> getDropdownListData(String sql){
//		jdbcTemplate.q
//		return null;
//	}

    public List<ReportDropdownListDataDto> getDropdownListData(String sql) {

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new ReportDropdownListDataDto(
                                rs.getInt("report_dropdown_id"),
                                rs.getString("report_dropdown_name"),
                                rs.getString("report_dropdown_bangla_name")
                        )
        );
    }

}
