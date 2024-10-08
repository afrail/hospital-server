package com.sopnobazz.demo.comon.base.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Transient;

import com.sopnobazz.demo.comon.base.model.MetaModel;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;


/**
 * @version 1.0.0
 * @Project demo-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 23, 2022
 */


@Component
public class UtilValidate {

    public boolean noData(String txt) {
        if (null == txt || txt.trim().isEmpty() || txt.equals("null") || txt.equalsIgnoreCase("null")) return true;
        return false;
    }

    public boolean noData(Boolean b) {
        return b == null || !b;
    }

    public boolean noData(Object obj) {
        return obj == null;
    }

    public boolean noData(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public boolean isTransient(Class<?> clz, String fieldName) {
        if (new ArrayList<>(Arrays.asList(FieldUtils.getFieldsWithAnnotation(clz, Transient.class))).stream().anyMatch(x -> x.getName().equalsIgnoreCase(fieldName)))
            return true;
        return false;
    }

    /**
     * @param number
     * @return true if number is grater than equal 0
     */
    public boolean isPositiveNumber(Integer number) {
        if (!noData(number) && number >= 0) return true;
        return false;
    }

    public boolean isPositiveNumber(Long number) {
        if (!noData(number) && number >= 0) return true;
        return false;
    }

    public boolean isPositiveNumber(Float number) {
        if (!noData(number) && number >= 0) return true;
        return false;
    }

    public boolean isPositiveNumber(Double number) {
        if (!noData(number) && number >= 0) return true;
        return false;
    }

    /**
     * @param number
     * @return true if number is grater than 0
     */
    public boolean isNonZeroPositiveNumber(Integer number) {
        if (!noData(number) && number > 0) return true;
        return false;
    }

    public boolean isNonZeroPositiveNumber(Long number) {
        if (!noData(number) && number > 0) return true;
        return false;
    }

    public boolean isNonZeroPositiveNumber(Float number) {
        if (!noData(number) && number > 0) return true;
        return false;
    }

    public boolean isNonZeroPositiveNumber(Double number) {
        if (!noData(number) && number > 0) return true;
        return false;
    }

    public boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public boolean isValidMeta(MetaModel meta) {
        return !noData(meta) && !noData(meta.getLimit()) && !noData(meta.getOffset());
    }
}
