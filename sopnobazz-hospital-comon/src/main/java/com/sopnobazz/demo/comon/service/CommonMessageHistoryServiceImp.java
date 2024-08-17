package com.sopnobazz.demo.comon.service;

import com.sopnobazz.demo.comon.entity.CommonMessageHistory;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.comon.repository.CommonMessageHistoryRepository;

import java.util.List;

/**
 * @version 2.0.0
 * @Since 3/23/2022
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */

@Service
@AllArgsConstructor
public class CommonMessageHistoryServiceImp implements CommonMessageHistoryService {


    private final CommonMessageHistoryRepository repo;
    private final CommonUtils commonUtils;


    @Override
    public CommonMessageHistory save(CommonMessageHistory entity) {
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Override
    public CommonMessageHistory update(CommonMessageHistory entity) {
        CommonMessageHistory dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public CommonMessageHistory getById(Integer id) {
        return repo.findById(id).get();
    }

    @Override
    public List<CommonMessageHistory> getAllByNotRemoveList(String userName) {
        return repo.getAllByNotRemoveList(userName);
    }
}
