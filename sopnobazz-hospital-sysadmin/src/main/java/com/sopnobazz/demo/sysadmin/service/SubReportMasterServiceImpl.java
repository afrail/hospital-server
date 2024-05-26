package com.sopnobazz.demo.sysadmin.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.sysadmin.entity.SubReportMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sopnobazz.demo.sysadmin.repository.SubReportMasterRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Service
@AllArgsConstructor
public class SubReportMasterServiceImpl implements SubReportMasterService {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(SubReportMasterServiceImpl.class);

    private final SubReportMasterRepository repo;
    private final CommonUtils commonUtils;

    @PostConstruct
    public void init() {
    }


    @Transactional
    @Override
    public SubReportMaster save(SubReportMaster entity) throws IOException {
        commonUtils.setEntryUserInfo(entity);
        //save report info to database
        return repo.save(entity);
    }

    @Transactional
    @Override
    public SubReportMaster update(SubReportMaster entity) throws IOException {

        SubReportMaster data = repo.findById(entity.getId()).get();
        if (ObjectUtils.isEmpty(data)) {
            return null;
        }
        commonUtils.setUpdateUserInfo(entity, data);
        //save report info to database
        return repo.save(entity);
    }

    @Transactional
    @Override
    public SubReportMaster delete(SubReportMaster entity) throws IOException {

        SubReportMaster data = repo.findById(entity.getId()).get();
        if (ObjectUtils.isEmpty(data)) {
            return null;
        }
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<SubReportMaster> getAll() {
        return repo.findAll();
    }

    @Override
    public List<SubReportMaster> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<SubReportMaster> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<SubReportMaster> pageresult = repo.findAll(pageRequest);
        List<SubReportMaster> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }


    @Override
    public SubReportMaster getById(Integer id) {
        return repo.findById(id).get();
    }


    @Override
    public List<SubReportMaster> getByReportMasterIdAndActive(int reportMasterId, boolean active) {
        return repo.findByReportMasterIdAndActive(reportMasterId, active);
    }

}
