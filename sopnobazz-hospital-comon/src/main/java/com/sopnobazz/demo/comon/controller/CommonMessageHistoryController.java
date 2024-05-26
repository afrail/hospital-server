package com.sopnobazz.demo.comon.controller;

import com.sopnobazz.demo.comon.constants.ApiConstants;
import com.sopnobazz.demo.comon.constants.MessageConstants;
import com.sopnobazz.demo.comon.entity.CommonMessageHistory;
import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.comon.service.CommonMessageHistoryService;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.UserTokenRequestUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @version 2.0.0
 * @Since 3/24/2022
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstants.COMMON_END_POINT + "common-message-history")
public class CommonMessageHistoryController implements MessageConstants {


    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(CommonMessageHistoryController.class);

    private final CommonMessageHistoryService service;

    /* utils */
    private final CommonUtils commonUtils;
    private final UserTokenRequestUtils authTokenUtils;

    @PostMapping
    public CommonResponse save(@Valid @RequestBody CommonMessageHistory body, HttpServletRequest request) {
        try {
            body.setEntryUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.save(body), SAVE_MESSAGE, SAVE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @PutMapping
    public CommonResponse update(@Valid @RequestBody CommonMessageHistory body, HttpServletRequest request) {
        try {
            body.setUpdateUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.update(body), UPDATE_MESSAGE, UPDATE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping("username/{userName}")
    public CommonResponse getByUserName(@PathVariable("userName") String userName) {
        try {
            return commonUtils.generateSuccessResponse(service.getAllByNotRemoveList(userName));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping(value = ApiConstants.GET_OBJECT_BY_ID, produces = ApiConstants.EXTERNAL_MEDIA_TYPE)
    public CommonResponse getByObjectId(@PathVariable("id") Integer id) {
        try {
            return commonUtils.generateSuccessResponse(service.getById(id));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

}
