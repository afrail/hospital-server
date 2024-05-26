package com.sopnobazz.demo.sysadmin.service;


import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.entity.AppUser;
import com.sopnobazz.demo.comon.entity.PasswordHistory;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.sysadmin.dto.AppUserEmployeeDto;
import com.sopnobazz.demo.sysadmin.entity.AppUserEmployee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sopnobazz.demo.comon.repository.AppUserRepository;
import com.sopnobazz.demo.comon.repository.PasswordHistoryRepository;
import com.sopnobazz.demo.sysadmin.repository.AppUserEmployeeRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */


@Service
@AllArgsConstructor
public class AppUserEmployeeServiceImpl implements AppUserEmployeeService {

    private final AppUserEmployeeRepository repo;
    private final AppUserRepository appUserRepo;
    private final PasswordHistoryRepository passwordHistoryRepo;
    private PasswordEncoder encoder;
    private final CommonUtils commonUtils;


    @Override
    public AppUserEmployee save(AppUserEmployeeDto dto) {
        /* save app user employee */
        AppUserEmployee entity = convertDtoToEntity(dto);
        entity.setEntryUser(dto.getEntryUser());
        commonUtils.setEntryUserInfo(entity);
        AppUserEmployee savedEntity = repo.save(entity);

        /* save password history */
        if (!ObjectUtils.isEmpty(savedEntity)) {
            savePasswordHistory(dto);
        }

        /* update app user information */
        updateAppuserInfoByActiveEmpUser(savedEntity, dto);

        return savedEntity;
    }


    @Override
    public AppUserEmployee update(AppUserEmployeeDto dto) {
        /* save app user employee */
        AppUserEmployee entity = convertDtoToEntity(dto);
        entity.setUpdateUser(dto.getUpdateUser());
        AppUserEmployee dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
//    	if(!entity.getActive()) {
//    		entity.setInactiveDate(new Date());
//    	}
        AppUserEmployee savedEntity = repo.save(entity);

        /* save password history */
        if (entity.getActive() && !ObjectUtils.isEmpty(savedEntity)) {
            savePasswordHistory(dto);
        }

        /* update app user */
        updateAppuserInfoByActiveEmpUser(savedEntity, dto);

        return savedEntity;

    }

    @Override
    public AppUserEmployee delete(AppUserEmployee entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<AppUserEmployee> getAll() {
        return repo.findAll();
    }

    @Override
    public List<AppUserEmployee> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<AppUserEmployee> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<AppUserEmployee> pageresult = repo.findAll(pageRequest);
        List<AppUserEmployee> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }

    @Override
    public AppUserEmployee getByAppUserId(Integer appUserId) {
        return repo.findByAppUserIdAndActive(appUserId, true);
    }

    @Override
    public List<AppUserEmployee> getByTransactionIdAndTransactionTable(Integer transactionId, String transactionTable) {
        return repo.findByTransactionIdAndTransactionTable(transactionId, transactionTable);
    }

    //============================== Helper ================================

    public AppUserEmployee convertDtoToEntity(AppUserEmployeeDto dto) {
        AppUserEmployee entity = new AppUserEmployee();
        entity.setId(dto.getId());
        entity.setAppUser(dto.getAppUser());
        entity.setEmployeeCode(dto.getEmployeeCode());
        entity.setDisplayName(dto.getDisplayName());
        entity.setName(dto.getName());
        entity.setBanglaName(dto.getBanglaName());
        entity.setDesignation(dto.getDesignation());
        entity.setMobile(dto.getMobile());
        entity.setEmail(dto.getEmail());
        entity.setActiveDate(dto.getActiveDate());
        entity.setInactiveDate(dto.getInactiveDate());
        entity.setActive(dto.getActive());
        return entity;
    }

    public void savePasswordHistory(AppUserEmployeeDto dto) {

        PasswordHistory history = new PasswordHistory();
        history.setEntryUser(dto.getEntryUser());
        history.setAppUser(dto.getAppUser());
//    	history.setPassword(encoder.encode(dto.getPassword()));
        history.setPassword(dto.getPassword());
        commonUtils.setEntryUserInfo(history);
        passwordHistoryRepo.save(history);

    }

    public void updateAppuserInfoByActiveEmpUser(AppUserEmployee savedEntity, AppUserEmployeeDto dto) {


        if (!ObjectUtils.isEmpty(savedEntity)) {

            AppUser appUser = savedEntity.getAppUser();
            AppUserEmployee activeEntity = repo.findByAppUserIdAndActive(appUser.getId(), true);

            if (!ObjectUtils.isEmpty(activeEntity)) {
                appUser.setAccountExpired(false);
                appUser.setUsername(activeEntity.getUpdateAppUserName());
                appUser.setDesignation(activeEntity.getDesignation());
                appUser.setName(activeEntity.getDisplayName());
                appUser.setBanglaName(activeEntity.getBanglaName());
            } else {
                appUser.setAccountExpired(true);
                appUser.setUsername("");
                appUser.setDesignation("");
                appUser.setName("");
                appUser.setBanglaName("");
            }

            if (dto.getPassword() != null && dto.getPassword() != "") {
                appUser.setPassword(encoder.encode(dto.getPassword()));
            }

            appUserRepo.save(appUser);
        }
    }


}
