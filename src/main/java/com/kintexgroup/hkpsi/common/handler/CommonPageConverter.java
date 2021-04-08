package com.kintexgroup.hkpsi.common.handler;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lmao
 * @since 2020/10/29 11:31
 */
public class CommonPageConverter {

    private CommonPageConverter() {
    }

    /**
     * 分页助手的转换
     *
     * @param entityList 数据库结果集
     * @param voList     返回前端结果集
     * @return 前端结果集
     */
    public static <E, T> PageInfo<T> toPageInfo(Page<E> entityList, List<T> voList) {
        PageInfo<E> entityPageInfo = entityList.toPageInfo();
        PageInfo<T> voPageInfo = new PageInfo<>(voList);
        voPageInfo.setTotal(entityPageInfo.getTotal());
        voPageInfo.setIsLastPage(entityPageInfo.isIsLastPage());
        voPageInfo.setIsFirstPage(entityPageInfo.isIsFirstPage());
        voPageInfo.setSize(entityPageInfo.getSize());
        voPageInfo.setPageSize(entityPageInfo.getPageSize());
        voPageInfo.setHasNextPage(entityPageInfo.isHasNextPage());
        voPageInfo.setHasPreviousPage(entityPageInfo.isHasPreviousPage());
        voPageInfo.setPrePage(entityPageInfo.getPrePage());
        voPageInfo.setNextPage(entityPageInfo.getNextPage());
        voPageInfo.setNavigateFirstPage(entityPageInfo.getNavigateFirstPage());
        voPageInfo.setNavigateLastPage(entityPageInfo.getNavigateLastPage());
        voPageInfo.setNavigatepageNums(entityPageInfo.getNavigatepageNums());
        voPageInfo.setNavigatePages(entityPageInfo.getNavigatePages());
        return voPageInfo;
    }

}


