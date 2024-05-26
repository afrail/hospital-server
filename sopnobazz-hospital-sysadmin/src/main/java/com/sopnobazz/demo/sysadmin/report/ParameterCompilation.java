package com.sopnobazz.demo.sysadmin.report;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sopnobazz.demo.sysadmin.dto.ReportDropdownListDataDto;
import com.sopnobazz.demo.sysadmin.entity.ReportWithParameter;
import com.sopnobazz.demo.sysadmin.entity.SubReportMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.sopnobazz.demo.sysadmin.service.ReportService;
import com.sopnobazz.demo.sysadmin.service.ReportWithParameterService;
import com.sopnobazz.demo.sysadmin.service.SubReportMasterService;

import net.sf.jasperreports.engine.JRException;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Component
public class ParameterCompilation {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(ParameterCompilation.class);

    @Autowired
    ReportSources reportSources;

    @Autowired
    ReportService reportService;

    @Autowired
    SubReportMasterService subReportMasterService;

    @Autowired
    ReportWithParameterService reportWithParameterService;


    public Map<String, Object> getParamsValue(Integer id, Map<String, Object> params) throws FileNotFoundException, JRException, ParseException {

        Map<String, Object> parameters = new HashMap<>();

        List<ReportWithParameter> list = reportWithParameterService.findByReportMasterId(id);
        if (!CollectionUtils.isEmpty(list)) {
            for (ReportWithParameter data : list) {

                if (data.getParameterMaster().getDataType().equals("Integer")) {
                    if (!ObjectUtils.isEmpty(params.get(data.getParameterMaster().getParameterName()))) {
                        parameters.put(data.getParameterMaster().getParameterName(), Integer.valueOf(params.get(data.getParameterMaster().getParameterName()).toString()));
                    } else {
                        parameters.put(data.getParameterMaster().getParameterName(), null);
                    }
                } else if (data.getParameterMaster().getDataType().equals("Date")) {
                    if (!ObjectUtils.isEmpty(params.get(data.getParameterMaster().getParameterName()))) {
                        String requestDateString = params.get(data.getParameterMaster().getParameterName()).toString();
                        Date requestDate = new SimpleDateFormat("yyyy-MM-dd").parse(requestDateString);

                        parameters.put(data.getParameterMaster().getParameterName(), requestDate);
                    } else {
                        parameters.put(data.getParameterMaster().getParameterName(), null);
                    }
                } else if (data.getParameterMaster().getDataType().equals("List")) {
                    if (!params.get(data.getParameterMaster().getParameterName()).equals("0")) {
                        parameters.put(data.getParameterMaster().getParameterName(), Integer.valueOf(params.get(data.getParameterMaster().getParameterName()).toString()));
                    } else {
                        parameters.put(data.getParameterMaster().getParameterName(), null);
                    }
                } else {
                    if (!ObjectUtils.isEmpty(params.get(data.getParameterMaster().getParameterName()))) {
                        parameters.put(data.getParameterMaster().getParameterName(), params.get(data.getParameterMaster().getParameterName()));
                    } else {
                        parameters.put(data.getParameterMaster().getParameterName(), null);
                    }
                }

            }
        }

        //System.out.println(params.get("P_MODULE_ID").toString());
        if (params.get("P_MODULE_ID").toString().equals("5")) {
            parameters.put("P_HRM_TYPE", Integer.valueOf(1));
        }
        if (params.get("P_MODULE_ID").toString().equals("8")) {
            parameters.put("P_HRM_TYPE", Integer.valueOf(2));
        }
        if (params.get("P_MODULE_ID").toString().equals("157")) {
            parameters.put("P_HRM_TYPE", Integer.valueOf(3));
        }
        parameters.put("P_MODULE_ID", Integer.valueOf(params.get("P_MODULE_ID").toString()));
        parameters.put("LOGO", reportSources.getImage(reportSources.IBCS_LOGO));
        List<SubReportMaster> subreportList = subReportMasterService.getByReportMasterIdAndActive(id, true);
        if (!CollectionUtils.isEmpty(subreportList)) {
            for (SubReportMaster subreport : subreportList) {
                parameters.put(subreport.getReportUpload().getFileNameParams(), reportSources.getCompiledReport(subreport.getReportUpload().getFileNameJasper()));
            }
        }

        return parameters;
    }


    public List<ReportWithParameter> getParamsView(Integer reportId) throws FileNotFoundException, JRException, ParseException {

        List<ReportWithParameter> res = new ArrayList<>();

        List<ReportWithParameter> list = reportWithParameterService.findByReportMasterId(reportId);

        if (!CollectionUtils.isEmpty(list)) {
            for (ReportWithParameter data : list) {
                if (data.getParameterMaster().getDataType().equals("List")) {
                    String dropdownValue = "";
                    String sql = data.getParameterMaster().getSql();
                    List<ReportDropdownListDataDto> listdata = reportService.getDropdownListData(sql);
                    if (!CollectionUtils.isEmpty(listdata)) {

                        for (ReportDropdownListDataDto a : listdata) {
                            Integer id = a.getReport_dropdown_id();
                            String name = a.getReport_dropdown_name();
                            dropdownValue += "<option value=" + id + ">" + name + "</option>";
                        }
                        data.setDropdownListData(dropdownValue);
                    }
                }

                res.add(data);
            }
        }

        return res;
    }

}
