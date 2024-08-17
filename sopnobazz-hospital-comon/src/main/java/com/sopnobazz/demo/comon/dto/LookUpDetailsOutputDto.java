/**
 *
 */
package com.sopnobazz.demo.comon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project bof-erp
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @version 1.0.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LookUpDetailsOutputDto {

    private Integer id;

    private String code;

    private String name;

    private String banglaName;

    private Integer masterId;

    private String masterName;

    private Integer parentId;

    private String parentName;

}
