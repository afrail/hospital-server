package com.sopnobazz.demo.comon.service;

import java.util.List;

import com.sopnobazz.demo.comon.entity.CommonMessageHistory;

/**
 * @version 2.0.0
 * @Since 3/23/2022
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */

public interface CommonMessageHistoryService {
    CommonMessageHistory save(CommonMessageHistory entity);

    CommonMessageHistory update(CommonMessageHistory entity);

    CommonMessageHistory getById(Integer id);

    List<CommonMessageHistory> getAllByNotRemoveList(String userName);
}
