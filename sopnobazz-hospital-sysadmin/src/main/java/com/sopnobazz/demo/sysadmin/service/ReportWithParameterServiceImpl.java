package com.sopnobazz.demo.sysadmin.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.sysadmin.entity.ReportWithParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.sysadmin.repository.ReportWithParameterRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Service
@AllArgsConstructor
public class ReportWithParameterServiceImpl implements ReportWithParameterService {

    private ReportWithParameterRepository repo;
    private final CommonUtils commonUtils;

    @Override
    public ReportWithParameter save(ReportWithParameter entity) {
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Override
    public ReportWithParameter update(ReportWithParameter entity) {
        ReportWithParameter dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public ReportWithParameter delete(ReportWithParameter entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<ReportWithParameter> getAll() {
        return repo.findAll();
    }

    @Override
    public List<ReportWithParameter> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<ReportWithParameter> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<ReportWithParameter> pageresult = repo.findAll(pageRequest);
        List<ReportWithParameter> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }

    @Override
    public List<ReportWithParameter> findByReportMasterId(int reportMasterId) {
        return repo.findParamInfoByReportMasterId(reportMasterId);
    }
}
