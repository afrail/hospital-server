package com.sopnobazz.demo.comon.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * @version 2.0.0
 * @Since November 17, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "COMMON_NOTICE_UPLOAD")
public class NoticeUpload extends BaseEntity {

    private static final long serialVersionUID = 3216408187347048010L;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "PUBLISH_DATE")
    private Date publishDate;

    @Column(name = "FILE_NAME", unique = true)
    private String fileName;

    @Column(name = "FILE_LOCATION")
    private String fileLocation;


}
