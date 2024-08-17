package com.sopnobazz.demo.comon.service;


import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.entity.PasswordPolicy;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.comon.repository.PasswordPolicyRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Since July 05, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */
@Service
@AllArgsConstructor
public class PasswordPolicyServiceImpl implements PasswordPolicyService {

    private final PasswordPolicyRepository repo;
    private final CommonUtils commonUtils;

    @Override
    public PasswordPolicy save(PasswordPolicy entity) {
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Override
    public PasswordPolicy update(PasswordPolicy entity) {
        PasswordPolicy dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public PasswordPolicy delete(PasswordPolicy entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<PasswordPolicy> getAll() {
        return repo.findAll();
    }

    @Override
    public List<PasswordPolicy> getAllActive() {
        return repo.findByActive(true);
    }


    @Override
    public Page<PasswordPolicy> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<PasswordPolicy> pageresult = repo.findAll(pageRequest);
        List<PasswordPolicy> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }


}
