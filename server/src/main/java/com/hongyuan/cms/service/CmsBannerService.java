package com.hongyuan.cms.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyuan.cms.domain.CmsBanner;
import com.hongyuan.cms.mapper.CmsBannerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsBannerService extends ServiceImpl<CmsBannerMapper, CmsBanner> {

    /**
     * 获取启用的轮播图列表（按排序字段升序）
     */
    public List<CmsBanner> getEnabledBanners() {
        return lambdaQuery()
                .eq(CmsBanner::getStatus, 1)
                .orderByAsc(CmsBanner::getSortOrder)
                .list();
    }
}
