package com.sopnobazz.hospital.doctor_patient.controller;
import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.UserTokenRequestUtils;
import com.sopnobazz.hospital.doctor_patient.entity.MedicineMaster;
import com.sopnobazz.hospital.doctor_patient.service.MedicineMasterService;
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
@RequestMapping(EHM_END_POINT+"medicine-master")
public class MedicineMasterController {
	
	@SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(MedicineMaster.class);

    private final MedicineMasterService service;

    /* utils */
    private final CommonUtils commonUtils;
    private final UserTokenRequestUtils authTokenUtils;


    @PostMapping
    public CommonResponse save(@Valid @RequestBody MedicineMaster body, HttpServletRequest request){
        try {
            body.setEntryUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.save(body), SAVE_MESSAGE, SAVE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }


    @PutMapping
    public CommonResponse update(@Valid @RequestBody MedicineMaster body, HttpServletRequest request){
        try {
            body.setUpdateUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.update(body), UPDATE_MESSAGE, UPDATE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @DeleteMapping
    public CommonResponse delete(@Valid @RequestBody MedicineMaster body){
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
    
    @GetMapping( value = "/pageable/{hosType}/{page}/{size}", produces = EXTERNAL_MEDIA_TYPE)
    public CommonResponse getPageableListByModuleId(@PathVariable("hosType") int hosType, @PathVariable("page") int page, @PathVariable("size")int size){
//    	doExtraWork();
    	try {
            return commonUtils.generateSuccessResponse(service.getPageableListByHosType(hosType, page,size));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }
    
    @GetMapping(value = SEARCH_VALUE_PATH)
    public CommonResponse getBySearchValue(@PathVariable(SEARCH_VALUE) String searchValue){
        try {
            return commonUtils.generateSuccessResponse( service.getBySearchValue(searchValue));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }
    
    
    @GetMapping(value = GET_OBJECT_BY_ID)
    public CommonResponse getById(@PathVariable(PATH_ID) Integer id){
        try {
            return commonUtils.generateSuccessResponse( service.getById(id));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }
    
    @GetMapping(value = "get-by-hostype/{hosType}")
    public CommonResponse getByHosType(@PathVariable("hosType") Integer hosType){
        try {
            return commonUtils.generateSuccessResponse( service.getByHosType(hosType));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }
    
    @GetMapping(value = "get-available-item-by-hostype/{hosType}")
    public CommonResponse getAvaileItemByHosType(@PathVariable("hosType") Integer hosType){
        try {
            return commonUtils.generateSuccessResponse( service.getAvaileItemByHosType(hosType));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

}
