package com.sopnobazz.demo.doctor_patient.service;


import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.doctor_patient.entity.DoctorInformation;
import com.sopnobazz.demo.doctor_patient.repository.DoctorInformationRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @Since Dec 14, 2021
 * @Author Md. Rahimul Islam - V00006
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Service
@AllArgsConstructor
public class DoctorInformationServiceImpl implements DoctorInformationService {
    private DoctorInformationRepository repo;
    private final CommonUtils commonUtils;

    @Override
    public DoctorInformation save(DoctorInformation entity) {
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Override
    public DoctorInformation update(DoctorInformation entity) {
        DoctorInformation dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public DoctorInformation delete(DoctorInformation entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<DoctorInformation> getAll() {
        return repo.findAll();
    }

    @Override
    public List<DoctorInformation> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<DoctorInformation> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<DoctorInformation> pageResult = repo.findAll(pageRequest);
        List<DoctorInformation> objList = pageResult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objList, pageRequest, pageResult.getTotalElements());
    }

    @Override
    public DoctorInformation getByAppUserId(Integer appUser) {
        return repo.findByAppUserId(appUser);
    }


}
