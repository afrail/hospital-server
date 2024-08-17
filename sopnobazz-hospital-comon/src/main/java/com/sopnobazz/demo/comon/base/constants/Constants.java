package com.sopnobazz.demo.comon.base.constants;


/**
 * @version 1.0.0
 * @Project demo-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 23, 2022
 */


public interface Constants {
    String PRODUCT_TYPE_PHYSICAL = "Physical";
    String PRODUCT_TYPE_VIRTUAL = "Virtual";
    String PRODUCT_TYPE_DOWNLOADABLE = "Downloadable";
    String PRODUCT_TYPE_CUSTOM = "Custom";


    // used in product-service, store-service
    String SAVE_SUCCESS = "Successfully Saved";
    String SAVE_FAILED = "Failed to save data.";
    String UPDATE_SUCCESS = "Successfully Updated";
    String UPDATE_FAILED = "Failed to update data";
    String DELETE_SUCCESS = "Successfully Deleted";
    String DELETE_FAILED = "Failed to deleted data.";
    String ADD_SUCCESS = "Successfully Added";
    String REMOVE_SUCCESS = "Successfully Removed";
    String UPLOAD_SUCCESS = "Successfully Uploaded";
    String UPLOAD_FAILED = "Failed to upload file";
    String INCREASE_SUCCESS = "Successfully Increased";
    String DECREASE_SUCCESS = "Successfully Decreased";
    String FETCH_SUCCESS = "Successfully Fetch Data";
    String FETCH_FAILED = "Failed to fetch data.";

    String RETURN_SUCCESS = "Returned successfully";
    String RETURN_FAILED = "Return failed";

    String AVAILABLE = "Available.";
    String NOT_AVAILABLE = "Not available.";
    String ALREADY_EXIST = "Already exist.";


    String DATA_ALRADY_EXISTS_MESSAGE = "Data already exists!!";
    String CHILD_RECORD_FOUND = "Child record found !!";
    String PROCESS_COMPLETE = "Process successfully completed !!";


    String DELIVERY_ADDRESS = "delivery";
    String BILLING_ADDRESS = "billing";
}
