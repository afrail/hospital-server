package com.sopnobazz.demo.doctor_patient.service;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.doctor_patient.entity.EhmCommonLookupDetails;
import com.sopnobazz.demo.doctor_patient.repository.EhmCommonLookupDetailsRepository;
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
public class EhmCommonLookupDetailsServiceImpl implements EhmCommonLookupDetailsService {

    private EhmCommonLookupDetailsRepository repo;
    private final CommonUtils commonUtils;

    @Override
    public EhmCommonLookupDetails save(EhmCommonLookupDetails entity) {
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Override
    public EhmCommonLookupDetails update(EhmCommonLookupDetails entity) {
        EhmCommonLookupDetails dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public EhmCommonLookupDetails delete(EhmCommonLookupDetails entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<EhmCommonLookupDetails> getAll() {
        return repo.findAll();
    }

    @Override
    public List<EhmCommonLookupDetails> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<EhmCommonLookupDetails> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<EhmCommonLookupDetails> pageresult = repo.findAll(pageRequest);
        List<EhmCommonLookupDetails> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }
    
    @Override
    public List<EhmCommonLookupDetails> getByMasterId(int masterId) {
        return repo.findByMasterIdAndActiveOrderByIdDesc(masterId, true);
    }

    @Override
    public List<EhmCommonLookupDetails> getByMasterAscId(int id) {
        return repo.findByMasterIdAndActiveOrderByIdAsc(id, true);
    }

}
