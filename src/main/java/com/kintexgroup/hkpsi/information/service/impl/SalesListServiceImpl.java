package com.kintexgroup.hkpsi.information.service.impl;

import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.information.dao.ExchangeRateDao;
import com.kintexgroup.hkpsi.information.dao.SalesListDao;
import com.kintexgroup.hkpsi.information.entity.SalesList;
import com.kintexgroup.hkpsi.information.model.SalesListCollectVO;
import com.kintexgroup.hkpsi.information.service.SalesListService;
import com.kintexgroup.hkpsi.purchasing.dao.BidDao;
import com.kintexgroup.hkpsi.purchasing.dao.BidRecordDao;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordSalesList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LMAO
 * @since 2020/11/17 11:52
 */
@Service("SalesListService")
@Transactional(rollbackFor = BusinessException.class)
@Slf4j
public class SalesListServiceImpl implements SalesListService {

    @Resource
    private SalesListDao salesListDao;

    @Resource
    private BidRecordDao bidRecordDao;

    @Resource
    private ExchangeRateDao exchangeRateDao;

    @Resource
    private BidDao bidDao;


    /**
     * 中标批次销售清单
     * 根据中标号创建或更新多条销售报表
     *
     * @param id 中标号
     * @return 单条销售报表
     */
    @Override
    public SalesListCollectVO selectSalesList(String id) {
        
        //查询中标批次的手机
//        var param = new BidRecordPageDTO();
//        param.setBidId(id);
//        var list = bidRecordDao.list(param);
//
//        // 保留两位小数用 DecimalFormat的format方法
//        DecimalFormat df = new DecimalFormat("#.00");
//
//        var salesLists = new ArrayList<SalesList>(list.size());
//
//        if (list.isEmpty()) {
//            return new SalesListCollectVO();
//        }
//        //获取中标汇率
//        final ListIterator<BidRecordSalesList> it = list.listIterator();
//        final Double wonBidRate = Double.parseDouble(df.format(it.next().getExchangeRate()));
//        final String currencyCode = it.next().getCurrencyCode();
//        final String currencySymbol = exchangeRateDao.selectSymbolByCode(currencyCode);
//        int totalWonBidRecord = 0;
//        while (it.hasNext()) {
//            totalWonBidRecord += it.next().getWonQty();
//
//        }
//
//        //批量创建销售报表单行
//        createSalesList(id, list, salesLists, wonBidRate);
//
//        var salesListCollect = new HashMap<String, Object>(9);
        //        salesListCollect.put("qtyPurchased", totalWonBidRecord);
//
//        salesListCollect.put("exchangeRate", df.format(wonBidRate));
//        double totalCurrent = 0.00;
//        double totalSales = 0.00;
//        final double freight = 0.00;
//        final double totalHkd = Double.parseDouble(df.format(mul(totalCurrent, wonBidRate)));
//        final double cogs = freight + totalHkd;
//
//        for (SalesList salesList : salesLists) {
//            double singleCurrent = mul(salesList.getCostPrice(), salesList.getQuantity());
//            double singleSales = mul(salesList.getAsp(), salesList.getQuantity());
//            totalCurrent = Double.parseDouble(df.format(totalCurrent + singleCurrent));
//            totalSales = Double.parseDouble(df.format(totalSales + singleSales));
//        }
//        String resultTotalCurrent = currencySymbol + totalCurrent;
//        String resultCurrencyCode = "total" + currencyCode;
//        salesListCollect.put(resultCurrencyCode.toLowerCase(), resultTotalCurrent);
//        salesListCollect.put("totalHKD", df.format(totalHkd));
//        salesListCollect.put("freight", freight);
//        salesListCollect.put("cogs", cogs);
//        if (totalSales == 0) {
//            log.info("卖出商品数量总计为 0");
//            throw new BusinessException(ResponseCode.REQUEST_PARAM_INVALID);
//        }
//        salesListCollect.put("totalSales", df.format(totalSales));
//        //负数要为红色
//        double margin = totalSales - cogs;
//        double gpm = div(margin, totalSales);
//        salesListCollect.put("margin", df.format(margin));
//        salesListCollect.put("gpm", df.format(gpm));
//
//
//        salesListCollectVO.setSalesListCollect(salesListCollect);
//        salesListCollectVO.setSalesLists(salesLists);

        return new SalesListCollectVO();
    }

    /**
     * 批量创建销售报表单行
     *
     * @param wonbidId   中标号
     * @param wonbidList 中标批次的手机
     * @param salesLists 单条销售报表
     * @param wonBidRate 中标汇率
     */
    private void createSalesList(String wonbidId, List<BidRecordSalesList> wonbidList, ArrayList<SalesList> salesLists, Double wonBidRate) {
//        final int createBy = ContextHolderUtil.getAuthedUserId();
//
//        // 保留两位小数用 DecimalFormat的format方法
//        DecimalFormat df = new DecimalFormat("#.00");
//
//        for (BidRecordSalesList wonbid : wonbidList) {
//
//            SalesList salesList = new SalesList();
//            salesList.setWonBidId(wonbidId);
//            salesList.setDescription(wonbid.getDescription());
//            salesList.setModelName(wonbid.getModelName());
//            salesList.setModelNumber(wonbid.getModel());
//            salesList.setItem(wonbid.getItem());
//            salesList.setCarrier(wonbid.getCarrier());
//            //----set----
//            salesList.setColor(wonbid.getColor());
//            salesList.setCapacity(wonbid.getCapacity());
//            salesList.setGrade(wonbid.getGrade());
//            salesList.setSkuId(wonbid.getSkuId());
//            salesList.setQuantity(wonbid.getWonQty());
//
//            //----运算部分----
//            var quantity = wonbid.getInventory();
//
//            //asp单个拟定价(hkd)
//            var asp = div(mul(wonbid.getSinglePrice(), wonbid.getSaleQuantity()), wonbid.getQuantity().doubleValue());
//            var aspCount = mul(asp, quantity);
//            //cpu单个成本价(美金)
//            var cpu = wonbid.getWonPrice();
//            var totalCpu = mul(cpu, quantity);
//            //hkd
//            var hkd = mul(cpu, wonBidRate);
//            //利润,负数为红色
//            var unitProfit = df.format(asp - hkd);
//            var countProfit = mul(Double.parseDouble(unitProfit), quantity);
//            double gpm;
//            if (asp != 0) {
//                gpm = div(Double.parseDouble(unitProfit), asp);
//            } else {
//                gpm = 0;
//            }
//
//            //成本价
//            salesList.setCostPrice(cpu);
//            salesList.setTotalCost(totalCpu);
//            //拟定价格
//            salesList.setAsp(Double.parseDouble(df.format(asp)));
//            salesList.setCountPrice(aspCount);
//
//            salesList.setUnitProfit(Double.parseDouble(unitProfit));
//            salesList.setCountProfit(countProfit);
//
//            salesList.setGpm(Double.parseDouble(df.format(gpm)));
//            salesList.setHkd(hkd);
//            salesList.setClientName(wonbid.getClientName());
//            salesList.setSalesman(wonbid.getSalesman());
//
//            salesList.setCreatedBy(createBy);
//            salesList.setUpdatedBy(createBy);
//
//            salesLists.add(salesList);
//        }
    }

}