package com.sopnobazz.demo.doctor_patient.service;


import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.doctor_patient.entity.EhmCommonLookupMaster;
import com.sopnobazz.demo.doctor_patient.repository.EhmCommonLookupMasterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Since Dec 07, 2021
 * @Author Mohaiminul Haq - 582
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Service
@AllArgsConstructor
public class EhmCommonLookupMasterServiceImpl implements EhmCommonLookupMasterService {

    private EhmCommonLookupMasterRepository repo;
    private final CommonUtils commonUtils;

    @Override
    public EhmCommonLookupMaster save(EhmCommonLookupMaster entity) {
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Override
    public EhmCommonLookupMaster update(EhmCommonLookupMaster entity) {
        EhmCommonLookupMaster dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public EhmCommonLookupMaster delete(EhmCommonLookupMaster entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<EhmCommonLookupMaster> getAll() {
        return repo.findAll();
    }

    @Override
    public List<EhmCommonLookupMaster> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<EhmCommonLookupMaster> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<EhmCommonLookupMaster> pageresult = repo.findAll(pageRequest);
        List<EhmCommonLookupMaster> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }
}
