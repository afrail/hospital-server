package com.sopnobazz.demo.comon.service;

import com.sopnobazz.demo.comon.entity.NoticeUpload;
import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.comon.utils.NoticeUploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sopnobazz.demo.comon.repository.NoticeUploadRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @version 2.0.0
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 * @Since Nov 14, 2021
 */

@Service
@AllArgsConstructor
public class NoticeUploadServiceImpl implements NoticeUploadService {

    private final NoticeUploadRepository repo;
    private final NoticeUploadUtil fileUtils;
    private final CommonUtils commonUtils;

    @Override
    public NoticeUpload save(MultipartFile file, NoticeUpload entity) throws IOException {

        if (file != null) {
            //save image to server
            Map<String, String> image = fileUtils.noticeUploadFileToServer(file, entity.getFileName(), entity.getFileLocation());
            //set attachment object info
            entity.setFileName(image.get("fileName"));
            entity.setFileLocation(image.get("fileLocation"));
        }
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Override
    public NoticeUpload update(MultipartFile file, NoticeUpload entity) throws IOException {
        NoticeUpload dbEntity = repo.findById(entity.getId()).get();

        if (ObjectUtils.isEmpty(dbEntity)) {
            return null;
        }

        if (file != null) {
            if (!ObjectUtils.isEmpty(dbEntity.getFileName())) {
                Path path = Paths.get(dbEntity.getFileLocation() + dbEntity.getFileName());
                if (Files.exists(path)) {
                    Files.delete(path);
                }
            }

            //save image to server
            Map<String, String> image = fileUtils.noticeUploadFileToServer(file, entity.getFileName(), entity.getFileLocation());

            //set attachment object info
            entity.setFileName(image.get("fileName"));
            entity.setFileLocation(image.get("fileLocation"));
        } else {
            if (!ObjectUtils.isEmpty(dbEntity.getFileName())) {
                entity.setFileName(dbEntity.getFileName());
                entity.setFileLocation(dbEntity.getFileLocation());
            }
        }
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public NoticeUpload delete(NoticeUpload entity) throws IOException {
        NoticeUpload dbEntity = repo.findById(entity.getId()).get();
        if (ObjectUtils.isEmpty(dbEntity)) {
            return null;
        }

        repo.delete(entity);
        if (!ObjectUtils.isEmpty(dbEntity.getFileName())) {
            Path path = Paths.get(dbEntity.getFileLocation() + dbEntity.getFileName());
            if (Files.exists(path)) {
                Files.delete(path);
            }
        }

        return entity;
    }

    @Override
    public List<NoticeUpload> getAll() {
        return repo.findAll();
    }

    @Override
    public List<NoticeUpload> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<NoticeUpload> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<NoticeUpload> pageresult = repo.findAll(pageRequest);
        List<NoticeUpload> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }


}
