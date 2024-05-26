/**
 *
 */
package com.sopnobazz.demo.sysadmin.doctor;

import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 * @version 1.0.0
 */


@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repo;

    @Autowired
    private CommonUtils commonUtils;


    public Doctor save(Doctor entity) {
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }


    public Doctor update(Doctor entity) {
        Doctor dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }


    public Doctor delete(Doctor entity) {
        repo.delete(entity);
        return entity;
    }


    public List<Doctor> getAll() {
        return repo.findAll();
    }


    public List<Doctor> getAllActive() {
        return repo.findByActive(true);
    }


    public Page<Doctor> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<Doctor> pageresult = repo.findAll(pageRequest);
        List<Doctor> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }

}
