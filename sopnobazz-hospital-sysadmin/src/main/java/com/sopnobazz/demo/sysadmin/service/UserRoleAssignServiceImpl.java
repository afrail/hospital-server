package com.sopnobazz.demo.sysadmin.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.entity.UserRoleAssign;
import com.sopnobazz.demo.comon.entity.UserRoleAssignMaster;
import com.sopnobazz.demo.comon.repository.UserRoleAssignRepository;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.sysadmin.dto.UserRoleAssignDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sopnobazz.demo.comon.repository.UserRoleAssignMasterRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */


@Service
@AllArgsConstructor
public class UserRoleAssignServiceImpl implements UserRoleAssignService {

    private final UserRoleAssignMasterRepository masterRepository;
    private final UserRoleAssignRepository detailsRepository;

    private final CommonUtils commonUtils;

    @Override
    public UserRoleAssignDto save(UserRoleAssignDto body, int userId) {
        UserRoleAssignMaster entity = body.getMaster();
        List<UserRoleAssign> details = body.getDetailsList();

        /*save master*/
        entity.setEntryUser(userId);
        commonUtils.setEntryUserInfo(entity);
        UserRoleAssignMaster saveEntity = masterRepository.save(entity);

        /*save details*/
        if (!ObjectUtils.isEmpty(saveEntity)) {
            List<UserRoleAssign> listForSave = new ArrayList<>();
            for (UserRoleAssign detail : details) {
                detail.setEntryUser(userId);
                detail.setAppUser(entity.getAppUser());
                commonUtils.setEntryUserInfo(detail);
                detail.setMaster(saveEntity);
                listForSave.add(detail);
            }
            detailsRepository.saveAll(listForSave);
        }
        return body;

    }

    @Override
    public UserRoleAssignDto update(UserRoleAssignDto body, int userId) {

        UserRoleAssignMaster entity = body.getMaster();
        List<UserRoleAssign> details = body.getDetailsList();

        /*save master*/
        entity.setUpdateUser(userId);
        UserRoleAssignMaster dbEntity = masterRepository.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        UserRoleAssignMaster saveEntity = masterRepository.save(entity);

        /*save details*/
        if (!ObjectUtils.isEmpty(saveEntity)) {

            /* delete previous data if needed */
            deleteDetilsData(saveEntity, body);

            /* now save data*/
            List<UserRoleAssign> listForSave = new ArrayList<>();
            for (UserRoleAssign detailsEntity : details) {
                detailsEntity.setMaster(saveEntity);
                if (detailsEntity.getId() == null) {
                    detailsEntity.setAppUser(entity.getAppUser());
                    detailsEntity.setEntryUser(userId);
                    commonUtils.setEntryUserInfo(detailsEntity);
                } else {
                    detailsEntity.setUpdateUser(userId);
                    detailsEntity.setUpdateDate(new Date());
                }

                listForSave.add(detailsEntity);
            }
            detailsRepository.saveAll(listForSave);
        }
        return body;
    }

    @Override
    public UserRoleAssignDto delete(UserRoleAssignDto entity) {
        detailsRepository.deleteAll(detailsRepository.findByMasterIdOrderById(entity.getMaster().getId()));
        masterRepository.delete(entity.getMaster());
        return entity;
    }

    @Override
    public List<UserRoleAssignDto> getAll() {
        return convertDetailsToMasterDetails(detailsRepository.findAll());
    }

    @Override
    public List<UserRoleAssignDto> getAllActive() {
        return convertDetailsToMasterDetails(detailsRepository.findByActive(true));
    }

    @Override
    public Page<UserRoleAssignDto> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<UserRoleAssignMaster> pageResult = masterRepository.findAll(pageRequest);
        List<UserRoleAssignMaster> objList = pageResult.stream().collect(Collectors.toList());
        return new PageImpl<>(convertMasterToMasterDetails(objList), pageRequest, pageResult.getTotalElements());
    }

    /*helper*/
    private void deleteDetilsData(UserRoleAssignMaster savedEntity, UserRoleAssignDto body) {
        List<UserRoleAssign> listForCheckDelete = detailsRepository.findByMasterIdOrderById(savedEntity.getId());
        for (UserRoleAssign obj : listForCheckDelete) {
            boolean isNeedToDelete = true;
            for (UserRoleAssign detailsEntity : body.getDetailsList()) {
                if (obj.getId() == detailsEntity.getId()) {
                    isNeedToDelete = false;
                    break;
                }
            }
            if (isNeedToDelete) {
                detailsRepository.delete(obj);
            }
        }
    }

    private List<UserRoleAssignDto> convertMasterToMasterDetails(List<UserRoleAssignMaster> list) {
        List<UserRoleAssignDto> returnList = new ArrayList<>();
        for (UserRoleAssignMaster master : list) {
            UserRoleAssignDto tmp = new UserRoleAssignDto();
            /*set master*/
            tmp.setMaster(master);
            /*set details*/
            tmp.setDetailsList(detailsRepository.findByMasterIdOrderById(master.getId()));
            returnList.add(tmp);
        }
        return returnList;
    }

    private List<UserRoleAssignDto> convertDetailsToMasterDetails(List<UserRoleAssign> details) {
        List<UserRoleAssignDto> groupList = new ArrayList<>();
        for (UserRoleAssign obj : details) {
            boolean isExists = false;
            for (UserRoleAssignDto grp : groupList) {
                if (grp.getMaster().getId() == obj.getMaster().getId()) {
                    isExists = true;
                    grp.getDetailsList().add(obj);
                }
            }
            if (!isExists) {
                UserRoleAssignDto group = new UserRoleAssignDto();
                group.setMaster(obj.getMaster());
                List<UserRoleAssign> list = new ArrayList<UserRoleAssign>();
                list.add(obj);
                group.setDetailsList(list);
                groupList.add(group);
            }

        }
        return groupList;
    }

}
