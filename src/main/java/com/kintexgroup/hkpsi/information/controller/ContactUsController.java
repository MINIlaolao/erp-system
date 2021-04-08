package com.kintexgroup.hkpsi.information.controller;

import io.swagger.annotations.Api;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.ContactUsDTO;
import com.kintexgroup.hkpsi.information.service.ContactUsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lmao
 * @since 2020/9/14 14:49
 */
@RestController
@ResponseWrapper
@RequestMapping("/api/information")
@Api(tags = "联系我们")
public class ContactUsController {

    @Resource
    private ContactUsService contactUsService;


    @PostMapping("/create_one_customer_message")
    public Boolean createOneCustomerMessage(@RequestBody @Validated ContactUsDTO dto) {
        return contactUsService.receiptAchieve(dto);
    }

}
