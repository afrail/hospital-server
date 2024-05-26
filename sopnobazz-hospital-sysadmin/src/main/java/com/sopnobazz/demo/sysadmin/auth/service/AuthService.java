package com.sopnobazz.demo.sysadmin.auth.service;

import java.util.ArrayList;
import java.util.List;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.sysadmin.dto.UserRoleAssignDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.comon.entity.AppUser;
import com.sopnobazz.demo.comon.entity.CommonSetupDetails;
import com.sopnobazz.demo.comon.entity.PasswordHistory;
import com.sopnobazz.demo.comon.entity.PasswordPolicy;
import com.sopnobazz.demo.comon.entity.UserBalance;
import com.sopnobazz.demo.comon.entity.UserRole;
import com.sopnobazz.demo.comon.entity.UserRoleAssign;
import com.sopnobazz.demo.comon.entity.UserRoleAssignMaster;
import com.sopnobazz.demo.comon.repository.PasswordHistoryRepository;
import com.sopnobazz.demo.comon.service.AppUserService;
import com.sopnobazz.demo.comon.service.UserBalanceService;
import com.sopnobazz.demo.sysadmin.auth.request.DoctorSignupRequest;
import com.sopnobazz.demo.sysadmin.doctor.Doctor;
import com.sopnobazz.demo.sysadmin.doctor.DoctorService;
import com.sopnobazz.demo.sysadmin.service.UserRoleAssignService;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */


@Service
public class AuthService {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private UserBalanceService balanceService;

    @Autowired
    private UserRoleAssignService userRoleAssignService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private CommonUtils commonUtils;

    @Autowired
    private PasswordHistoryRepository historyRepository;

    public AppUser doctorSignup(DoctorSignupRequest request) throws Exception {

        if (request.getAgreements() == null || !request.getAgreements())
            throw new Exception("You must have to accept agreements");

        PasswordPolicy passwordPolicy = new PasswordPolicy();
        passwordPolicy.setId(2);

        AppUser appUser = new AppUser();

        appUser.setEmail(request.getEmail());
        appUser.setPassword(request.getPassword());
        appUser.setMobile(request.getMobile());
        appUser.setUsername(request.getEmail());
        appUser.setEntryUser(28);

        AppUser saveEntity = appUserService.saveUser(appUser);

        PasswordHistory history = new PasswordHistory();
        history.setAppUser(saveEntity);
        history.setPassword(request.getPassword());
        history.setEntryUser(saveEntity.getId());
        commonUtils.setEntryUserInfo(history);
        historyRepository.save(history);


        CommonSetupDetails currency = new CommonSetupDetails();
        currency.setId(1);

        UserBalance balance = new UserBalance();
        balance.setAppUser(saveEntity);
        balance.setCurrency(currency);
        balance.setPoint(0);
        balance.setMoney(0.00);
        balance.setEntryUser(saveEntity.getId());
        balanceService.save(balance);


        // doctor role assign

        UserRoleAssignMaster master = new UserRoleAssignMaster();
        master.setActive(true);
        master.setAppUser(saveEntity);

        UserRole userRole = new UserRole();
        userRole.setId(48);

        UserRoleAssign roleAssign = new UserRoleAssign();
        roleAssign.setActive(true);
        roleAssign.setUserRole(userRole);
        roleAssign.setAppUser(saveEntity);

        List<UserRoleAssign> detailsList = new ArrayList<>();
        detailsList.add(roleAssign);

        UserRoleAssignDto dto = new UserRoleAssignDto();
        dto.setMaster(master);
        dto.setDetailsList(detailsList);

        userRoleAssignService.save(dto, saveEntity.getId());

        Doctor doctor = new Doctor();
        doctor.setAgreements(request.getAgreements());
        doctor.setActive(true);
        doctor.setAppUser(saveEntity);
        doctor.setHospital(request.getHospital());
        doctor.setEntryUser(saveEntity.getId());

        doctorService.save(doctor);

        return saveEntity;
    }

}
