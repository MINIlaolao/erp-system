package com.kintexgroup.hkpsi.information.model;

import com.google.common.base.MoreObjects;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author lmao
 * @since 2020/10/24 17:26
 */
@Data
@NoArgsConstructor
public class Attribute {

    private String color;
    private int capacity;
    private String model;
    private String carrier;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Attribute)) {
            return false;
        }
        Attribute attribute = (Attribute) o;
        return getCapacity() == attribute.getCapacity() &&
            getColor().equals(attribute.getColor()) &&
            getModel().equals(attribute.getModel()) &&
            getCarrier().equals(attribute.getCarrier());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getCapacity(), getModel(), getCarrier());
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("color", color)
            .add("capacity", capacity)
            .add("model", model)
            .add("carrier", carrier)
            .toString();
    }
}


