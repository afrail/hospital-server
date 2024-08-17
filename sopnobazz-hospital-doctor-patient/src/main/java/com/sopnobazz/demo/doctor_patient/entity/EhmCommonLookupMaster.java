package com.sopnobazz.demo.doctor_patient.entity;

import com.sopnobazz.demo.comon.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @Since Dec 07, 2021
 * @Author Mohaiminul Haq - 582
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EHM_COMMON_LOOKUP_MASTER")
public class EhmCommonLookupMaster extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "NAME", length=200, nullable = false, unique = true)
	private String name;
	
	@Column(name = "BANGLA_NAME", length=200, nullable = false, unique = true)
	private String banglaName;

	@Column(name = "PARENT_ID")
    private Integer parent;

}
