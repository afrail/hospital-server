package com.sopnobazz.demo.comon.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sopnobazz.demo.comon.controller.BaseController;
import com.sopnobazz.demo.comon.entity.SmsFactory;
import com.sopnobazz.demo.comon.repository.SmsFactoryRepository;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class ScheduleTaskHelper {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    private static final int DELAY_SECOND = 30;
    private static final String SUCCESS_RESPONSE = "1101";
    private static final String STATUS_PENDING = "P";
    private static final String STATUS_SEND = "S";

    private SmsFactoryRepository smsFactoryRepository;


    // @Scheduled(fixedDelay = DELAY_SECOND * 1000)
    public void scheduleTaskWithFixedDelay() {
        List<SmsFactory> smsList = smsFactoryRepository.findByStatus(STATUS_PENDING);

        for (SmsFactory sms : smsList) {
            String smsResponse = sendSms(sms.getMobileNo(), sms.getSmsText());
            sms.setSendDate(new Date());
            sms.setReponse(smsResponse);
            String smsRes[] = smsResponse.split("\\|");
            if (smsRes.length > 0 && smsRes[0].equals(SUCCESS_RESPONSE)) {
                sms.setStatus(STATUS_SEND);
            }
            smsFactoryRepository.save(sms);
        }
    }


    private String sendSms(String mobile, String message) {
        try {

            String url = "http://66.45.237.70/api.php?username={username}&password={password}&number={number}&message={message}";

            RestTemplate template = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            HttpEntity requestEntity = new HttpEntity<>(headers);

            Map<String, String> uriVariables = new HashMap<>();

            uriVariables.put("username", "IBCS");
            uriVariables.put("password", "PV6BFAN2");
            uriVariables.put("number", mobile);
            uriVariables.put("message", message);

            ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, requestEntity, String.class, uriVariables);


            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
