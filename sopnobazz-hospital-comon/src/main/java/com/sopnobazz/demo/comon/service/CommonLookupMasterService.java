package com.sopnobazz.demo.comon.service;

import java.util.List;

import com.sopnobazz.demo.comon.entity.CommonSetupMaster;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Since May 20, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */
public interface CommonLookupMasterService {

    //CRUD function
    CommonSetupMaster save(CommonSetupMaster CommonSetupMaster);

    CommonSetupMaster update(CommonSetupMaster CommonSetupMaster);

    CommonSetupMaster delete(CommonSetupMaster CommonSetupMaster);

    List<CommonSetupMaster> getAll();

    List<CommonSetupMaster> getAllActive();

    List<CommonSetupMaster> getMovementConfig();

    Page<CommonSetupMaster> getPageableList(int page, int size);


    //business function

}
