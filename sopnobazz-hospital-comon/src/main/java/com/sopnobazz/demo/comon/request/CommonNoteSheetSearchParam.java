package com.sopnobazz.demo.comon.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class CommonNoteSheetSearchParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fromDate;
    private Date toDate;
    private String approvalStatus;
    private String refNo;
    private Integer entryUser;
    private List<Integer> moduleIds;
}
