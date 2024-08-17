package com.sopnobazz.demo.comon.service;

import com.sopnobazz.demo.comon.entity.NoticeUpload;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @version 2.0.0
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @Since Nov 14, 2021
 */
public interface NoticeUploadService {

    //CRUD
    NoticeUpload save(MultipartFile file, NoticeUpload obj) throws IOException;

    NoticeUpload update(MultipartFile file, NoticeUpload obj) throws IOException;

    NoticeUpload delete(NoticeUpload obj) throws IOException;

    List<NoticeUpload> getAll();

    List<NoticeUpload> getAllActive();

    Page<NoticeUpload> getPageableList(int page, int size);

}
