package com.kintexgroup.hkpsi.information.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lmao
 * @since 2020/9/8 14:51
 */
@Data
@Entity
@Table(name = "hkpsi_category")
public class Category implements Serializable {

    private static final long serialVersionUID = -1258324678104061478L;
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "category_name")
    private String name;
    @Column(name = "disabled")
    private Integer disabled;

    private List<SkuAttribute> attributes;
    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 修改时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "updated_by")
    private Integer updatedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }

        Category category = (Category) o;

        if (getName() != null ? !getName().equals(category.getName())
            : category.getName() != null) {
            return false;
        }
        return getDisabled() != null ? getDisabled().equals(category.getDisabled())
            : category.getDisabled() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getDisabled() != null ? getDisabled().hashCode() : 0);
        return result;
    }
}
