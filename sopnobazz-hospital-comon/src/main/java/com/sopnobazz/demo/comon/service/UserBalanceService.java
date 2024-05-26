/**
 *
 */
package com.sopnobazz.demo.comon.service;

import com.sopnobazz.demo.comon.entity.UserBalance;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.comon.repository.UserBalanceRepository;

/**
 * @Project hospital-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 9, 2022
 * @version 1.0.0
 */

@Service
public class UserBalanceService {

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private UserBalanceRepository repo;


    public UserBalance save(UserBalance e) {
        commonUtils.setEntryUserInfo(e);
        return repo.save(e);
    }
}
