package com.kintexgroup.hkpsi.information.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author pengli
 * @author lmao
 * @since 2020/9/8 2:20 下午
 * <p>
 * Serializable ：实现序列化接口。 序列化：把对象转换为字节序列的过程
 */
@Data
public class Spu implements Serializable {

    private Integer id;

    private String name;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 种类
     */
    private String category;

    /**
     * 描述
     */
    private Map<String, Object> spec;

    /**
     * 是否可用
     */
    private Integer disabled;

    /**
     * 创建时间 数据表自动生成
     */
    private Date createdAt;

    /**
     * 创建人ID
     */
    private Integer createdBy;

    /**
     * 修改时间 数据表自动生成
     */
    private Date updatedAt;

    /**
     * 修改人ID
     */
    private Integer updatedBy;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Spu)) {
            return false;
        }
        Spu spu = (Spu) o;
        return getId().equals(spu.getId()) &&
            getName().equals(spu.getName()) &&
            getBrand().equals(spu.getBrand()) &&
            getCategory().equals(spu.getCategory()) &&
            getDisabled().equals(spu.getDisabled());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBrand(), getCategory(), getDisabled());
    }
}
