package com.sopnobazz.demo.sysadmin.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.sopnobazz.demo.comon.constants.ApiConstants;
import com.sopnobazz.demo.comon.constants.MessageConstants;
import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.comon.service.MailService;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.UserTokenRequestUtils;
import com.sopnobazz.demo.sysadmin.dto.AppUserEmployeeDto;
import com.sopnobazz.demo.sysadmin.entity.AppUserEmployee;
import com.sopnobazz.demo.sysadmin.service.AppUserEmployeeService;
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
@RequestMapping(ApiConstants.SYSTEM_ADMIN_END_POINT + "app-user-employee")
public class AppUserEmployeeController implements MessageConstants {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(AppUserEmployeeController.class);

    private final AppUserEmployeeService service;
    private final MailService mailService;

    /* utils */
    private final CommonUtils commonUtils;
    private final UserTokenRequestUtils authTokenUtils;

    /* properties */
    private final String SUBJECT = "Bangladesh Ordnance Factory";
    private final String BODY = " is your current password. please login with this password. Change this password in your first login";


    @PostMapping
    public CommonResponse save(@Valid @RequestBody AppUserEmployeeDto body, HttpServletRequest request) {
        try {
            body.setEntryUser(authTokenUtils.getUserIdFromRequest(request));
            if (body.isGeneratePassword()) {
                String randomPassword = commonUtils.getRendom4Digit();
                body.setPassword(randomPassword);
                if (mailService.sendEmail(body.getEmail(), SUBJECT, randomPassword + BODY)) {
                    System.out.println("mail successfully send");
                } else {
                    System.out.println("mail send failed");
                }
            }
            return commonUtils.generateSuccessResponse(service.save(body), SAVE_MESSAGE, SAVE_MESSAGE_BN);
        } catch (Exception e) {
            e.printStackTrace();
            return commonUtils.generateErrorResponse(e);
        }
    }

    @PutMapping
    public CommonResponse update(@Valid @RequestBody AppUserEmployeeDto body, HttpServletRequest request) {
        try {
            body.setUpdateUser(authTokenUtils.getUserIdFromRequest(request));
            if (body.isGeneratePassword()) {
                String randomPassword = commonUtils.getRendom4Digit();
                body.setPassword(randomPassword);
                if (mailService.sendEmail(body.getEmail(), SUBJECT, randomPassword + BODY)) {
                    System.out.println("mail successfully send");
                } else {
                    System.out.println("mail send failed");
                }
            }
            return commonUtils.generateSuccessResponse(service.update(body), UPDATE_MESSAGE, UPDATE_MESSAGE_BN);
        } catch (Exception e) {
            e.printStackTrace();
            return commonUtils.generateErrorResponse(e);
        }
    }

    @DeleteMapping
    public CommonResponse delete(@Valid @RequestBody AppUserEmployee body) {
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

    @GetMapping(value = "get-by-app-user-id/{appUserId}")
    public CommonResponse getByAppUserId(@PathVariable("appUserId") Integer appUserId) {
        try {
            return commonUtils.generateSuccessResponse(service.getByAppUserId(appUserId));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping(value = "get-by-transaction-table-and-id/{transactionId}/{transactionTable}")
    public CommonResponse getByTransactionIdAndTransactionTable(@PathVariable("transactionId") Integer transactionId, @PathVariable("transactionTable") String transactionTable) {
        try {
            return commonUtils.generateSuccessResponse(service.getByTransactionIdAndTransactionTable(transactionId, transactionTable));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

}
