/**
 *
 */
package com.sopnobazz.demo.comon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @Since Jul 29, 2021
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "COMMON_FILE_UPLOAD")
public class CommonFileUpload extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @Column(name = "FILE_LOCATION", nullable = false)
    private String fileLocation;

    @Column(name = "FILE_TITLE", nullable = false)
    private String fileTitle;

    @Column(name = "TABLE_NAME", nullable = false)
    private String tableName;

    @Column(name = "TABLE_TRANSECTION_ID", nullable = false)
    private Integer tableTransectionId;

    @Column(name = "MODULE_ID", nullable = false)
    private String moduleId;

    @Column(name = "SERIAL_NO", nullable = false)
    private String serialNo;

    @Column(name = "IS_IMAGE", columnDefinition = "boolean default false")
    private Boolean isImage = false;

    @Column(name = "IS_SIGNATURE", columnDefinition = "boolean default false")
    private Boolean isSignature = false;
}






















