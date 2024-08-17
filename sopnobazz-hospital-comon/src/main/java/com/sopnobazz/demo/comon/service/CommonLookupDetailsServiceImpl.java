package com.sopnobazz.demo.comon.service;


import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.entity.CommonSetupDetails;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.comon.repository.CommonLookupDetailsRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Since May 20, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */
@Service
@AllArgsConstructor
public class CommonLookupDetailsServiceImpl implements CommonLookupDetailsService {

    private final CommonLookupDetailsRepository repo;

    private final CommonUtils commonUtils;

    @Override
    public CommonSetupDetails save(CommonSetupDetails entity) {
        commonUtils.setEntryUserInfo(entity);
        entity.setCode(commonUtils.commonCode("CLD", String.format("%03d", repo.getMaxId() + 1)));
        return repo.save(entity);
    }

    @Override
    public CommonSetupDetails update(CommonSetupDetails entity) {
        CommonSetupDetails dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public CommonSetupDetails delete(CommonSetupDetails entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<CommonSetupDetails> getAll() {
        return repo.findAll();
    }

    @Override
    public List<CommonSetupDetails> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<CommonSetupDetails> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<CommonSetupDetails> pageresult = repo.findAll(pageRequest);
        List<CommonSetupDetails> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }

    /**
     * @Author Md. Nayeemul Islam - 596
     * @Since Jun 2, 2021
     * @version 1.0.0
     */
    @Override
    public List<CommonSetupDetails> getByMasterId(int id) {
        return repo.findByMasterIdAndActiveOrderByName(id, true);
    }

    @Override
    public CommonSetupDetails getById(int id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public List<CommonSetupDetails> getOfficeInfo() {
        return repo.getOfficeInfo();
    }

    @Override
    public List<CommonSetupDetails> getByMasterIdAndSearchValue(Integer masterId, String searchValue) {
        return repo.getByMasterIdAndSearchValue(masterId, searchValue);
    }

    @Override
    public List<CommonSetupDetails> getByDependentMasterIdAndSearchValue(Integer parentId) {
        return repo.getByDependentMasterIdAndSearchValue(parentId);
    }
}
