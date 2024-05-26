package com.sopnobazz.demo.comon.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class CommonDespatchRegisterySearchParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fromDate;
    private Date toDate;
    private Integer moduleId;
}
