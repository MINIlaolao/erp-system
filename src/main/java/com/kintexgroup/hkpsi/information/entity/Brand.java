package com.kintexgroup.hkpsi.information.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author pengli
 * @since 2020/9/8 6:20 下午
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Brand implements Serializable {

    /**
     * `brand_id` INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT
     */
    private Integer id;

    /**
     * `brand_name` VARCHAR(255) NOT NULL UNIQUE
     */
    private String name;

    /**
     * `disabled` TINYINT UNSIGNED NOT NULL DEFAULT 0
     */
    private Integer disabled;

    private Date createdAt;

    private Integer createdBy;

    private Date updatedAt;

    private Integer updatedBy;

    private Date removedAt;

    private Integer removedBy;

    private static final long serialVersionUID = 1L;
}
