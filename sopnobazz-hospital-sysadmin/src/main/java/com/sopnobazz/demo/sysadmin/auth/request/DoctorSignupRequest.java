package com.sopnobazz.demo.sysadmin.auth.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0.0
 * @Project demo-management
 * @Author Md. Nayeemul Islam
 * @Since May 28, 2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSignupRequest {

    public String userId;
    public String username;
    public String email;
    public String password;
    public String hospital;
    public String mobile;

    private Boolean agreements;

}
