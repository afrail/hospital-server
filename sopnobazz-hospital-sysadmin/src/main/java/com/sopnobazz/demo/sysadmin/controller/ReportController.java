package com.sopnobazz.demo.sysadmin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopnobazz.demo.comon.constants.ApiConstants;
import com.sopnobazz.demo.comon.entity.AppUser;
import com.sopnobazz.demo.comon.entity.AppUserLog;
import com.sopnobazz.demo.comon.repository.AppUserLogRepository;
import com.sopnobazz.demo.comon.repository.AppUserRepository;
import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.UserTokenRequestUtils;
import com.sopnobazz.demo.sysadmin.entity.AppUserEmployee;
import com.sopnobazz.demo.sysadmin.report.ParameterCompilation;
import com.sopnobazz.demo.sysadmin.report.ReportCompilation;
import com.sopnobazz.demo.sysadmin.report.ReportExporter;
import com.sopnobazz.demo.sysadmin.report.ReportResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sopnobazz.demo.sysadmin.repository.AppUserEmployeeRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@RestController
@RequestMapping(ApiConstants.SYSTEM_ADMIN_END_POINT + "report-configure")
public class ReportController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    ReportCompilation reportCompilation;

    @Autowired
    ParameterCompilation parameterCompilation;

    @Autowired
    ReportExporter reportExporter;

    @Autowired
    ReportResponse reportResponse;

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private UserTokenRequestUtils authTokenUtils;

    @Autowired
    private AppUserRepository appUserRepo;

    @Autowired
    private AppUserEmployeeRepository appUserEmpRepo;

    @Autowired
    private AppUserLogRepository appUserLogRepo;


    @GetMapping(value = "/params/{reportId}")
    public CommonResponse getAllParamsById(@PathVariable("reportId") Integer reportId) {
        try {
            return commonUtils.generateSuccessResponse(parameterCompilation.getParamsView(reportId));
        } catch (Exception e) {
            e.printStackTrace();
            return commonUtils.generateErrorResponse(e);
        }
    }


    @PostMapping("/generate-report/print")
    public ResponseEntity<byte[]> printReport(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws IOException, JRException, SQLException, ParseException {
        Integer id = Integer.valueOf(params.get("id").toString());
        JasperReport report = reportCompilation.compileReports(id);
        Map<String, Object> parameters = parameterCompilation.getParamsValue(id, params);
        byte[] bytes = reportExporter.uploadPDFReport(id, report, parameters, response);

        var contentDisposition = ContentDisposition.builder("attachment").filename(id + ".pdf").build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);

        /* add report log */
        AppUser appUser = appUserRepo.findById(authTokenUtils.getUserIdFromRequest(request)).get();
        AppUserLog appUserLog = new AppUserLog();
        appUserLog.setAppUser(appUser.getId());
        AppUserEmployee appUserEmp = appUserEmpRepo.findByAppUserIdAndActive(appUser.getId(), true);
        appUserLog.setAppUserEmp(appUserEmp == null ? 0 : appUserEmp.getId());
        appUserLog.setLoginDate(new Date());
        appUserLog.setReportIS(true);
        appUserLogRepo.save(appUserLog);

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf; charset=UTF-8")
                .headers(headers)
                .body(bytes);
    }

    @PostMapping("/generate-report/download")
    public ResponseEntity<byte[]> downloadReport(@RequestBody Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws IOException, JRException, SQLException, ParseException {
        Integer id = Integer.valueOf(params.get("id").toString());
        JasperReport report = reportCompilation.compileReports(id);
        Map<String, Object> parameters = parameterCompilation.getParamsValue(id, params);

        if (params.get("reportFormat").toString() == "rtf") {
            byte[] bytes = reportExporter.uploadRTF(id, report, parameters, response);

            var contentDisposition = ContentDisposition.builder("attachment").filename(id + ".rtf").build();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(contentDisposition);
            return ResponseEntity.ok()
                    .headers(headers)
                    .header("Content-Type", "application/octet-stream; charset=UTF-8")
                    .body(bytes);
        } else {
            byte[] bytes = reportExporter.uploadXlsxReport(id, report, parameters, response);

            var contentDisposition = ContentDisposition.builder("attachment").filename(id + ".xlsx").build();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(contentDisposition);
            return ResponseEntity.ok()
                    .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8")
                    .headers(headers)
                    .body(bytes);
        }

    }

}
