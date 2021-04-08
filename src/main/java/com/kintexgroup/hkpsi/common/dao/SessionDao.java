package com.kintexgroup.hkpsi.common.dao;

import com.kintexgroup.hkpsi.common.handler.MapResultHandler;
import com.kintexgroup.hkpsi.information.dao.VendorSkuDao;
import com.kintexgroup.hkpsi.sales.dao.GoodsDao;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * 用于session查询
 * <p>
 * https://blog.csdn.net/jlh912008548/article/details/62884627
 *
 * @author lmao
 * @since 2020/11/5 10:22
 */
@Repository
public class SessionDao extends SqlSessionDaoSupport {

    @Override
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }


    /**
     * https://blog.csdn.net/jlh912008548/article/details/62884627
     *
     * @param tags tags array
     * @return 转换好的 map
     */
    @SuppressWarnings("unchecked")
    public <T> Map<String, String> selectDescriptionConverterMap(Set<String> tags) {
        MapResultHandler<T> handler = new MapResultHandler<>();
        this.getSqlSession()
            .select(VendorSkuDao.class.getName() + ".selectDescription", tags, RowBounds.DEFAULT, handler);
        return handler.getMappedResults();
    }

/*    @SuppressWarnings("unchecked")
    public <T> Map<String, Attribute> selectSkuIdConverterMap(Set<String> skuSet) {
        MapResultHandler<T> handler = new MapResultHandler<>();
        this.getSqlSession()
            .select(SkuDao.class.getName() + ".batchMapSelect", skuSet, RowBounds.DEFAULT, handler);
        Map<String, Object> map = handler.getMappedResults();
        Map<String, Attribute> attributeMap = new HashMap<>(16);
        map.forEach((k, v) -> attributeMap.put(k, new Attribute(v)));
        return attributeMap;
    }*/

    @SuppressWarnings("unchecked")
    public <T> Map<String, Double> selectCostPriceConverterMap(Map<String, String> idImeiMap) {

        MapResultHandler<T> handler = new MapResultHandler<>();
        this.getSqlSession().select(GoodsDao.class.getName() + ".batchSelectCostPrice", idImeiMap, RowBounds.DEFAULT, handler);

        return handler.getMappedResults();
    }

}



