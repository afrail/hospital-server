package com.sopnobazz.demo.comon.constants;

import org.springframework.http.MediaType;

/**
 * @version 1.0.0
 * @Since May 20, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */

public interface ApiConstants {

    String EXTERNAL_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;


    // base URL 
    String PRIVATE_BOF_ENDPOINT = "/api/";

    // model URL
    String AUTH_END_POINT = PRIVATE_BOF_ENDPOINT + "/auth/";
    String COMMON_END_POINT = PRIVATE_BOF_ENDPOINT + "/common/";
    String HRM_END_POINT = PRIVATE_BOF_ENDPOINT + "/hrm/";
    String ACCESS_END_POINT = PRIVATE_BOF_ENDPOINT + "/acc/";
    String REC_END_POINT = PRIVATE_BOF_ENDPOINT + "/rec/";
    String CLR_END_POINT = PRIVATE_BOF_ENDPOINT + "/clr/";
    String ICT_END_POINT = PRIVATE_BOF_ENDPOINT + "ict/";
    String SYSTEM_ADMIN_END_POINT = PRIVATE_BOF_ENDPOINT + "sya/";
    String FINANCE_END_POINT = PRIVATE_BOF_ENDPOINT + "fin/";
    String DRAWING_END_POINT = PRIVATE_BOF_ENDPOINT + "drw/";
    String RATION_AND_WELFARE_END_POINT = PRIVATE_BOF_ENDPOINT + "raw/";
    String BUDGET_END_POINT = PRIVATE_BOF_ENDPOINT + "budget/";
    String EHM_END_POINT = PRIVATE_BOF_ENDPOINT + "ehm/";


    // find active URL
    String ACTIVE_PATH_WITH_REQUEST = "active-with-request";
    String ACTIVE_PATH = "active";
    String ID_PATH = "/{id}";
    String ACTIVE_RECRUITMENT_REFERENCE = "active-recruitment-reference";
    String officer_PATH = "officer";
    String ITEM_STATUS = "itemStatus";
    String ITEM_STATUS_PATH = "item-status/{" + ITEM_STATUS + "}";

    // page able URL and variable
    String PAGEABLE_PAGE = "page";
    String PAGEABLE_SIZE = "size";
    String PAGEABLE_PATH = "pageable/{" + PAGEABLE_PAGE + "}/{" + PAGEABLE_SIZE + "}";
    String PAGEABLE_PATH_WITH_REQUEST = "pageable-with-request/{" + PAGEABLE_PAGE + "}/{" + PAGEABLE_SIZE + "}";
    String ACTIVE_PAGEABLE_PATH = "pageable/{" + PAGEABLE_PAGE + "}/{" + PAGEABLE_SIZE + "}/{" + officer_PATH + "}";
    //    String GET_KEY_INFO = "issue-key-list/{" + ACTIVE_PATH + "}";
    String GET_RESULT_INFO = "result-setup-info";

    // find by id
    String PATH_ID = "id";
    String GET_OBJECT_BY_ID = "get-by-id/{" + PATH_ID + "}";
    String GET_OBJECT_BY_ID_WITH_REQUEST = "get-by-id-with-request/{" + PATH_ID + "}";

    // find by code URL and variable
    String PATH_CODE = "code";
    String FIND_BY_CODE = "find-by-code/{" + PATH_CODE + "}";

    // find by master id URL and variable
    String MASTER_ID = "id";
    String MASTER_PATH = "master/{" + MASTER_ID + "}";
    String GET_BY_ID = "/{id}";


    // find by search value
    String SEARCH_VALUE = "searchValue";
    String SEARCH_VALUE_PATH = "get-by-search-value/{" + SEARCH_VALUE + "}";
    String SEARCH_VALUE_PATH_WITH_REQUEST = "get-by-search-value-with-request/{" + SEARCH_VALUE + "}";


    String PARENT_ID = "parentId";
    String MASTER_ID_AND_SEARCH_VALUE_PATH = "get-by-search-value/{" + MASTER_ID + "}/{" + SEARCH_VALUE + "}";
    String DEPENDENT_MASTER_ID_AND_SEARCH_VALUE_PATH = "get-by-search-value/{" + PARENT_ID + "}/{" + MASTER_ID + "}/{" + SEARCH_VALUE + "}";

    // Remarks Default Value

    int REMARKS_DEFAULT_VALUE = 250;


}
