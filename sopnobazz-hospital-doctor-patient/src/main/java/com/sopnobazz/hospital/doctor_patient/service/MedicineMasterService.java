package com.sopnobazz.hospital.doctor_patient.service;


import com.sopnobazz.hospital.doctor_patient.entity.MedicineMaster;
import org.springframework.data.domain.Page;
import java.util.List;



public interface MedicineMasterService {

    /*CURD*/
    MedicineMaster save(MedicineMaster medicineMaster);

    MedicineMaster update(MedicineMaster medicineMaster);

    MedicineMaster delete(MedicineMaster medicineMaster);

    List<MedicineMaster> getAll();

    List<MedicineMaster> getAllActive();

    Page<MedicineMaster> getPageableList(int page, int size);
    
    Page<MedicineMaster> getPageableListByHosType(int hosType, int page, int size);
    

    /*BUSINESS*/
    List<MedicineMaster> getBySearchValue(String searchValue);
    
    MedicineMaster getById(Integer id);
    
    List<MedicineMaster> getByHosType(Integer hosType);
    
    List<MedicineMaster> getAvaileItemByHosType(Integer hosType);
}
