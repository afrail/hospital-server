package com.sopnobazz.demo.comon.controller;

import com.sopnobazz.demo.comon.constants.ApiConstants;
import com.sopnobazz.demo.comon.constants.MessageConstants;
import com.sopnobazz.demo.comon.entity.NoticeUpload;
import com.sopnobazz.demo.comon.response.CommonResponse;
import com.sopnobazz.demo.comon.service.NoticeUploadService;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.UserTokenRequestUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;

/**
 * @version 2.0.0
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @Since Nov 14, 2021
 */

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstants.COMMON_END_POINT + "notice-upload")
public class NoticeUploadController implements MessageConstants {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(NoticeUploadController.class);

    @Autowired
    private Environment env;
    private final ResourceLoader resourceLoader;
    private final NoticeUploadService service;

    /* utils */
    private final CommonUtils commonUtils;
    private final UserTokenRequestUtils authTokenUtils;

    @PostMapping
    public CommonResponse save(@RequestPart(value = "fileObject", required = true) NoticeUpload obj,
                               @RequestPart(value = "file", required = false) final MultipartFile file, HttpServletRequest request) {
        try {
            obj.setEntryUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.save(file, obj), SAVE_MESSAGE, SAVE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @PutMapping
    public CommonResponse update(@RequestPart(value = "fileObject", required = true) NoticeUpload obj,
                                 @RequestPart(value = "file", required = false) final MultipartFile file, HttpServletRequest request) {
        try {
            obj.setUpdateUser(authTokenUtils.getUserIdFromRequest(request));
            return commonUtils.generateSuccessResponse(service.update(file, obj), UPDATE_MESSAGE, UPDATE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @DeleteMapping
    public CommonResponse delete(@Valid @RequestBody NoticeUpload body) {
        try {
            return commonUtils.generateSuccessResponse(service.delete(body), DELETE_MESSAGE, DELETE_MESSAGE_BN);
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping
    public CommonResponse getAll() {
        try {
            return commonUtils.generateSuccessResponse(service.getAll());
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }


    @GetMapping(value = ApiConstants.ACTIVE_PATH, produces = ApiConstants.EXTERNAL_MEDIA_TYPE)
    public CommonResponse getAllActive() {
        try {
            return commonUtils.generateSuccessResponse(service.getAllActive());
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }

    @GetMapping(value = ApiConstants.PAGEABLE_PATH, produces = ApiConstants.EXTERNAL_MEDIA_TYPE)
    public CommonResponse getPagableList(@PathVariable(ApiConstants.PAGEABLE_PAGE) int page, @PathVariable(ApiConstants.PAGEABLE_SIZE) int size) {
        try {
            return commonUtils.generateSuccessResponse(service.getPageableList(page, size));
        } catch (Exception e) {
            return commonUtils.generateErrorResponse(e);
        }
    }


    @GetMapping("/show/{filename}")
    public ResponseEntity<?> showPhotos(@PathVariable("filename") String fileName) {
        Resource resource = resourceLoader.getResource("file:" + env.getProperty("drawing.file.upload.dir") + fileName);
        try {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e1) {
            e1.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable("filename") String fileName) {
        Resource resource = resourceLoader.getResource("file:" + env.getProperty("drawing.file.upload.dir") + fileName);
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
        Resource resource = resourceLoader.getResource("file:" + env.getProperty("drawing.file.upload.dir") + fileName);
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


    @GetMapping("/preview/{filename}")
    public ResponseEntity<?> previewFile(@PathVariable("filename") String fileName) {
        Resource resource = resourceLoader.getResource("file:" + env.getProperty("drawing.file.upload.dir") + fileName);
        // convert auto cad to pdf here
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


    @GetMapping(value = "/open-soft/{filename}")
    public CommonResponse openSoftware(@PathVariable("filename") String fileName) {
        Runtime rs = Runtime.getRuntime();
        try {
            rs.exec("java -jar /bof/data/jar/caffviewer.jar /bof/data/files/drawing/" + fileName);

        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

}
