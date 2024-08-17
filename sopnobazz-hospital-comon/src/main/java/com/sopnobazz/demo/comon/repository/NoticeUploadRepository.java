package com.sopnobazz.demo.comon.repository;

import com.sopnobazz.demo.comon.entity.NoticeUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @version 2.0.0
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @Since Nov 14, 2021
 */
public interface NoticeUploadRepository extends JpaRepository<NoticeUpload, Integer> {

    List<NoticeUpload> findByActive(boolean active);


}
