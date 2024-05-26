package com.sopnobazz.demo.sysadmin.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.sysadmin.entity.ReportMaster;
import com.sopnobazz.demo.sysadmin.entity.ReportRoleAssign;
import com.sopnobazz.demo.sysadmin.entity.ReportRolePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.sopnobazz.demo.sysadmin.repository.ReportMasterRepository;
import com.sopnobazz.demo.sysadmin.repository.ReportRoleAssignRepository;
import com.sopnobazz.demo.sysadmin.repository.ReportRolePermissionRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Service
@AllArgsConstructor
public class ReportMasterServiceImpl implements ReportMasterService {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(ReportMasterServiceImpl.class);

    private final ReportMasterRepository repo;
    private final ReportRoleAssignRepository reportRoleAssignRepository;
    private final ReportRolePermissionRepository reportRolePermissionRepository;
    private final CommonUtils commonUtils;

    @PostConstruct
    public void init() {
    }


    @Transactional
    @Override
    public ReportMaster save(ReportMaster entity) throws IOException {
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Transactional
    @Override
    public ReportMaster update(ReportMaster entity) throws IOException {

        ReportMaster data = repo.findById(entity.getId()).get();
        if (ObjectUtils.isEmpty(data)) {
            return null;
        }
        commonUtils.setUpdateUserInfo(entity, data);
        return repo.save(entity);
    }

    @Transactional
    @Override
    public ReportMaster delete(ReportMaster entity) throws IOException {

        ReportMaster data = repo.findById(entity.getId()).get();
        if (ObjectUtils.isEmpty(data)) {
            return null;
        }
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<ReportMaster> getAll() {
        return repo.findAll();
    }

    @Override
    public List<ReportMaster> getAllActive() {
        return repo.findByActiveAndSerial();
    }

    @Override
    public Page<ReportMaster> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<ReportMaster> pageresult = repo.findAll(pageRequest);
        List<ReportMaster> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }


    @Override
    public ReportMaster getById(Integer id) {
        return repo.findById(id).get();
    }


    @Override
    public List<ReportMaster> getAllAuthorizedReport(int userId) {
        List<ReportMaster> res = new ArrayList<>();
        // find from report role assign
        List<ReportRoleAssign> roleAssignList = reportRoleAssignRepository.findByAppUserId(userId);
        if (!CollectionUtils.isEmpty(roleAssignList)) {

            for (ReportRoleAssign roleAssign : roleAssignList) {

                // find from report role
                List<ReportRolePermission> reportRolePermissionList = reportRolePermissionRepository.findByReportRoleId(roleAssign.getReportRole().getId());
                if (!CollectionUtils.isEmpty(reportRolePermissionList)) {

                    for (ReportRolePermission p : reportRolePermissionList) {
                        res.add(p.getReport());

                    }
                }
            }
        }

        return res;
    }

}
