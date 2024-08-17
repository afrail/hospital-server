package com.sopnobazz.demo.doctor_patient.controller;

import com.sopnobazz.demo.comon.constants.MessageConstants;
import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.UserTokenRequestUtils;
import com.sopnobazz.demo.doctor_patient.entity.PatientIllnessHistory;
import com.sopnobazz.demo.doctor_patient.service.PatientIllnessHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.sopnobazz.demo.comon.constants.ApiConstants.*;


@RestController
@AllArgsConstructor

@RequestMapping(EHM_END_POINT+"patient-illness-history")
public class PatientIllnessHistoryController implements MessageConstants {
	
	private final PatientIllnessHistoryService service;

    /* utils */
    private final CommonUtils commonUtils;
    private final UserTokenRequestUtils authTokenUtils;

    @PostMapping
    public CommonResponse save(@Valid @RequestBody PatientIllnessHistory body, HttpServletRequest request){
        try {
            body.setEntryUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.save(body), SAVE_MESSAGE, SAVE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @PutMapping
    public CommonResponse update(@Valid @RequestBody PatientIllnessHistory body, HttpServletRequest request){
        try {
            body.setUpdateUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.update(body), UPDATE_MESSAGE, UPDATE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @DeleteMapping
    public CommonResponse delete(@Valid @RequestBody PatientIllnessHistory body){
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
    
    @GetMapping( value = "get-by-pat-id/{patId}")
    public CommonResponse getByPatId(@PathVariable("patId") int patId){
        try {
            return commonUtils.generateSuccessResponse(service.getByPatId(patId));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

}
