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
@Table(name = "EHM_COMMON_LOOKUP_DETAILS")
public class EhmCommonLookupDetails extends BaseEntity {

	private static final long serialVersionUID = 1L;

		@Column(name = "NAME", length=200, nullable = false)
		private String name;
		
		@Column(name = "BANGLA_NAME", length=200, nullable = false)
		private String banglaName;
		
		@ManyToOne
		@JoinColumn(name = "MASTER_ID", nullable = false)
		private EhmCommonLookupMaster master;

		@Column(name = "PARENT_ID")
	    private Integer parent;
		
		@Column(name = "SHORT_CODE", length=30)
		private String shortCode;


}
