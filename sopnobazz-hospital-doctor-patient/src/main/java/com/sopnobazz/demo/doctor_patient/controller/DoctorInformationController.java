package com.sopnobazz.demo.doctor_patient.controller;

import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.UserTokenRequestUtils;
import com.sopnobazz.demo.doctor_patient.entity.DoctorInformation;
import com.sopnobazz.demo.doctor_patient.service.DoctorInformationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import static com.sopnobazz.demo.comon.constants.ApiConstants.*;
import static com.sopnobazz.demo.comon.constants.MessageConstants.*;




@AllArgsConstructor
@RestController
@RequestMapping(EHM_END_POINT+"doctor-information")
public class DoctorInformationController {
	
	@SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(DoctorInformation.class);

    private final DoctorInformationService service;

    /* utils */
    private final CommonUtils commonUtils;
    private final UserTokenRequestUtils authTokenUtils;

    @PostMapping
    public CommonResponse save(@Valid @RequestBody DoctorInformation body, HttpServletRequest request){
        try {
            body.setEntryUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.save(body), SAVE_MESSAGE, SAVE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @PutMapping
    public CommonResponse update(@Valid @RequestBody DoctorInformation body, HttpServletRequest request){
        try {
            body.setUpdateUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.update(body), UPDATE_MESSAGE, UPDATE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @DeleteMapping
    public CommonResponse delete(@Valid @RequestBody DoctorInformation body){
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

    @GetMapping( value = "get-by-app-user/{appUser}", produces = EXTERNAL_MEDIA_TYPE)
    public CommonResponse getByAppUserId(@PathVariable("appUser") int appUser){
        try {
            return commonUtils.generateSuccessResponse(service.getByAppUserId(appUser));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

}
