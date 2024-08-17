package com.sopnobazz.demo.comon.service;

import java.util.List;

import com.sopnobazz.demo.comon.entity.CommonSetupDetails;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Since May 20, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */
public interface CommonLookupDetailsService {

    //CRUD
    CommonSetupDetails save(CommonSetupDetails obj);

    CommonSetupDetails update(CommonSetupDetails obj);

    CommonSetupDetails delete(CommonSetupDetails obj);

    List<CommonSetupDetails> getAll();

    List<CommonSetupDetails> getAllActive();

    Page<CommonSetupDetails> getPageableList(int page, int size);

    //Business
    List<CommonSetupDetails> getByMasterId(int id);

    public CommonSetupDetails getById(int id);

    List<CommonSetupDetails> getOfficeInfo();

    List<CommonSetupDetails> getByMasterIdAndSearchValue(Integer masterId, String searchValue);

    List<CommonSetupDetails> getByDependentMasterIdAndSearchValue(Integer parentId);
}
