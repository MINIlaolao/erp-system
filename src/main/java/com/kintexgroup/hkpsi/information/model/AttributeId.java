package com.kintexgroup.hkpsi.information.model;

import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

/**
 * @author LMAO
 * @since 2020/11/25 10:38
 */

public class AttributeId extends Attribute {
    @Getter
    @Setter
    private String id;

    public AttributeId() {
    }

    @SuppressWarnings("unchecked")
    public AttributeId(Object o) {
        if (o instanceof Map) {
            var map = (Map<String, Object>) o;
            setColor((String) map.get("color"));
            setCapacity((Integer) map.get("capacity"));
            setCarrier((String) map.get("carrier"));
            setModel((String) map.get("model"));
            setId("0");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttributeId)) {
            return false;
        }
        AttributeId attribute = (AttributeId) o;

        return getCapacity() == attribute.getCapacity() &&
            getColor().equals(attribute.getColor()) &&
            getModel().equals(attribute.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .toString();
    }
}


