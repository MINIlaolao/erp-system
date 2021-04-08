package com.kintexgroup.hkpsi.information.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author lmao
 * @since 2020/9/14 14:27
 */
@Data
public class ContactUsDTO {

    /**
     * 姓名
     */
    @NotBlank
    @Length(max = 50)
    private String name;

    /**
     * 邮箱
     */
    @Email(message = "{valid.mail}")
    private String email;

    @NotBlank
    private String message;
}
