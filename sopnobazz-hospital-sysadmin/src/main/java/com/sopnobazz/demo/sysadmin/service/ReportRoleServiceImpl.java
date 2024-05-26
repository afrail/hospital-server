package com.sopnobazz.demo.sysadmin.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.sysadmin.dto.ReportRoleDto;
import com.sopnobazz.demo.sysadmin.entity.ReportRole;
import com.sopnobazz.demo.sysadmin.entity.ReportRolePermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sopnobazz.demo.sysadmin.repository.ReportRolePermissionRepository;
import com.sopnobazz.demo.sysadmin.repository.ReportRoleRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Service
@AllArgsConstructor
public class ReportRoleServiceImpl implements ReportRoleService {

    private final ReportRoleRepository repo;
    private final ReportRolePermissionRepository rolePermissionRepo;
    private final CommonUtils commonUtils;

    @Transactional
    @Override
    public ReportRoleDto save(ReportRoleDto dto, int userId) {

        ReportRole role = dto.getReportRole();
        role.setEntryUser(userId);
        commonUtils.setEntryUserInfo(role);
        ReportRole savedEntity = repo.save(role);

        for (ReportRolePermission obj : dto.getReportPermission()) {
            obj.setEntryUser(userId);
            obj.setReportRole(savedEntity);
            commonUtils.setEntryUserInfo(obj);
            rolePermissionRepo.save(obj);
        }

        return dto;
    }

    @Transactional
    @Override
    public ReportRoleDto update(ReportRoleDto dto, int userId) {

        // update master
        ReportRole role = dto.getReportRole();
        role.setUpdateUser(userId);
        role.setUpdateDate(new Date());
        role.setEntryDate(new Date());
        role.setEntryUser(userId);
        role.setEntryUser(userId);
        ReportRole savedEntity = repo.save(role);

        // first delete data which is delete from font end
        deleteDetilsData(savedEntity, dto);

        if (!ObjectUtils.isEmpty(savedEntity)) {

            for (ReportRolePermission detailsEntity : dto.getReportPermission()) {
                detailsEntity.setReportRole(savedEntity);
                if (detailsEntity.getId() == null) {
                    detailsEntity.setEntryUser(userId);
                    commonUtils.setEntryUserInfo(detailsEntity);
                } else {
                    detailsEntity.setEntryDate(new Date());
                    detailsEntity.setEntryUser(userId);
                    detailsEntity.setUpdateUser(userId);
                    detailsEntity.setUpdateDate(new Date());

                }
                rolePermissionRepo.save(detailsEntity);
            }
        }

        return dto;
    }


    @Transactional
    @Override
    public ReportRoleDto delete(ReportRoleDto dto) {
        for (ReportRolePermission p : dto.getReportPermission()) {
            rolePermissionRepo.delete(p);
        }
        repo.delete(dto.getReportRole());
        return dto;
    }

    @Override
    public List<ReportRoleDto> getAll() {
        return gererareGroupFromDetails(rolePermissionRepo.findAll());
    }

    @Override
    public List<ReportRoleDto> getAllActive() {
        return gererareGroupFromDetails(rolePermissionRepo.findByActive(true));
    }


    @Override
    public Page<ReportRoleDto> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        List<ReportRoleDto> objlist = gererareGroupFromDetails(rolePermissionRepo.findAll());
        return new PageImpl<>(objlist, pageRequest, objlist.size());
    }

//    @Override
//    public Page<ReportRole> getPageableList(int page, int size) {
//        PageRequest pageRequest  = commonUtils.getPageRequest(page, size);
//        Page<ReportRole> pageresult = repo.findAll(pageRequest);
//        List<ReportRole> objlist = pageresult.stream()
//                .collect(Collectors.toList());
//        return new PageImpl<>(objlist,pageRequest,pageresult.getTotalElements());
//    }


    private void deleteDetilsData(ReportRole savedEntity, ReportRoleDto group) {
        List<ReportRolePermission> listForCheckDelete = rolePermissionRepo.findByReportRoleId(savedEntity.getId());
        for (ReportRolePermission obj : listForCheckDelete) {
            boolean isNeedToDelete = true;
            for (ReportRolePermission detailsEntity : group.getReportPermission()) {
                if (obj.getId() == detailsEntity.getId()) {
                    isNeedToDelete = false;
                    break;
                }
            }
            if (isNeedToDelete) {
                rolePermissionRepo.delete(obj);
            }
        }
    }


    private List<ReportRoleDto> gererareGroupFromDetails(List<ReportRolePermission> details) {
        List<ReportRoleDto> groupList = new ArrayList<>();
        for (ReportRolePermission obj : details) {
            boolean isExists = false;
            for (ReportRoleDto grp : groupList) {
                if (grp.getReportRole().getId() == obj.getReportRole().getId()) {
                    isExists = true;
                    grp.getReportPermission().add(obj);
                }
            }
            if (!isExists) {
                ReportRoleDto group = new ReportRoleDto();
                group.setReportRole(obj.getReportRole());
                List<ReportRolePermission> list = new ArrayList<ReportRolePermission>();
                list.add(obj);
                group.setReportPermission(list);
                groupList.add(group);
            }

        }
        return groupList;
    }

}
