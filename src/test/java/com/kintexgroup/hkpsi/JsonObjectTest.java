package com.kintexgroup.hkpsi;

import com.kintexgroup.hkpsi.purchasing.dao.BidDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author LMAO
 * @since 2020/9/10 14:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonObjectTest {

    @Autowired
    public BidDao bidDao;

//    @Test
//    public void JsonMapperTest() {
//        var map = bidDao.selectOne2("20090301001");
//        System.out.println(map.get("date").getClass().toString());
//    }
}
