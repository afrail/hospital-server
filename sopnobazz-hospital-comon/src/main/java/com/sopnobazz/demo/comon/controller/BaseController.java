package com.sopnobazz.demo.comon.controller;


import com.sopnobazz.demo.comon.constants.ApiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Since May 20, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class BaseController implements ApiConstants {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);


    @GetMapping
    public String getAll() {

        return "API Application Running.................";

    }


}

