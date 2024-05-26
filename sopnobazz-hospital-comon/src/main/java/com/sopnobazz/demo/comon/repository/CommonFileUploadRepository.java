/**
 *
 */
package com.sopnobazz.demo.comon.repository;

import java.util.List;

import com.sopnobazz.demo.comon.entity.CommonFileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 * @Since Jul 29, 2021
 * @version 1.0.0
 */


@Repository
public interface CommonFileUploadRepository extends JpaRepository<CommonFileUpload, Integer> {

    List<CommonFileUpload> findByActive(boolean active);

    List<CommonFileUpload> findByTableNameAndTableTransectionId(String tableName, Integer id);
}
