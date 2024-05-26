package com.sopnobazz.demo.comon.service;

import org.springframework.data.domain.Page;

import java.util.List;

@SuppressWarnings("hiding")
public interface CommonCrudService<Object> {
    Object save(Object entity);

    Object update(Object entity);

    Object delete(Object entity);

    Object getById(int id);

    List<Object> getAll();

    List<Object> getAllActive();

    Page<Object> getPageableList(int page, int size);
}
