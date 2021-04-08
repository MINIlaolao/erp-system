package com.kintexgroup.hkpsi;

import com.kintexgroup.hkpsi.common.dao.SessionDao;
import com.kintexgroup.hkpsi.information.dao.ContactUsDao;
import com.kintexgroup.hkpsi.information.dao.VendorSkuDao;
import com.kintexgroup.hkpsi.information.dao.WebsiteMessageDao;
import com.kintexgroup.hkpsi.information.entity.Spu;
import com.kintexgroup.hkpsi.inventory.dao.SkuDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author LMAO
 * @since 2020/9/15 17:00
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Resource
    private WebsiteMessageDao websiteMessageDao;

    @Resource
    private ContactUsDao contactUsDao;

    @Resource
    private SkuDao skuDao;

    @Resource
    private VendorSkuDao vendorSkuDao;
    
    @Resource
    private SessionDao sessionDao;
    
//    @Test
//    public void SessionMapperTest() {
//        var set = new HashSet<String>();
//        set.add("1");
//        set.add("2");
//
//        var result = sessionDao.selectSkuIdConverterMap(set);
//        result.forEach((k,v)->{
//            System.out.println(v.toString());
//        });
//        
//    }

    @Test
    public void BatchInsertSpuTest() {
        Spu spu = new Spu();
        spu.setBrand("test");
        spu.setCategory("test");
        spu.setName("Apple");
        var map = new HashMap<String, Object>();

        map.put("color", new String[]{"Silver", "Space Gray"});
        map.put("model", new String[]{"A1865", "A1901", "A1902"});
        map.put("capacity", new int[]{64, 256});
        spu.setSpec(map);
        spu.setCreatedBy(1);
        spu.setUpdatedBy(1);
        var list = new Spu[3];
    }

//    @Test
//    public void mapperTest() {
//        var entity = ContactUs.builder().build();
//        System.out.println(contactUsDao.insert(entity));
//    }
}
