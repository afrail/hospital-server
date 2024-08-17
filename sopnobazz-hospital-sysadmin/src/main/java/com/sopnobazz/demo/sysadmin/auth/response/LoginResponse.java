package com.sopnobazz.demo.sysadmin.auth.response;

import java.util.List;

import com.sopnobazz.demo.comon.entity.PasswordHistory;
import com.sopnobazz.demo.comon.entity.PasswordPolicy;
import com.sopnobazz.demo.comon.entity.UserRoleAssign;
import com.sopnobazz.demo.comon.repository.ApprovalTeamDetailsRepository.ApprovalUser;

import lombok.Data;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */


@Data
public class LoginResponse {

    public String token;
    public Integer id;
    public List<UserRoleAssign> userRoles;
    public PasswordHistory passwordHistory;
    PasswordPolicy passwordPolicy;
    List<ApprovalUser> approvalUser;

    public LoginResponse(String token,
                         Integer id,
                         List<UserRoleAssign> userRoles,
                         PasswordHistory passwordHistory) {
        super();
        this.token = token;
        this.id = id;
        this.userRoles = userRoles;
        this.passwordHistory = passwordHistory;
    }


}
