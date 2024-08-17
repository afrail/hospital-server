package com.sopnobazz.demo.doctor_patient.service;


import com.sopnobazz.demo.doctor_patient.entity.EhmCommonLookupMaster;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Since Dec 07, 2021
 * @Author Mohaiminul Haq - 582
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

public interface EhmCommonLookupMasterService {

    EhmCommonLookupMaster save(EhmCommonLookupMaster ehmCommonLookupMaster);

    EhmCommonLookupMaster update(EhmCommonLookupMaster ehmCommonLookupMaster);

    EhmCommonLookupMaster delete(EhmCommonLookupMaster ehmCommonLookupMaster);

    List<EhmCommonLookupMaster> getAll();

    List<EhmCommonLookupMaster> getAllActive();

    Page<EhmCommonLookupMaster> getPageableList(int page, int size);
}
