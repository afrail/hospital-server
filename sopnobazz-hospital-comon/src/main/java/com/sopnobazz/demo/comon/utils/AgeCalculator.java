package com.sopnobazz.demo.comon.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class AgeCalculator {

    public static Double calculateAge(Date birthDate) {
        LocalDate birthDateLocal = convertToLocalDateViaInstant(birthDate);
        LocalDate currentDateLocal = convertToLocalDateViaInstant(new Date());
        if ((birthDateLocal != null) && (currentDateLocal != null)) {
            Integer year = Period.between(birthDateLocal, currentDateLocal).getYears();
            Integer month = Period.between(birthDateLocal, currentDateLocal).getMonths();
            String age = year + "." + month;
            return Double.parseDouble(age);
        } else {
            return 0.0;
        }
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
