package com.sopnobazz.demo.doctor_patient.controller;


import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.UserTokenRequestUtils;
import com.sopnobazz.demo.doctor_patient.entity.TokenRegister;
import com.sopnobazz.demo.doctor_patient.request.TokenRegisterRequest;
import com.sopnobazz.demo.doctor_patient.request.TokenRegisterSearchParam;
import com.sopnobazz.demo.doctor_patient.service.TokenRegisterService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import static com.sopnobazz.demo.comon.constants.ApiConstants.*;
import static com.sopnobazz.demo.comon.constants.MessageConstants.*;
import static com.sopnobazz.demo.comon.constants.MessageConstants.UPDATE_MESSAGE_BN;

@RestController
@AllArgsConstructor
@RequestMapping(EHM_END_POINT+"token-register")
public class TokenRegisterController {
    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(TokenRegister.class);

    private final TokenRegisterService service;

    /* utils */
    private final CommonUtils commonUtils;
    private final UserTokenRequestUtils authTokenUtils;

    @PostMapping
    public CommonResponse save(@RequestBody TokenRegisterRequest body, HttpServletRequest request){
        try {
            body.setEntryUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.save(body), SAVE_MESSAGE, SAVE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    
    @PutMapping
    public CommonResponse update(@Valid @RequestBody TokenRegisterRequest body, HttpServletRequest request){
        try {
            body.setUpdateUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.update(body), UPDATE_MESSAGE, UPDATE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @DeleteMapping
    public CommonResponse delete(@Valid @RequestBody TokenRegister body){
        try {
            return commonUtils.generateSuccessResponse(service.delete(body), DELETE_MESSAGE, DELETE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping
    public CommonResponse getAll(){
        try {
            return commonUtils.generateSuccessResponse(service.getAll());
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping( value = ACTIVE_PATH, produces = EXTERNAL_MEDIA_TYPE)
    public CommonResponse getAllActive(){
        try {
            return commonUtils.generateSuccessResponse(service.getAllActive());
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping( value = PAGEABLE_PATH, produces = EXTERNAL_MEDIA_TYPE)
    public CommonResponse getPagableList(@PathVariable(PAGEABLE_PAGE) int page, @PathVariable(PAGEABLE_SIZE)int size){
        try {
            return commonUtils.generateSuccessResponse(service.getPageableList(page,size));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @PutMapping(value = "get-patient")
    public CommonResponse getPatientPhone(@RequestBody String phone){
        try {
            return commonUtils.generateSuccessResponse(service.getPatient(phone));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @PutMapping( value = "search")
    public CommonResponse searchParamAttendanceDetails(@RequestBody TokenRegisterSearchParam obj){
        try {
            return commonUtils.generateSuccessResponse(service.getSearch(obj));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping( value = "get-by-app-user-id/{appUserId}")
    public CommonResponse getByAppUserId(@PathVariable("appUserId") int appUserId){
        try {
            return commonUtils.generateSuccessResponse(service.getByAppUserId(appUserId));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }


}
