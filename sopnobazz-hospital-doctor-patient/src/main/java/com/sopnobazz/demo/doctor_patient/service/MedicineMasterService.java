package com.sopnobazz.demo.doctor_patient.service;


import com.sopnobazz.demo.doctor_patient.entity.MedicineMaster;
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
    

    /*BUSINESS*/
    List<MedicineMaster> getBySearchValue(String searchValue);
    
    MedicineMaster getById(Integer id);

}
