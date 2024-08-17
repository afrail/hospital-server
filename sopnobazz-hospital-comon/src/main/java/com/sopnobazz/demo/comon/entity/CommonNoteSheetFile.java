/**
 *
 */
package com.sopnobazz.demo.comon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "COMMON_NOTE_SHEET_FILE")
public class CommonNoteSheetFile extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @Column(name = "PATH", nullable = false)
    private String path;
}



























