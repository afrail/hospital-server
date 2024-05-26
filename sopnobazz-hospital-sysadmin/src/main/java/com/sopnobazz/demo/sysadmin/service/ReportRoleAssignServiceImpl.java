package com.sopnobazz.demo.sysadmin.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.sysadmin.dto.ReportRoleAssignDto;
import com.sopnobazz.demo.sysadmin.entity.ReportRole;
import com.sopnobazz.demo.sysadmin.entity.ReportRoleAssign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sopnobazz.demo.sysadmin.repository.ReportRoleAssignRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Service
@AllArgsConstructor
public class ReportRoleAssignServiceImpl implements ReportRoleAssignService {

    private final ReportRoleAssignRepository repo;
    private final CommonUtils commonUtils;

    @Transactional
    @Override
    public ReportRoleAssignDto save(ReportRoleAssignDto dto, int userId) {
        for (ReportRole role : dto.getReportRoleList()) {
            ReportRoleAssign entity = new ReportRoleAssign();
            entity.setActive(dto.getActive());
            entity.setAppUser(dto.getAppUser());
            entity.setReportRole(role);
            entity.setEntryUser(userId);
            commonUtils.setEntryUserInfo(entity);
            repo.save(entity);
        }
        return dto;
    }

    @Transactional
    @Override
    public ReportRoleAssignDto update(ReportRoleAssignDto dto, int userId) {

        // first delete data which is delete from font end
        deleteDetilsData(dto);

        // now save data
        for (ReportRole role : dto.getReportRoleList()) {
            ReportRoleAssign dbEntity = repo.findByAppUserIdAndReportRoleId(dto.getAppUser().getId(), role.getId());

            if (!ObjectUtils.isEmpty(dbEntity)) {
                dbEntity.setUpdateUser(userId);
                commonUtils.setUpdateUserInfo(dbEntity, dbEntity);
                repo.save(dbEntity);
            } else {
                ReportRoleAssign entity = new ReportRoleAssign();
                entity.setActive(dto.getActive());
                entity.setAppUser(dto.getAppUser());
                entity.setReportRole(role);
                entity.setEntryUser(userId);
                commonUtils.setEntryUserInfo(entity);
                repo.save(entity);
            }
        }

        return dto;
    }


    @Transactional
    @Override
    public ReportRoleAssignDto delete(ReportRoleAssignDto dto) {
        List<ReportRoleAssign> listForCheckDelete = repo.findByAppUserId(dto.getAppUser().getId());
        for (ReportRoleAssign obj : listForCheckDelete) {
            repo.delete(obj);
        }
        return dto;
    }

    @Override
    public List<ReportRoleAssignDto> getAll() {
        return gererareGroupFromDetails(repo.findAll());
    }

    @Override
    public List<ReportRoleAssignDto> getAllActive() {
        return gererareGroupFromDetails(repo.findByActive(true));
    }

    @Override
    public Page<ReportRoleAssignDto> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        List<ReportRoleAssignDto> objlist = gererareGroupFromDetails(repo.findAllByOrderByIdDesc());
        return new PageImpl<>(objlist, pageRequest, objlist.size());
    }


    private List<ReportRoleAssignDto> gererareGroupFromDetails(List<ReportRoleAssign> details) {
        List<ReportRoleAssignDto> groupList = new ArrayList<>();
        for (ReportRoleAssign obj : details) {
            boolean isExists = false;
            for (ReportRoleAssignDto grp : groupList) {
                if (grp.getAppUser().getId() == obj.getAppUser().getId()) {
                    isExists = true;
                    grp.getReportRoleList().add(obj.getReportRole());
                }
            }
            if (!isExists) {
                ReportRoleAssignDto group = new ReportRoleAssignDto();
                group.setAppUser(obj.getAppUser());
                group.setActive(obj.getActive());
                List<ReportRole> list = new ArrayList<ReportRole>();
                list.add(obj.getReportRole());
                group.setReportRoleList(list);
                groupList.add(group);
            }

        }
        return groupList;
    }

    private void deleteDetilsData(ReportRoleAssignDto dto) {
        List<ReportRoleAssign> listForCheckDelete = repo.findByAppUserId(dto.getAppUser().getId());

        for (ReportRoleAssign obj : listForCheckDelete) {
            boolean isNeedToDelete = true;
            for (ReportRole detailsEntity : dto.getReportRoleList()) {
                if (obj.getReportRole().getId() == detailsEntity.getId()) {
                    isNeedToDelete = false;
                    break;
                }
            }
            if (isNeedToDelete) {
                repo.delete(obj);
            }
        }
    }


}
