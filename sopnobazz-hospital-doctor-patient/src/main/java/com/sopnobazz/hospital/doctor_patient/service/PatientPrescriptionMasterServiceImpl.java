package com.sopnobazz.hospital.doctor_patient.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.hospital.doctor_patient.entity.*;
import com.sopnobazz.hospital.doctor_patient.repository.*;
import com.sopnobazz.hospital.doctor_patient.request.PatientPrescriptionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import com.ibcsprimax.bof.employee_health.request.PatientPrescriptionSearchParam;
import com.ibcsprimax.bof.employee_health.utils.EHMConstant;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class PatientPrescriptionMasterServiceImpl implements PatientPrescriptionMasterService{
	
	  private PatientPrescriptionMasterRepository masterRepository;
	  private PatientPrescriptionChiefComplaintRepository ccRepo;
	  private PatientPrescriptionDiseaseRepository diseaseRepo;
	  private PatientPrescriptionDisposalRepository disposalRepo;
	  private PatientPrescriptionInvestigationRepository investigationRepo;
	  private PatientPrescriptionInvestigationFindingRepository investigationFindingRepo;
	  private PatientPrescriptionTreatmentRepository treatmentRepo;
	  private PatientPrescriptionAdviceRepository adviceRepo;
	  private TokenRegisterRepository tokenRegisterRepo;
	  private OnExaminationRepository onExaminationRepo;

	  private final CommonUtils commonUtils;

	@Override
	public PatientPrescriptionRequest save(PatientPrescriptionRequest body, int userId) {
		
		PatientPrescriptionMaster entity = body.getMaster();
		List<PatientIllnessHistory> pastIllList = body.getPastIllHistoryList();
        List<PatientPrescriptionChiefComplaint> ccList = body.getCcList();
        List<PatientPrescriptionInvestigationFinding> investigationFindingList = body.getInvestigationFindingList();
        List<PatientPrescriptionDisease> diseaseList = body.getDiseaseList();
        List<PatientPrescriptionInvestigation> investigationList = body.getInvestigationList();
        List<PatientPrescriptionTreatment> treatmentList = body.getTreatmentList();
        List<PatientPrescriptionAdvice> adviceList = body.getAdviceList();
        List<PatientPrescriptionDisposal> disposalList = body.getDisposalList();
        List<OnExamination> onExaminationList= body.getOnExaminationList();

        /*save master*/
        entity.setEntryUser(userId);
        if (!entity.getTemplateIs()) {
        	entity.setPrescriptionNo(generateNumber(entity.getPrescriptionDate()));
        }
        commonUtils.setEntryUserInfo(entity);
        PatientPrescriptionMaster saveEntity = masterRepository.save(entity);
        

        /*save cc*/
        if(!ObjectUtils.isEmpty(saveEntity) && ccList != null && ccList.size() > 0){
        	List<PatientPrescriptionChiefComplaint> listForSave = new ArrayList<>();
            for(PatientPrescriptionChiefComplaint obj: ccList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            ccRepo.saveAll(listForSave);
        }

        /*save investigation finding*/
        if(!ObjectUtils.isEmpty(saveEntity) && investigationFindingList != null && investigationFindingList.size() > 0){
        	List<PatientPrescriptionInvestigationFinding> listForSave = new ArrayList<>();
            for(PatientPrescriptionInvestigationFinding obj: investigationFindingList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            investigationFindingRepo.saveAll(listForSave);
        }
        
        /*save disease*/
        if(!ObjectUtils.isEmpty(saveEntity) && diseaseList != null && diseaseList.size() > 0){
        	List<PatientPrescriptionDisease> listForSave = new ArrayList<>();
            for(PatientPrescriptionDisease obj: diseaseList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            diseaseRepo.saveAll(listForSave);
        }
        
        /*save investigation*/
        if(!ObjectUtils.isEmpty(saveEntity) && investigationList != null && investigationList.size() > 0){
        	List<PatientPrescriptionInvestigation> listForSave = new ArrayList<>();
            for(PatientPrescriptionInvestigation obj: investigationList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            investigationRepo.saveAll(listForSave);
        }
        
        /*save treatment*/
        if(!ObjectUtils.isEmpty(saveEntity) && treatmentList != null && treatmentList.size() > 0){
        	List<PatientPrescriptionTreatment> listForSave = new ArrayList<>();
            for(PatientPrescriptionTreatment obj: treatmentList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            treatmentRepo.saveAll(listForSave);
        }

        
        /*save advice*/
        if(!ObjectUtils.isEmpty(saveEntity) && adviceList != null && adviceList.size() > 0){
        	List<PatientPrescriptionAdvice> listForSave = new ArrayList<>();
            for(PatientPrescriptionAdvice obj: adviceList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            adviceRepo.saveAll(listForSave);
        }

        
        /*save disposal*/
        if(!ObjectUtils.isEmpty(saveEntity) && disposalList != null && disposalList.size() > 0){
        	List<PatientPrescriptionDisposal> listForSave = new ArrayList<>();
            for(PatientPrescriptionDisposal obj: disposalList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            disposalRepo.saveAll(listForSave);
        }

        /*save On*/
        if(!ObjectUtils.isEmpty(saveEntity) && onExaminationList.size() > 0){
        	List<OnExamination> listForSave = new ArrayList<>();
            for(OnExamination obj: onExaminationList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            onExaminationRepo.saveAll(listForSave);
        }
        
        if(!ObjectUtils.isEmpty(saveEntity) && !entity.getTemplateIs()){
        	/* update token register */
            TokenRegister tokenRegister = tokenRegisterRepo.findById(saveEntity.getTokenRegisterId()).get();
            tokenRegister.setActionToken(true);
            tokenRegisterRepo.save(tokenRegister);
        }
        
        return body;
	}

	@Override
    @Transactional
	public PatientPrescriptionRequest update(PatientPrescriptionRequest body, int userId) {
		
		PatientPrescriptionMaster entity = body.getMaster();
        List<PatientPrescriptionChiefComplaint> ccList = body.getCcList();
        List<PatientIllnessHistory> pastIllList = body.getPastIllHistoryList();
        List<PatientPrescriptionDisease> diseaseList = body.getDiseaseList();
        List<PatientPrescriptionInvestigation> investigationList = body.getInvestigationList();
        List<PatientPrescriptionInvestigationFinding> investigationFindingList = body.getInvestigationFindingList();
        List<PatientPrescriptionTreatment> treatmentList = body.getTreatmentList();
        List<PatientPrescriptionAdvice> adviceList = body.getAdviceList();
        List<PatientPrescriptionDisposal> disposalList = body.getDisposalList();
        List<OnExamination> onExaminationList = body.getOnExaminationList();

        
        /*update master*/
        entity.setUpdateUser(userId);
        PatientPrescriptionMaster dbEntity = masterRepository.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        PatientPrescriptionMaster saveEntity = masterRepository.save(entity);

		
        /*update cc*/
        ccRepo.deleteByPrescriptionMasterId(saveEntity.getId());
        if(!ObjectUtils.isEmpty(saveEntity) && ccList.size() > 0){
        	List<PatientPrescriptionChiefComplaint> listForSave = new ArrayList<>();
            for(PatientPrescriptionChiefComplaint obj: ccList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            ccRepo.saveAll(listForSave);
        }

        /*save investigation finding*/
        investigationFindingRepo.deleteByPrescriptionMasterId(saveEntity.getId());
        if(!ObjectUtils.isEmpty(saveEntity) && investigationFindingList.size() > 0){
        	List<PatientPrescriptionInvestigationFinding> listForSave = new ArrayList<>();
            for(PatientPrescriptionInvestigationFinding obj: investigationFindingList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            investigationFindingRepo.saveAll(listForSave);
        }
        
        
        
        /*update disease*/
        diseaseRepo.deleteByPrescriptionMasterId(saveEntity.getId());
        if(!ObjectUtils.isEmpty(saveEntity) && diseaseList.size() > 0){
        	List<PatientPrescriptionDisease> listForSave = new ArrayList<>();
            for(PatientPrescriptionDisease obj: diseaseList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            diseaseRepo.saveAll(listForSave);
        }
        
        /*save investigation*/
        investigationRepo.deleteByPrescriptionMasterId(saveEntity.getId());
        if(!ObjectUtils.isEmpty(saveEntity) && investigationList.size() > 0){
        	List<PatientPrescriptionInvestigation> listForSave = new ArrayList<>();
            for(PatientPrescriptionInvestigation obj: investigationList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            investigationRepo.saveAll(listForSave);
        }
        
        /*update treatment*/
        deleteDetilsData(saveEntity, treatmentList);
        if(!ObjectUtils.isEmpty(saveEntity) && treatmentList.size() > 0){
        	List<PatientPrescriptionTreatment> listForSave = new ArrayList<>();
            for(PatientPrescriptionTreatment obj: treatmentList){
            	obj.setPrescriptionMaster(saveEntity);
            	if(obj.getId() == null) {
            		obj.setEntryUser(userId);
                    commonUtils.setEntryUserInfo(obj);
                }else {
                	obj.setUpdateUser(userId);
                	obj.setUpdateDate(new Date());
                }
                listForSave.add(obj);
            }
            treatmentRepo.saveAll(listForSave);
        }
        
        /*update advice*/
        adviceRepo.deleteByPrescriptionMasterId(saveEntity.getId());
        if(!ObjectUtils.isEmpty(saveEntity) && adviceList.size() > 0){
        	List<PatientPrescriptionAdvice> listForSave = new ArrayList<>();
            for(PatientPrescriptionAdvice obj: adviceList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            adviceRepo.saveAll(listForSave);
        }
        
        /*update disposal*/
        disposalRepo.deleteByPrescriptionMasterId(saveEntity.getId());
        if(!ObjectUtils.isEmpty(saveEntity) && disposalList.size() > 0){
        	List<PatientPrescriptionDisposal> listForSave = new ArrayList<>();
            for(PatientPrescriptionDisposal obj: disposalList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            disposalRepo.saveAll(listForSave);
        }

        /*update On Examination*/
        onExaminationRepo.deleteByPrescriptionMasterId(saveEntity.getId());
        if(!ObjectUtils.isEmpty(saveEntity) && onExaminationList.size() > 0){
        	List<OnExamination> listForSave = new ArrayList<>();
            for(OnExamination obj: onExaminationList){
            	obj.setEntryUser(userId);
                commonUtils.setEntryUserInfo(obj);
                obj.setPrescriptionMaster(saveEntity);
                listForSave.add(obj);
            }
            onExaminationRepo.saveAll(listForSave);
        }
		
		
		return body;
	}
	
	/*helper*/
    private void deleteDetilsData(PatientPrescriptionMaster savedEntity, List<PatientPrescriptionTreatment> treatmentList) {
        List<PatientPrescriptionTreatment> listForCheckDelete = treatmentRepo.findByPrescriptionMasterIdOrderByIdAsc(savedEntity.getId());
        for(PatientPrescriptionTreatment obj: listForCheckDelete) {
            boolean isNeedToDelete = true;
            for(PatientPrescriptionTreatment detailsEntity: treatmentList) {
                if(obj.getId().equals(detailsEntity.getId())) {
                    isNeedToDelete = false;
                    break;
                }
            }
            if(isNeedToDelete) {treatmentRepo.delete(obj);}
        }
    }


	@Override
	public PatientPrescriptionRequest delete(PatientPrescriptionRequest entity) {
		masterRepository.delete(entity.getMaster());
		return entity;
	}

	@Override
	public List<PatientPrescriptionRequest> getAll() {
		return convertMasterToMasterDetails(masterRepository.findAll());
	}

	@Override
	public List<PatientPrescriptionRequest> getAllActive() {
		return convertMasterToMasterDetails(masterRepository.findByActive(true));
	}

	@Override
	public Page<PatientPrescriptionRequest> getPageableList(int page, int size) {
		return null;
	}

	
	@Override
	public List<PatientPrescriptionRequest> getTemplateList(Integer hosType, int doctor) {
		return convertMasterToMasterDetails(
				masterRepository.findAllTemplate(hosType, doctor));
	}
	
	@Override
    public List<PatientPrescriptionRequest> getPatient(Integer patient) {
        return convertMasterToMasterDetails(masterRepository.findByPatientId(patient));
    }
	
	@Override
    public List<PatientPrescriptionTreatment> getContinueMedicine(Integer patId) {
        return treatmentRepo.findContinueMedicine(patId);
    }
	
	@Override
    public List<PatientPrescriptionRequest> getChronicPatientLastPrescription(Integer patient) {
        return convertMasterToMasterDetails(masterRepository.findChronicPatientLastPrescription(patient));
    }
	
	@Override
    public List<PatientPrescriptionRequest> getByPrescriptionNo(String prescriptionNo) {
        return convertMasterToMasterDetails(masterRepository.findByPrescriptionNo(prescriptionNo));
    }
	
	
	/* helper */
	private String generateNumber(Date prescriptionDate){
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy-");
        LocalDateTime now = LocalDateTime.now();
        String datePart = dtf.format(now);
        int todayCount = masterRepository.countData(prescriptionDate) + 1;
    	return datePart+todayCount;
    }
	
	private String tokenGenerateNumber(TokenRegister entity){
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy-");
        LocalDateTime now = LocalDateTime.now();
        String datePart = dtf.format(now);
        int docId = entity.getTokenType() == EHMConstant.MEDICINE_ID ? 0 : entity.getReferToDoctorId();
        int todayCount = tokenRegisterRepo.countTodayRegister(entity.getVisitDate(), entity.getTokenType(), entity.getHospitalType(),docId) + 1;
    	return entity.getTokenType() == EHMConstant.MEDICINE_ID ? EHMConstant.PREFIX_MEDICINE+datePart+todayCount :  todayCount+"";
    }
	
	private List<PatientPrescriptionRequest> convertMasterToMasterDetails(List<PatientPrescriptionMaster> list) {
        List<PatientPrescriptionRequest> returnList = new ArrayList<>();
        for (PatientPrescriptionMaster master: list) {
        	PatientPrescriptionRequest tmp = new PatientPrescriptionRequest();
        	
            /*set master*/
            tmp.setMaster(master);
            /*set cc*/
            tmp.setCcList(ccRepo.findByPrescriptionMasterId(master.getId()));
            /*set disease*/
            tmp.setDiseaseList(diseaseRepo.findByPrescriptionMasterId(master.getId()));
            /*set disease*/
            tmp.setInvestigationFindingList(investigationFindingRepo.findByPrescriptionMasterId(master.getId()));
            /*set investigation*/
            tmp.setInvestigationList(investigationRepo.findByPrescriptionMasterId(master.getId()));
            /*set treatment*/
            tmp.setTreatmentList(treatmentRepo.findByPrescriptionMasterIdOrderByIdAsc(master.getId()));
            /*set advice*/
            tmp.setAdviceList(adviceRepo.findByPrescriptionMasterId(master.getId()));
            /* set disposal */
            tmp.setDisposalList(disposalRepo.findByPrescriptionMasterId(master.getId()));

            /* set on Examination */
            tmp.setOnExaminationList(onExaminationRepo.findByPrescriptionMasterId(master.getId()));
            returnList.add(tmp);
        }
        return  returnList;
    }

    @Override
    public Page<PatientPrescriptionMaster> getPageableListWithHosType(int page, int size, int hosType) {
        PageRequest pageRequest  = commonUtils.getPageRequest(page, size);
        Page<PatientPrescriptionMaster> pageResult = masterRepository.findAllByHosTypeOrderByIdDesc(hosType, pageRequest);
        List<PatientPrescriptionMaster> objList = pageResult.stream().collect(Collectors.toList());
        return new PageImpl<>(objList, pageRequest, pageResult.getTotalElements());
    }

    @Override
    public PatientPrescriptionRequest convertToResponseType(PatientPrescriptionMaster master) {
        PatientPrescriptionRequest tmp = new PatientPrescriptionRequest();
        /*set master*/
        tmp.setMaster(master);
        /*set cc*/
        tmp.setCcList(ccRepo.findByPrescriptionMasterId(master.getId()));
        /*set disease*/
        tmp.setDiseaseList(diseaseRepo.findByPrescriptionMasterId(master.getId()));
        /*set disease*/
        tmp.setInvestigationFindingList(investigationFindingRepo.findByPrescriptionMasterId(master.getId()));
        /*set investigation*/
        tmp.setInvestigationList(investigationRepo.findByPrescriptionMasterId(master.getId()));
        /*set treatment*/
        tmp.setTreatmentList(treatmentRepo.findByPrescriptionMasterIdOrderByIdAsc(master.getId()));
        /*set advice*/
        tmp.setAdviceList(adviceRepo.findByPrescriptionMasterId(master.getId()));
        /* set disposal */
        tmp.setDisposalList(disposalRepo.findByPrescriptionMasterId(master.getId()));

        /* set on Examination */
        tmp.setOnExaminationList(onExaminationRepo.findByPrescriptionMasterId(master.getId()));
        // returnList.add(tmp);
        return  tmp;
    }
    
}
