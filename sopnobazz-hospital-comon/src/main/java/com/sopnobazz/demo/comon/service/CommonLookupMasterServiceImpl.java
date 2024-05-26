package com.sopnobazz.demo.comon.service;


import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.entity.CommonSetupMaster;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.comon.repository.CommonLookupMasterRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Since May 20, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */
@Service
@AllArgsConstructor
public class CommonLookupMasterServiceImpl implements CommonLookupMasterService {

    private final CommonLookupMasterRepository repo;
    private final CommonUtils commonUtils;

    @Override
    public CommonSetupMaster save(CommonSetupMaster entity) {
        /* set code */
        entity.setCode(commonUtils.generateCode("LM", repo.getMaxId(), 3));

        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Override
    public CommonSetupMaster update(CommonSetupMaster entity) {
        CommonSetupMaster dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public CommonSetupMaster delete(CommonSetupMaster entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<CommonSetupMaster> getAll() {
        return repo.findAll();
    }

    @Override
    public List<CommonSetupMaster> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<CommonSetupMaster> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<CommonSetupMaster> pageresult = repo.findAll(pageRequest);
        List<CommonSetupMaster> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }

    @Override
    public List<CommonSetupMaster> getMovementConfig() {
        return repo.getMovementConfigMasterValue();
    }

}
