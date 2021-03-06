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
        // ??????????????????????????????id,????????????????????????????????????id
        var sale = skuDao.selectSaleIdByClient(clientId);
        // ??????list ????????????id
        List<Integer> saleId = new ArrayList<>();
        // ??????list ???????????????
        List<String> saleDate;
        // ????????????map skuID???key???dataList???value
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        // ???skuId??????key?????????map???
        sale.forEach(sales -> map.put(sales.getSkuId(), new ArrayList<>()));
        for (SkuPrice saleOrder : sale) {
            saleId.add(saleOrder.getId());
            // ??????skuID
            String key = saleOrder.getSkuId();
            // ?????? date
            String value = saleOrder.getDate();
            // ??????map???key???skuId???value????????????
            if (map.get(key) == null || map.get(key).size() == 0) {
                saleDate = new ArrayList<>();
            } else {
                // value???????????????value???????????????saleDate
                saleDate = map.get(key);
            }
            // ???date?????????saleDate
            saleDate.add(value);
            map.put(key, saleDate);
        }

        String[] str = sku.split(split);

        // ????????????map ???skuId????????????priceList???????????????map???
        MultiValueMap<String, Double> skuMap = new LinkedMultiValueMap<>();

        // ?????????????????????skuId???????????????map???
        for (String s : str) {
            skuMap.put(s, new ArrayList<>());
        }
        // ????????????????????????skuId,clientId,saleId ??????????????????????????????????????????skuId?????????
        var skuPriceList = skuDao.selectHistoryPriceBySkuId(str, clientId, saleId);
        List<Double> priceList;

        for (SkuPrice skuPrice : skuPriceList) {
            // ??????skuId
            String key = skuPrice.getSkuId();
            // ????????????
            Double price = skuPrice.getPrice();
            List<Double> value = skuMap.get(key);
            // ??????skuId?????????value????????????  ?????? ?????????????????????priceList???
            if (value == null || value.size() == 0) {
                priceList = new ArrayList<>();
            } else {
                // ???????????????value ????????? priceList
                priceList = value;
            }
            priceList.add(price);
            skuMap.put(key, priceList);
        }
        var skuList = skuDao.batchSelect(str);
        skuList.forEach(skuOne -> {
            // ??????skuId???map???key??????
            String skuId = skuOne.getSkuId();
            if (skuMap.containsKey(skuId)) {
                // ???map???key?????????skuId???value?????????????????? ???skuId???????????????
                skuOne.setHistoryPrice(skuMap.get(skuOne.getSkuId()));
            }
            if (map.containsKey(skuId)) {
                skuOne.setDate(map.get(skuId));
            }

        });
        return skuList;
    }


    /**
     * ????????????,??????
     *
     * @param dto ?????? dto { "skuId":"????????? skuId", "price":"???????????????", "quantity":"?????? skuId ?????????" }
     * @return ?????????
     */
    @Override
    public String getBill(SalesDTO dto) {
        var invoiceRecordList = dto.getInvoice();

        int size = invoiceRecordList.size();
        int invoiceSize = dto.getInvoice().size();
        //????????????
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
            //????????????
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
            //????????????
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
            //??? skuid,quantity ????????????sku??????
            skuMap.put(sqlSkuId, soldQuantity);
            //?????????
            int lastQuantity = sqlQuantity - soldQuantity;
            if (lastQuantity < 0) {
                throw new BusinessException(ResponseCode.BIZ_INVENTORY_INSUFFICIENT);
            }
            record.setInventoryQuantity(lastQuantity);
        });

        //???,????????????????????????
        ExceptionUtil.isUpdateSuccess(inventoryDao.updateBatch(inventoryList));
        //???????????????????????????????????????
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
        // ??????saleOrderId???saleOrder??????????????????
        Double exchangeRate = goodsDao.selectExchangeRate(saleOrderId);
        // ????????????????????? DecimalFormat???format??????
        DecimalFormat df = new DecimalFormat("#.00");
        list.forEach(entity -> {
            //???????????????????????????
            Double price = exchangeRate * entity.getPrice();
            //Double.parseDouble() ????????? String??????Double??????
            entity.setPrice(Double.parseDouble(df.format(price)));
            // ?????????????????????*??????*??????
            Double amount = exchangeRate * entity.getPrice() * entity.getQuantity();
            entity.setAmount(Double.parseDouble(df.format(amount)));
        });
        // ?????????????????????????????????
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
