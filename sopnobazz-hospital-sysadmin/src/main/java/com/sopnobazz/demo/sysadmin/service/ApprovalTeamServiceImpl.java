package com.sopnobazz.demo.sysadmin.service;


import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.entity.ApprovalTeamDetails;
import com.sopnobazz.demo.comon.entity.ApprovalTeamMaster;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.comon.repository.ApprovalTeamDetailsRepository;
import com.sopnobazz.demo.comon.repository.ApprovalTeamMasterRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Service
@AllArgsConstructor
public class ApprovalTeamServiceImpl implements ApprovalTeamService {

    private final ApprovalTeamMasterRepository repo;
    private final ApprovalTeamDetailsRepository detailsRepo;
    private final CommonUtils commonUtils;

    @Override
    public ApprovalTeamMaster save(ApprovalTeamMaster entity) {
        commonUtils.setEntryUserInfo(entity);

        for (ApprovalTeamDetails obj : entity.getApprovalTeamDetailList()) {
            obj.setEntryUser(entity.getEntryUser());
            commonUtils.setEntryUserInfo(obj);
        }

        return repo.save(entity);
    }

    @Override
    public ApprovalTeamMaster update(ApprovalTeamMaster entity) {
        ApprovalTeamMaster dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);

        for (ApprovalTeamDetails obj : entity.getApprovalTeamDetailList()) {

            if (obj.getId() == null) {
                obj.setEntryUser(entity.getUpdateUser());
                commonUtils.setEntryUserInfo(obj);
            } else {
                ApprovalTeamDetails dbEntityPermission = detailsRepo.findById(obj.getId()).get();
                obj.setUpdateUser(entity.getUpdateUser());
                commonUtils.setUpdateUserInfo(obj, dbEntityPermission);
            }

            detailsRepo.save(obj);
        }

        return repo.save(entity);
    }

    @Override
    public ApprovalTeamMaster delete(ApprovalTeamMaster entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<ApprovalTeamMaster> getAll() {
        return repo.findAll();
    }

    @Override
    public List<ApprovalTeamMaster> getAllActive() {
        return repo.findByActive(true);
    }


    @Override
    public Page<ApprovalTeamMaster> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<ApprovalTeamMaster> pageresult = repo.findAll(pageRequest);
        List<ApprovalTeamMaster> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }

    @Override
    public List<ApprovalTeamMaster> getByModuleId(Integer moduleId) {
        return repo.findBymoduleIdAndActive(moduleId, true);
    }


}
