package com.sopnobazz.demo.sysadmin.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.sopnobazz.demo.comon.entity.AppUser;
import com.sopnobazz.demo.comon.entity.PasswordHistory;
import com.sopnobazz.demo.comon.entity.UserRoleAssign;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.sysadmin.auth.request.DoctorSignupRequest;
import com.sopnobazz.demo.sysadmin.auth.request.LoginRequest;
import com.sopnobazz.demo.sysadmin.auth.service.AuthService;
import com.sopnobazz.demo.sysadmin.auth.utils.AuthTokenUtils;
import com.sopnobazz.demo.sysadmin.auth.utils.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sopnobazz.demo.comon.repository.AppUserLogRepository;
import com.sopnobazz.demo.comon.repository.AppUserRepository;
import com.sopnobazz.demo.comon.repository.PasswordHistoryRepository;
import com.sopnobazz.demo.comon.repository.UserRoleAssignRepository;
import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.sysadmin.auth.response.LoginResponse;

import lombok.AllArgsConstructor;

import static com.sopnobazz.demo.comon.constants.ApiConstants.*;

import java.util.List;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */


@AllArgsConstructor
@RestController
@RequestMapping(AUTH_END_POINT)
public class AuthController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final UserRoleAssignRepository userRoleAssignRepository;
    private final PasswordHistoryRepository historyRepository;
    private final AppUserRepository appUserRepository;
    private final AppUserLogRepository appUserLogRepo;

    private final AuthTokenUtils authTokenUtils;
    private final CommonUtils commonUtils;
    private PasswordEncoder encoder;

    @Autowired
    private AuthService authService;

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = authTokenUtils.generateJwtToken(authentication);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            /* get user role */
            List<UserRoleAssign> userRoles = userRoleAssignRepository.findByAppUserId(userDetails.getId());

            /* get password policy */
            AppUser appUser = appUserRepository.findById(userDetails.getId()).get();
            PasswordHistory passwordHistory = historyRepository.getMaxEntryDateByUserId(userDetails.getId());

            /* add login log */

            /*AppUserLog appUserLog = new AppUserLog();
            appUserLog.setAppUser(appUser.getId());
            AppUserEmployee appUserEmp = appUserEmpRepo.findByAppUserIdAndActive(appUser.getId(), true);
            appUserLog.setAppUserEmp(appUserEmp == null ? 0 : appUserEmp.getId());
            appUserLog.setLoginDate(new Date());
            appUserLogRepo.save(appUserLog);*/

            /* create response */
            CommonResponse res = new CommonResponse();
            res.setStatus(true);
            res.setData(new LoginResponse(jwtToken, userDetails.getId(), userRoles,passwordHistory));
            return ResponseEntity.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            CommonResponse res = new CommonResponse();
            res.setStatus(false);
            res.setMessage("Wrong username or password");
            return ResponseEntity.ok(res);
        }


    }

    @PostMapping("reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody PasswordHistory history, HttpServletRequest request) {
        System.out.println("hit method");
        try {
            CommonResponse res = new CommonResponse();

            Integer userId = authTokenUtils.getUserIdFromRequest(request);

            AppUser appUser = history.getAppUser();
            appUser.setUpdateUser(userId);
            appUser.setPassword(encoder.encode(history.getPassword()));
            AppUser dbEntity = appUserRepository.findById(appUser.getId()).get();
            commonUtils.setUpdateUserInfo(appUser, dbEntity);

            AppUser savedEntity = appUserRepository.save(appUser);
            if (!ObjectUtils.isEmpty(savedEntity)) {
                history.setId(null);
                history.setEntryUser(userId);
                commonUtils.setEntryUserInfo(history);
                historyRepository.save(history);
                res.setStatus(true);
            } else {
                res.setStatus(false);
                res.setMessage("unable to save data");
            }

            return ResponseEntity.ok(res);

        } catch (Exception e) {
            CommonResponse res = new CommonResponse();
            res.setStatus(false);
            res.setMessage("Server Error");
            return ResponseEntity.ok(res);
        }


    }

    @PostMapping("doctor-signup")
    public ResponseEntity<?> doctorSignup(@Valid @RequestBody DoctorSignupRequest request) {

        try {
            /* create response */
            CommonResponse res = new CommonResponse();
            res.setStatus(true);
            res.setData(authService.doctorSignup(request));
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            CommonResponse res = new CommonResponse();
            res.setStatus(false);
            res.setMessage("Something is wrong");
            return ResponseEntity.ok(res);
        }
    }
}
