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
 * @Project hospital-management
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "COMMON_SETUP_MASTER")
public class CommonSetupMaster extends CommonEntityField {

    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", length = 200, nullable = false, unique = true)
    private String name;

    @Column(name = "BANGLA_NAME", length = 200, nullable = false, unique = true)
    private String banglaName;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private CommonSetupMaster parent;
    /*
     * @OneToMany(mappedBy="parent")
     *
     * @JsonIgnore private Collection<CommonLookupMaster> children;
     */


}
