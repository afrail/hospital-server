package com.sopnobazz.hospital.doctor_patient.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.sopnobazz.demo.comon.constants.MessageConstants;
import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.UserTokenRequestUtils;
import com.sopnobazz.hospital.doctor_patient.request.PatientPrescriptionRequest;
import com.sopnobazz.hospital.doctor_patient.service.PatientPrescriptionMasterService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ibcsprimax.bof.employee_health.request.PatientPrescriptionSearchParam;


import lombok.AllArgsConstructor;

import static com.sopnobazz.demo.comon.constants.ApiConstants.*;

@RestController
@AllArgsConstructor

@RequestMapping(EHM_END_POINT+"patient-prescription-master")
public class PatientPrescriptionMasterController implements MessageConstants {
	
	private final PatientPrescriptionMasterService service;

    /* utils */
    private final CommonUtils commonUtils;
    private final UserTokenRequestUtils authTokenUtils;

    @PostMapping
    public CommonResponse save(@Valid @RequestBody PatientPrescriptionRequest body, HttpServletRequest request){
        try {
            return commonUtils.generateSuccessResponse(service.save(body, authTokenUtils.getUserIdFromRequest(request)), SAVE_MESSAGE, SAVE_MESSAGE_BN);
        } catch (Exception e) {
        	e.printStackTrace();
            return commonUtils.generateErrorResponse(e);
        }
    }

    @PutMapping
    public CommonResponse update(@Valid @RequestBody PatientPrescriptionRequest body, HttpServletRequest request){
        try {
            return commonUtils.generateSuccessResponse(service.update(body, authTokenUtils.getUserIdFromRequest(request)), UPDATE_MESSAGE, UPDATE_MESSAGE_BN);
        } catch (Exception e) {
        	e.printStackTrace();
            return commonUtils.generateErrorResponse(e);
        }
    }

    @DeleteMapping
    public CommonResponse delete(@Valid @RequestBody PatientPrescriptionRequest body){
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
    
   /* @PutMapping( value = "search")
    public CommonResponse search(@RequestBody PatientPrescriptionSearchParam searchParam){
        try {
            return commonUtils.generateSuccessResponse(service.search(searchParam));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }*/
    
    @GetMapping(value = "search-patient-id/{patient}", produces = EXTERNAL_MEDIA_TYPE)
    public CommonResponse getByPatientId(@PathVariable("patient") Integer patient){
        try {
            return commonUtils.generateSuccessResponse( service.getPatient(patient));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }
    
    @GetMapping( value = "get-template-list/{hosType}")
    public CommonResponse getTemplateList(@PathVariable("hosType") int hosType, HttpServletRequest request){
        try {
            return commonUtils.generateSuccessResponse(service.getTemplateList(hosType,authTokenUtils.getUserIdFromRequest(request)));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }
    
    @GetMapping( value = "get-continue-medicine/{patId}")
    public CommonResponse getContinueMedicine(@PathVariable("patId") int patId){
        try {
            return commonUtils.generateSuccessResponse(service.getContinueMedicine(patId));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }
    
    @GetMapping(value = "get-chronic-patient-prescription/{patient}", produces = EXTERNAL_MEDIA_TYPE)
    public CommonResponse getChronicPatientLastPrescription(@PathVariable("patient") Integer patient){
        try {
            return commonUtils.generateSuccessResponse( service.getChronicPatientLastPrescription(patient));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }
    
    @GetMapping(value = "get-by-prescription-no/{prescriptionNo}", produces = EXTERNAL_MEDIA_TYPE)
    public CommonResponse getByPrescriptionNo(@PathVariable("prescriptionNo") String prescriptionNo){
        try {
            return commonUtils.generateSuccessResponse( service.getByPrescriptionNo(prescriptionNo));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }
    @GetMapping( value = PAGEABLE_PATH + "/{hosType}", produces = EXTERNAL_MEDIA_TYPE)
    public CommonResponse getPagableList(@PathVariable(PAGEABLE_PAGE) int page, @PathVariable(PAGEABLE_SIZE)int size, @PathVariable("hosType")int hosType){
        try {
            return commonUtils.generateSuccessResponse(service.getPageableListWithHosType(page, size, hosType));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }


}
