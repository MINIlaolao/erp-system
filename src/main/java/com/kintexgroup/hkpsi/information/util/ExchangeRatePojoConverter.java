package com.kintexgroup.hkpsi.information.util;


import com.kintexgroup.hkpsi.information.entity.ExchangeRate;
import com.kintexgroup.hkpsi.information.model.ExchangeRateDTO;
import com.kintexgroup.hkpsi.information.model.ExchangeRateVO;

/**
 * @author pengli
 * @since 2020/11/13 11:31 上午
 */
public final class ExchangeRatePojoConverter {
    private ExchangeRatePojoConverter() {
    }


    /**
     * 将从接口请求传入的参数类 转换为 实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static ExchangeRate toEntity(ExchangeRateDTO dto) {
        ExchangeRate entity = new ExchangeRate();
        entity.setCurrencyCode(dto.getCurrencyCode());
        entity.setCurrencyName(dto.getCurrencyName());
        entity.setCurrencySymbol(dto.getCurrencySymbol());
        entity.setExchangeRateDouble(dto.getExchangeRate());
        entity.setDisabled(dto.getDisabled());
        return entity;
    }


    /**
     * 将 从数据库获取到的实体类 转为发送给前端的 视图类
     *
     * @param exchangeRate 实体类
     * @return 视图类
     */
    public static ExchangeRateVO fromEntity(ExchangeRate exchangeRate) {
        ExchangeRateVO vo = new ExchangeRateVO();
        vo.setId(exchangeRate.getId());
        vo.setCurrencyCode(exchangeRate.getCurrencyCode());
        vo.setCurrencyName(exchangeRate.getCurrencyName());
        vo.setCurrencySymbol(exchangeRate.getCurrencySymbol());
        vo.setExchangeRate(exchangeRate.getExchangeRateDouble());
        vo.setDisabled(exchangeRate.getDisabled());
        return vo;
    }

    /**
     * 用于创建的实体类
     *
     * @param creator 创建人id
     * @param dto     参数类
     * @return 实体类
     */
    public static ExchangeRate toCreateEntity(int creator, ExchangeRateDTO dto) {
        ExchangeRate entity = toEntity(dto);
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }

    /**
     * 用于更新的实体类
     *
     * @param id      要更新的实体类id
     * @param updater 更新人的id
     * @param dto     参数类
     * @return 实体类
     */
    public static ExchangeRate toUpdateEntity(int id, int updater, ExchangeRateDTO dto) {
        ExchangeRate entity = toEntity(dto);
        entity.setId(id);
        entity.setUpdatedBy(updater);
        return entity;
    }
}
