package com.sopnobazz.demo.sysadmin.service;

import java.util.List;

import com.sopnobazz.demo.comon.entity.MenuItem;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface MenuItemService {

    //CRUD function
    MenuItem save(MenuItem MenuItem);

    MenuItem update(MenuItem MenuItem);

    MenuItem delete(MenuItem MenuItem);

    List<MenuItem> getAll();

    List<MenuItem> getAllActive();

    Page<MenuItem> getPageableList(int page, int size);


    //business function
    List<MenuItem> getByMenuType(int id);

    List<MenuItem> getByMenuTypeNot(int id);

}
