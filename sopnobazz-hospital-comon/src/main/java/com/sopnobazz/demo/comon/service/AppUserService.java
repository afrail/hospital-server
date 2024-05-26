/**
 *
 */
package com.sopnobazz.demo.comon.service;

import java.util.List;

import com.sopnobazz.demo.comon.dto.PasswordChangeDto;
import com.sopnobazz.demo.comon.entity.AppUser;
import org.springframework.data.domain.Page;

/**
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 * @Since Jun 9, 2021
 * @version 1.0.0
 */
public interface AppUserService {

    //CRUD
    public AppUser save(AppUser obj);

    public AppUser saveUser(AppUser entity);

    public AppUser update(AppUser obj);

    public AppUser delete(AppUser obj);

    public List<AppUser> getAll();

    public List<AppUser> getAllActive();

    public Page<AppUser> getPageableList(int page, int size);

    public AppUser getProfile(Integer id) throws Exception;

    public String changePassword(PasswordChangeDto dto, Integer id) throws Exception;

}
