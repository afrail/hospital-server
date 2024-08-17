package com.sopnobazz.demo.comon.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version 1.0.0
 * @Since May 20, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "COMMON_SETUP_DETAILS")
public class CommonSetupDetails extends CommonEntityField {

    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", length = 200, nullable = false)
    private String name;

    @Column(name = "BANGLA_NAME", length = 200, nullable = false)
    private String banglaName;

    @ManyToOne
    @JoinColumn(name = "MASTER_ID", nullable = false)
    private CommonSetupMaster master;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private CommonSetupDetails parent;

    @Column(name = "SHORT_CODE", length = 30)
    private String shortCode;

//	    @OneToMany(mappedBy="parent")
//	    @JsonIgnore
//	    private Collection<CommonLookupDetails> children;

}
