package com.sopnobazz.demo.sysadmin.service;


import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.entity.MenuItemUrl;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.sysadmin.repository.MenuItemUrlRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Service
@AllArgsConstructor
public class MenuItemUrlServiceImpl implements MenuItemUrlService {

    private final MenuItemUrlRepository repo;
    private final CommonUtils commonUtils;

    @Override
    public MenuItemUrl save(MenuItemUrl entity) {
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Override
    public MenuItemUrl update(MenuItemUrl entity) {
        MenuItemUrl dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public MenuItemUrl delete(MenuItemUrl entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<MenuItemUrl> getAll() {
        return repo.findAll();
    }

    @Override
    public List<MenuItemUrl> getAllActive() {
        return repo.findByActive(true);
    }


    @Override
    public Page<MenuItemUrl> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<MenuItemUrl> pageresult = repo.findAll(pageRequest);
        List<MenuItemUrl> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }


}
