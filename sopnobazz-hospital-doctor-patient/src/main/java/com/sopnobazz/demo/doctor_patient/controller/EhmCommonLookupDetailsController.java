package com.sopnobazz.demo.doctor_patient.controller;


import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.UserTokenRequestUtils;
import com.sopnobazz.demo.doctor_patient.entity.EhmCommonLookupDetails;
import com.sopnobazz.demo.doctor_patient.service.EhmCommonLookupDetailsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.sopnobazz.demo.comon.constants.ApiConstants.*;
import static com.sopnobazz.demo.comon.constants.MessageConstants.*;

/**
 * @Since Dec 07, 2021
 * @Author Mohaiminul Haq - 582
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@AllArgsConstructor
@RestController
@RequestMapping(EHM_END_POINT+"common-lookup-details")
public class EhmCommonLookupDetailsController {
	
	@SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(EhmCommonLookupDetails.class);

    private final EhmCommonLookupDetailsService service;

    /* utils */
    private final CommonUtils commonUtils;
    private final UserTokenRequestUtils authTokenUtils;

    @PostMapping
    public CommonResponse save(@Valid @RequestBody EhmCommonLookupDetails body, HttpServletRequest request){
        try {
            body.setEntryUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.save(body), SAVE_MESSAGE, SAVE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @PutMapping
    public CommonResponse update(@Valid @RequestBody EhmCommonLookupDetails body, HttpServletRequest request){
        try {
            body.setUpdateUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.update(body), UPDATE_MESSAGE, UPDATE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @DeleteMapping
    public CommonResponse delete(@Valid @RequestBody EhmCommonLookupDetails body){
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

    @GetMapping(value = MASTER_PATH)
	public CommonResponse getByMasterId(@PathVariable(MASTER_ID) int id) {
		try {
    		return commonUtils.generateSuccessResponse(service.getByMasterId(id));
		} catch (Exception e) {
			return commonUtils.generateErrorResponse(e);
		}
	}

    @GetMapping(value = "asc/{" + MASTER_ID + "}")
    public CommonResponse getByMasterAscId(@PathVariable(MASTER_ID) int id) {
        try {
            return commonUtils.generateSuccessResponse(service.getByMasterAscId(id));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

}
