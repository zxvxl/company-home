package com.hongyuan.cms.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyuan.cms.domain.CmsPartner;
import com.hongyuan.cms.mapper.CmsPartnerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsPartnerService extends ServiceImpl<CmsPartnerMapper, CmsPartner> {

    /**
     * 获取启用的合作伙伴列表
     */
    public List<CmsPartner> getEnabledList() {
        return lambdaQuery()
                .eq(CmsPartner::getStatus, 1)
                .orderByAsc(CmsPartner::getSortOrder)
                .list();
    }
}
