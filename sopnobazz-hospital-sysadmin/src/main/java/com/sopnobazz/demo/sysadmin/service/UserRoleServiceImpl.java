package com.sopnobazz.demo.sysadmin.service;


import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.entity.UserRole;
import com.sopnobazz.demo.comon.entity.UserRolePermission;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.sysadmin.repository.UserRolePermissionRepository;
import com.sopnobazz.demo.sysadmin.repository.UserRoleRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Service
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository repo;
    private final UserRolePermissionRepository rolePermissionrepo;
    private final CommonUtils commonUtils;

    @Override
    public UserRole save(UserRole entity) {
        commonUtils.setEntryUserInfo(entity);
        entity.setCode(commonUtils.generateCode("UR", repo.getMaxId(), 3));

        for (UserRolePermission obj : entity.getRolePermissionList()) {
            obj.setEntryUser(entity.getEntryUser());
            commonUtils.setEntryUserInfo(obj);
        }

        return repo.save(entity);
    }

    @Override
    public UserRole update(UserRole entity) {
        UserRole dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);

        for (UserRolePermission obj : entity.getRolePermissionList()) {

            if (obj.getId() == null) {
                obj.setEntryUser(entity.getUpdateUser());
                commonUtils.setEntryUserInfo(obj);
            } else {
                UserRolePermission dbEntityPermission = rolePermissionrepo.findById(obj.getId()).get();
                obj.setUpdateUser(entity.getUpdateUser());
                commonUtils.setUpdateUserInfo(obj, dbEntityPermission);
            }

            rolePermissionrepo.save(obj);
        }

        return repo.save(entity);
    }

    @Override
    public UserRole delete(UserRole entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<UserRole> getAll() {
        return repo.findAll();
    }

    @Override
    public List<UserRole> getAllActive() {
        return repo.findByActive(true);
    }


    @Override
    public Page<UserRole> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<UserRole> pageresult = repo.findAll(pageRequest);
        List<UserRole> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }


}
