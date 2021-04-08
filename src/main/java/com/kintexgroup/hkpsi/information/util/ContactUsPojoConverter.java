package com.kintexgroup.hkpsi.information.util;

import com.kintexgroup.hkpsi.information.entity.ContactUs;
import com.kintexgroup.hkpsi.information.model.ContactUsDTO;
import org.apache.commons.lang3.RandomUtils;

/**
 * @author lmao
 * @since 2020/9/14 14:32
 */
public final class ContactUsPojoConverter {

    private ContactUsPojoConverter() {
    }

    public static ContactUs toEntity(ContactUsDTO dto) {

        ContactUs entity = new ContactUs();

        entity.setId(
            //随机生成工单号为 八位数
            RandomUtils.nextLong(10000000, 99999999)
        );
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        return entity;
    }

}

