package com.ibcsprimax.bof.employee_health.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ibcsprimax.bof.comon.utils.CommonUtils;
import com.ibcsprimax.bof.employee_health.entity.PatientIllnessHistory;
import com.ibcsprimax.bof.employee_health.repository.PatientIllnessHistoryRepository;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientIllnessHistoryServiceImpl implements PatientIllnessHistoryService{
	
	private PatientIllnessHistoryRepository repo;
	private final CommonUtils commonUtils;

	@Override
	public PatientIllnessHistory save(PatientIllnessHistory entity) {
		commonUtils.setEntryUserInfo(entity);
		return repo.save(entity);
	}

	@Override
	public PatientIllnessHistory update(PatientIllnessHistory entity) {
		PatientIllnessHistory dbEntity = repo.findById(entity.getId()).get();
		commonUtils.setUpdateUserInfo(entity, dbEntity);
		return repo.save(entity);
	}

	@Override
	public PatientIllnessHistory delete(PatientIllnessHistory entity) {
		repo.delete(entity);
		return entity;
	}

	@Override
	public List<PatientIllnessHistory> getAll() {
		
		return repo.findAll();
	}

	@Override
	public List<PatientIllnessHistory> getAllActive() {
		
		return repo.findByActive(true);
	}

	@Override
	public Page<PatientIllnessHistory> getPageableList(int page, int size) {
		
		 PageRequest pageRequest = commonUtils.getPageRequest(page, size);
		 Page<PatientIllnessHistory> resulPage = repo.findAll(pageRequest);
		 List<PatientIllnessHistory> objList = resulPage.stream().collect(Collectors.toList());
		return new PageImpl<PatientIllnessHistory>(objList,pageRequest,resulPage.getTotalElements());
	}

	@Override
	public List<PatientIllnessHistory> getByPatId(Integer patId) {
		return repo.findByPatientInfoId(patId);
	}


}
