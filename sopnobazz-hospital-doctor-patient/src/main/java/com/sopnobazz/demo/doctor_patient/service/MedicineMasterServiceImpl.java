package com.sopnobazz.demo.doctor_patient.service;


import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.doctor_patient.entity.MedicineMaster;
import com.sopnobazz.demo.doctor_patient.repository.MedicineMasterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;



@Service
@AllArgsConstructor
public class MedicineMasterServiceImpl implements MedicineMasterService {
    private MedicineMasterRepository repo;
    private final CommonUtils commonUtils;

    @Override
    public MedicineMaster save(MedicineMaster entity) {
        commonUtils.setEntryUserInfo(entity);
        return repo.save(entity);
    }

    @Override
    public MedicineMaster update(MedicineMaster entity) {
        MedicineMaster dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }

    @Override
    public MedicineMaster delete(MedicineMaster entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<MedicineMaster> getAll() {
        return repo.findAll();
    }

    @Override
    public List<MedicineMaster> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<MedicineMaster> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<MedicineMaster> pageResult = repo.findAll(pageRequest);
        List<MedicineMaster> objList = pageResult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objList, pageRequest, pageResult.getTotalElements());
    }

    @Override
    public List<MedicineMaster> getBySearchValue(String searchValue) {
        return repo.findBySearchValue(searchValue);
    }

	@Override
	public MedicineMaster getById(Integer id) {
		return repo.findById(id).get();
	}

}
