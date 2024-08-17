package com.sopnobazz.demo.doctor_patient.service;



import com.sopnobazz.demo.doctor_patient.entity.EhmCommonLookupDetails;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Since Dec 07, 2021
 * @Author Mohaiminul Haq - 582
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

public interface EhmCommonLookupDetailsService {
    EhmCommonLookupDetails save(EhmCommonLookupDetails ehmCommonLookupDetails);

    EhmCommonLookupDetails update(EhmCommonLookupDetails ehmCommonLookupDetails);

    EhmCommonLookupDetails delete(EhmCommonLookupDetails ehmCommonLookupDetails);

    List<EhmCommonLookupDetails> getAll();

    List<EhmCommonLookupDetails> getAllActive();

    Page<EhmCommonLookupDetails> getPageableList(int page, int size);
    
	/* business */
    List<EhmCommonLookupDetails> getByMasterId(int id);
    List<EhmCommonLookupDetails> getByMasterAscId(int id);
}
