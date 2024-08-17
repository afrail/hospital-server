/**
 *
 */
package com.sopnobazz.demo.comon.dto;

import lombok.Data;

/**
 * @Project demo-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 22, 2022
 * @version 1.0.0
 */

@Data
public class PasswordChangeDto {

    private String password;

    private String confirmPassword;
}
