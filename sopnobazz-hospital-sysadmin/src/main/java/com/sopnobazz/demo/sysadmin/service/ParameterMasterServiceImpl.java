package com.sopnobazz.demo.sysadmin.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.sysadmin.entity.ParameterMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.sysadmin.repository.ParameterMasterRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Service
@AllArgsConstructor
public class ParameterMasterServiceImpl implements ParameterMasterService {

    private ParameterMasterRepository repo;
    private final CommonUtils commonUtils;

    @Override
    public ParameterMaster save(ParameterMaster entity) {
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Override
    public ParameterMaster update(ParameterMaster entity) {
        ParameterMaster dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public ParameterMaster delete(ParameterMaster entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<ParameterMaster> getAll() {
        return repo.findAll();
    }

    @Override
    public List<ParameterMaster> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<ParameterMaster> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<ParameterMaster> pageresult = repo.findAll(pageRequest);
        List<ParameterMaster> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }
}
