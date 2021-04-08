package com.kintexgroup.hkpsi.sales.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.dao.SessionDao;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.ExceptionUtil;
import com.kintexgroup.hkpsi.common.util.JsonUtil;
import com.kintexgroup.hkpsi.information.dao.ExchangeRateDao;
import com.kintexgroup.hkpsi.information.dao.SalesListDao;
import com.kintexgroup.hkpsi.inventory.dao.InventoryDao;
import com.kintexgroup.hkpsi.inventory.dao.SkuDao;
import com.kintexgroup.hkpsi.information.dao.ClientDao;
import com.kintexgroup.hkpsi.sales.dao.GoodsDao;
import com.kintexgroup.hkpsi.sales.dao.SaleOrderDao;
import com.kintexgroup.hkpsi.sales.entity.Goods;
import com.kintexgroup.hkpsi.sales.model.*;
import com.kintexgroup.hkpsi.sales.service.GoodsService;
import com.kintexgroup.hkpsi.sales.util.GoodsPojoConverter;
import com.kintexgroup.hkpsi.sales.util.SaleOrderPojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lmao               pengli
 * @since 2020/9/27 16:08   2020/11/19
 */
@Service("GoodsService")
@Transactional(rollbackFor = BusinessException.class)
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Resource
    private SkuDao skuDao;

    @Resource
    private SaleOrderDao saleOrderDao;

    @Resource
    private InventoryDao inventoryDao;

    @Resource
    private SessionDao sessionDao;

    @Resource
    private ExchangeRateDao exchangeRateDao;

    @Resource
    private ClientDao clientDao;

    @Resource
    private SalesListDao salesListDao;

    static final String ATTRIBUTE = "attribute";

    @Override
    public Map<String, Object> getGoods(Long imei) {
        var map = goodsDao.selectGoods(imei);
        if (map == null || map.isEmpty()) {
            throw new BusinessException(ResponseCode.BIZ_GOODS_SOLD);
        }
        map.put(ATTRIBUTE, JsonUtil.parse((String) map.get(ATTRIBUTE)));
        return map;
    }

    @Override
    public List<GoodsSkuModel> getGoodsSku(String sku, Integer clientId) {
        var split = ",";
        // 根据前端传进来的客户id,查询此客户近三次的销售单id
        var sale = skuDao.selectSaleIdByClient(clientId);
        // 定义list 存销售单id
        List<Integer> saleId = new ArrayList<>();
        // 定义list 存销售时间
        List<String> saleDate;
        // 定义一个map skuID为key，dataList为value
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        // 把skuId当做key添加到map中
        sale.forEach(sales -> map.put(sales.getSkuId(), new ArrayList<>()));
        for (SkuPrice saleOrder : sale) {
            saleId.add(saleOrder.getId());
            // 取出skuID
            String key = saleOrder.getSkuId();
            // 取出 date
            String value = saleOrder.getDate();
            // 判断map中key为skuId的value是否为空
            if (map.get(key) == null || map.get(key).size() == 0) {
                saleDate = new ArrayList<>();
            } else {
                // value不为空，把value取出赋值给saleDate
                saleDate = map.get(key);
            }
            // 把date添加到saleDate
            saleDate.add(value);
            map.put(key, saleDate);
        }

        String[] str = sku.split(split);

        // 定义一个map 把skuId作为键，priceList作为值存到map中
        MultiValueMap<String, Double> skuMap = new LinkedMultiValueMap<>();

        // 循环把传进来的skuId当做键放入map中
        for (String s : str) {
            skuMap.put(s, new ArrayList<>());
        }
        // 根据前端传进来的skuId,clientId,saleId 查询这个顾客的近三次买同一个skuId的价格
        var skuPriceList = skuDao.selectHistoryPriceBySkuId(str, clientId, saleId);
        List<Double> priceList;

        for (SkuPrice skuPrice : skuPriceList) {
            // 获取skuId
            String key = skuPrice.getSkuId();
            // 获取价格
            Double price = skuPrice.getPrice();
            List<Double> value = skuMap.get(key);
            // 判断skuId对应得value是否为空  为空 就把价格添加到priceList中
            if (value == null || value.size() == 0) {
                priceList = new ArrayList<>();
            } else {
                // 把取出来的value 赋值给 priceList
                priceList = value;
            }
            priceList.add(price);
            skuMap.put(key, priceList);
        }
        var skuList = skuDao.batchSelect(str);
        skuList.forEach(skuOne -> {
            // 判断skuId在map的key中的
            String skuId = skuOne.getSkuId();
            if (skuMap.containsKey(skuId)) {
                // 把map中key是这个skuId的value取出来设置为 此skuId的历史售价
                skuOne.setHistoryPrice(skuMap.get(skuOne.getSkuId()));
            }
            if (map.containsKey(skuId)) {
                skuOne.setDate(map.get(skuId));
            }

        });
        return skuList;
    }


    /**
     * 库存验证,开单
     *
     * @param dto 每个 dto { "skuId":"开单的 skuId", "price":"填入的价格", "quantity":"对应 skuId 的数量" }
     * @return 订单号
     */
    @Override
    public String getBill(SalesDTO dto) {
        var invoiceRecordList = dto.getInvoice();

        int size = invoiceRecordList.size();
        int invoiceSize = dto.getInvoice().size();
        //创建订单
        var saleOrder = SaleOrderPojoConverter.toCreateEntity(0, dto);

        saleOrder.setId(saleOrderId(saleOrder.getDate()));

        ExceptionUtil.isInsertSuccess(saleOrderDao.create(saleOrder));
        String saleOrderNum = saleOrder.getId();

        var inputQuantityMap = new HashMap<String, Integer>(size);
        var goodsMap = new HashMap<String, Goods>(size);

        var strings = new String[size];
        var index = 0;

        var idImeisList = new ArrayList<IdImeiBean>(size);
        for (InvoiceRecord bill : invoiceRecordList) {
            var skuId = bill.getSkuId();
            var quantity = bill.getQuantity();
            var grade = bill.getGrade();
            if (quantity <= 0 || dto.getExchangeRate() <= 0) {
                throw new BusinessException(ResponseCode.INSERT_PARAM_INVALID);
            }
            //单件售价
            var price = (bill.getPrice() / quantity) / dto.getExchangeRate();
            var imeis = bill.getImeis();
            String singleImei = null;
            for (String imei : imeis) {
                if (singleImei == null) {
                    singleImei = imei;
                }
                var idImeiBean = new IdImeiBean(skuId, imei);
                idImeisList.add(idImeiBean);
            }
            strings[index] = skuId;
            inputQuantityMap.put(skuId, quantity);
            //设置商品
            Goods goods = new Goods();
            goods.setPrice(price);
            goods.setGrade(grade);
            goods.setSkuId(skuId);
            goods.setSaleOrder(saleOrderNum);
            goodsMap.put(skuId, goods);
            ++index;

        }
        var inventoryList = inventoryDao.selectBatchInventoryQuantity(strings);
        //k:skuId v:inputQuantity 
        var skuMap = new HashMap<String, Integer>(invoiceSize);

        inventoryList.forEach(record -> {

            var sqlSkuId = String.valueOf(record.getSkuId());
            var sqlQuantity = record.getInventoryQuantity();
            var soldQuantity = inputQuantityMap.get(sqlSkuId);
            //取 skuid,quantity 用来查询sku属性
            skuMap.put(sqlSkuId, soldQuantity);
            //减库存
            int lastQuantity = sqlQuantity - soldQuantity;
            if (lastQuantity < 0) {
                throw new BusinessException(ResponseCode.BIZ_INVENTORY_INSUFFICIENT);
            }
            record.setInventoryQuantity(lastQuantity);
        });

        //是,更改库存表中数量
        ExceptionUtil.isUpdateSuccess(inventoryDao.updateBatch(inventoryList));
        //更改商品表中的价格和订单号
        //FIXME
        ExceptionUtil
            .isUpdateSuccess(goodsDao.updateBatchInvoiceSelective(goodsMap, idImeisList));

        return saleOrderNum;
    }

    private String saleOrderId(Date date) {
        var count = saleOrderDao.selectCountByDate(date);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        var dateStr = dateFormat.format(date);
        return "XS" + dateStr + count;
    }

    @Override
    public GoodsVO[] getAllGoods() {
        var entityList = goodsDao.selectAllGoods();
        int size = entityList.size();
        int index = 0;
        var goods = new GoodsVO[size];
        for (GoodsVO vo : entityList) {
            vo.setAttribute(JsonUtil.toObject((String) vo.getAttribute()));
            goods[index] = vo;
            index++;
        }
        return goods;
    }

    @Override
    public Map<String, Object> getOneGoodsBySaleOrderId(String saleOrderId) {
        Map<String, Object> map = new HashMap<>(2);
        List<GoodsVO> list = goodsDao.selectOneBySaleOrderId(saleOrderId);
        // 根据saleOrderId向saleOrder表中查询汇率
        Double exchangeRate = goodsDao.selectExchangeRate(saleOrderId);
        // 保留两位小数用 DecimalFormat的format方法
        DecimalFormat df = new DecimalFormat("#.00");
        list.forEach(entity -> {
            //把单价进行汇率转换
            Double price = exchangeRate * entity.getPrice();
            //Double.parseDouble() 方法把 String转为Double类型
            entity.setPrice(Double.parseDouble(df.format(price)));
            // 计算金额：汇率*单价*数量
            Double amount = exchangeRate * entity.getPrice() * entity.getQuantity();
            entity.setAmount(Double.parseDouble(df.format(amount)));
        });
        // 把查询的手续费乘以汇率
        Double handling = goodsDao.selectCountHandlingCharge(saleOrderId) * exchangeRate;
        Double handlingCharge = Double.parseDouble(df.format(handling));
        map.put("handlingCharge", handlingCharge);
        map.put("list", list);
        return map;
    }

    @Override
    public PageInfo<GoodsVO> selectOneOrManyGoods(GoodsPageDTO dto, int page, int size) {
        PageMethod.startPage(page, size, true);
        var list = new ArrayList<GoodsVO>();
        var records = goodsDao.selectOneOrMany(dto);
        records.forEach(record -> list.add(GoodsPojoConverter.fromGoods(record)));
        return CommonPageConverter.toPageInfo(records, list);

    }


}
