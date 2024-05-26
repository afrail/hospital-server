package com.sopnobazz.demo.comon.controller;


import com.sopnobazz.demo.comon.constants.ApiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sopnobazz.demo.comon.constants.MessageConstants;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Since May 20, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */
@AllArgsConstructor
@RestController
@RequestMapping(ApiConstants.COMMON_END_POINT + "file")
public class FileDownloadController implements MessageConstants {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(FileDownloadController.class);

    private final ResourceLoader resourceLoader;
    @Autowired
    private Environment env;

    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable("filename") String fileName) {
        Resource resource = resourceLoader.getResource("file:" + env.getProperty("file.upload.dir") + fileName);
        try {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e1) {
            e1.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/print/{filename}")
    public ResponseEntity<?> printFile(@PathVariable("filename") String fileName) {
        Resource resource = resourceLoader.getResource("file:" + env.getProperty("file.upload.dir") + fileName);
        try {
            return ResponseEntity.ok()
                    .header("Content-Type", "application/pdf; charset=UTF-8")
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e1) {
            e1.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


}

