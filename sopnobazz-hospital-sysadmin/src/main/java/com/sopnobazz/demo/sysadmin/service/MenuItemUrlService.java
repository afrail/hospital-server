package com.sopnobazz.demo.sysadmin.service;

import java.util.List;

import com.sopnobazz.demo.comon.entity.MenuItemUrl;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface MenuItemUrlService {

    //CRUD function
    MenuItemUrl save(MenuItemUrl MenuItemUrl);

    MenuItemUrl update(MenuItemUrl MenuItemUrl);

    MenuItemUrl delete(MenuItemUrl MenuItemUrl);

    List<MenuItemUrl> getAll();

    List<MenuItemUrl> getAllActive();

    Page<MenuItemUrl> getPageableList(int page, int size);


    //business function
}
