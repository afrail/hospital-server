/**
 * 
 */
package com.sopnobazz.hospital.doctor_patient.service;


import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.hospital.doctor_patient.entity.PatientInvestigationReport;
import com.sopnobazz.hospital.doctor_patient.repository.PatientInvestigationReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;

/**
 * @Since April 24, 2021
 * @Author Md Mizanur Rahman -598
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Service
@AllArgsConstructor
public class PatientInvestigationReportServiceImpl implements PatientInvestigationReportService {

	private final PatientInvestigationReportRepository repo;
	private final CommonUtils commonUtils;

    @Override
    public PatientInvestigationReport save(PatientInvestigationReport entity){
    	commonUtils.setEntryUserInfo(entity);
		return repo.save(entity);
    }
    
    @Override
	public PatientInvestigationReport update(PatientInvestigationReport entity) {
    	PatientInvestigationReport dbEntity = repo.findById(entity.getId()).get();
    	commonUtils.setUpdateUserInfo(entity, dbEntity);
		return repo.save(entity);
	}

    @Override
    public PatientInvestigationReport delete(PatientInvestigationReport entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<PatientInvestigationReport> getAll() {
		return repo.findAll();
    }
    
    @Override
    public List<PatientInvestigationReport> getAllActive() {
		return repo.findByActive(true);
    }

    @Override
    public Page<PatientInvestigationReport> getPageableList(int page, int size) {
    	PageRequest pageRequest  = commonUtils.getPageRequest(page, size);
        Page<PatientInvestigationReport> pageresult = repo.findAll(pageRequest);
        List<PatientInvestigationReport> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist,pageRequest,pageresult.getTotalElements());
    }

    
}
