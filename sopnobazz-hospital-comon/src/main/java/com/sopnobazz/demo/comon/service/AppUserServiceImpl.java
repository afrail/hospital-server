/**
 *
 */
package com.sopnobazz.demo.comon.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.dto.PasswordChangeDto;
import com.sopnobazz.demo.comon.entity.AppUser;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.comon.repository.AppUserRepository;

import lombok.AllArgsConstructor;

/**
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 * @Since Jun 9, 2021
 * @version 1.0.0
 */

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(AppUserServiceImpl.class);

    private AppUserRepository repo;
    private PasswordEncoder encoder;
    private final CommonUtils commonUtils;

    @Override
    public AppUser save(AppUser entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        commonUtils.setEntryUserInfo(entity);
        AppUser savedEntity = repo.save(entity);


        return savedEntity;
    }

    @Override
    public AppUser saveUser(AppUser entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        commonUtils.setEntryUserInfo(entity);
        AppUser savedEntity = repo.save(entity);

        return savedEntity;
    }

    @Override
    public AppUser update(AppUser entity) {
//    	entity.setPassword(encoder.encode(entity.getPassword()));
        AppUser dbEntity = repo.findById(entity.getId()).get();

        commonUtils.setUpdateUserInfo(entity, dbEntity);
        commonUtils.copyNonNullProperties(entity, dbEntity);
        return repo.save(dbEntity);
    }

    @Override
    public AppUser delete(AppUser entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<AppUser> getAll() {
        return repo.findAll();
    }

    @Override
    public List<AppUser> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<AppUser> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<AppUser> pageresult = repo.findAll(pageRequest);
        List<AppUser> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }

    public AppUser getProfile(Integer id) throws Exception {
        Optional<AppUser> optional = repo.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            throw new Exception("User Not Found");
    }

    public String changePassword(PasswordChangeDto dto, Integer id) throws Exception {
        Optional<AppUser> optional = repo.findById(id);
        if (optional.isPresent()) {
            if (!commonUtils.noData(dto) && !commonUtils.noData(dto.getPassword()) && !commonUtils.noData(dto.getConfirmPassword())) {
                if (dto.getPassword().equals(dto.getConfirmPassword())) {
                    AppUser appUser = optional.get();
                    appUser.setPassword(encoder.encode(dto.getPassword()));
                    repo.save(appUser);
                    return "Save successfully";
                } else
                    throw new Exception("Password doesn't match");
            } else
                throw new Exception("Invalid Data");
        } else
            throw new Exception("User Not Found");
    }

}
