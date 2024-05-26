package com.sopnobazz.demo.sysadmin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.sopnobazz.demo.comon.constants.ApiConstants;
import com.sopnobazz.demo.comon.constants.MessageConstants;
import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.UserTokenRequestUtils;
import com.sopnobazz.demo.sysadmin.entity.SubReportMaster;
import com.sopnobazz.demo.sysadmin.service.SubReportMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstants.SYSTEM_ADMIN_END_POINT + "sub-report-master")
public class SubReportMasterController implements MessageConstants {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(SubReportMasterController.class);

    @SuppressWarnings("unused")
    private final ResourceLoader resourceLoader;
    @SuppressWarnings("unused")
    @Autowired
    private Environment env;

    private final SubReportMasterService service;

    /* utils */
    private final CommonUtils commonUtils;
    private final UserTokenRequestUtils authTokenUtils;


    @PostMapping()
    public CommonResponse save(@Valid @RequestBody SubReportMaster obj, HttpServletRequest request) {

        try {
            obj.setEntryUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.save(obj), SAVE_MESSAGE, SAVE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @PutMapping
    public CommonResponse update(@Valid @RequestBody SubReportMaster obj, HttpServletRequest request) {

        try {
            obj.setUpdateUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.update(obj), UPDATE_MESSAGE, UPDATE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @DeleteMapping
    public CommonResponse delete(@Valid @RequestBody SubReportMaster body) {
        try {
            return commonUtils.generateSuccessResponse(service.delete(body), DELETE_MESSAGE, DELETE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping
    public CommonResponse getAll() {
        try {
            return commonUtils.generateSuccessResponse(service.getAll());
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping(value = ApiConstants.ACTIVE_PATH, produces = ApiConstants.EXTERNAL_MEDIA_TYPE)
    public CommonResponse getAllActive() {
        try {
            return commonUtils.generateSuccessResponse(service.getAllActive());
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping(value = ApiConstants.PAGEABLE_PATH, produces = ApiConstants.EXTERNAL_MEDIA_TYPE)
    public CommonResponse getPagableList(@PathVariable(ApiConstants.PAGEABLE_PAGE) int page, @PathVariable(ApiConstants.PAGEABLE_SIZE) int size) {
        try {
            return commonUtils.generateSuccessResponse(service.getPageableList(page, size));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

}
