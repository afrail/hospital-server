package com.sopnobazz.demo.comon.base.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;


/**
 * @version 1.0.0
 * @Project hospital-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 23, 2022
 */


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaModel {
    private Integer offset;
    private Integer prevOffset;
    private Integer nextOffset;
    private Integer limit;
    private Long totalRecords;
    private Integer resultCount;
    private Integer totalPageCount;
    private List<SortModel> sort;
}
