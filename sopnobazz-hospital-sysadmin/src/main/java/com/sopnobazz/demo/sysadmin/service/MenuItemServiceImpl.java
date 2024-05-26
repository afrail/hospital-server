package com.sopnobazz.demo.sysadmin.service;


import java.util.List;
import java.util.stream.Collectors;

import com.sopnobazz.demo.comon.entity.MenuItem;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sopnobazz.demo.sysadmin.repository.MenuItemRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Service
@AllArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository repo;
    private final CommonUtils commonUtils;

    @Override
    public MenuItem save(MenuItem entity) {
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Override
    public MenuItem update(MenuItem entity) {
        MenuItem dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public MenuItem delete(MenuItem entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<MenuItem> getAll() {
        return repo.findAll();
    }

    @Override
    public List<MenuItem> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<MenuItem> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<MenuItem> pageresult = repo.findAll(pageRequest);
        List<MenuItem> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }

    @Override
    public List<MenuItem> getByMenuType(int id) {
        return repo.findByMenuTypeAndActive(id, true);
    }

    @Override
    public List<MenuItem> getByMenuTypeNot(int id) {
        return repo.findByMenuTypeNotAndActive(id, true);
    }

}
