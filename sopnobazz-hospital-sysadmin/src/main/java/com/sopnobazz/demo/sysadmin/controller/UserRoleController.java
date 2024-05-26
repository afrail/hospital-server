package com.sopnobazz.demo.sysadmin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.sopnobazz.demo.comon.constants.ApiConstants;
import com.sopnobazz.demo.comon.constants.MessageConstants;
import com.sopnobazz.demo.comon.entity.UserRole;
import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.UserTokenRequestUtils;
import com.sopnobazz.demo.sysadmin.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(ApiConstants.SYSTEM_ADMIN_END_POINT + "user-role")
public class UserRoleController implements MessageConstants {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(UserRoleController.class);

    private final UserRoleService service;

    /* utils */
    private final CommonUtils commonUtils;
    private final UserTokenRequestUtils authTokenUtils;

    @PostMapping
    public CommonResponse save(@Valid @RequestBody UserRole body, HttpServletRequest request) {
        try {
            body.setEntryUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.save(body), SAVE_MESSAGE, SAVE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @PutMapping
    public CommonResponse update(@Valid @RequestBody UserRole body, HttpServletRequest request) {
        try {
            body.setUpdateUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.update(body), UPDATE_MESSAGE, UPDATE_MESSAGE_BN);
        } catch (Exception e) {
            e.printStackTrace();
            return commonUtils.generateErrorResponse(e);
        }
    }

    @DeleteMapping
    public CommonResponse delete(@Valid @RequestBody UserRole body) {
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
