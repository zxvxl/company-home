package com.hongyuan.cms.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyuan.cms.domain.CmsBusiness;
import com.hongyuan.cms.mapper.CmsBusinessMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsBusinessService extends ServiceImpl<CmsBusinessMapper, CmsBusiness> {

    /**
     * 获取启用的业务领域列表（按排序字段升序）
     */
    public List<CmsBusiness> getEnabledList() {
        return lambdaQuery()
                .eq(CmsBusiness::getStatus, 1)
                .orderByAsc(CmsBusiness::getSortOrder)
                .list();
    }
}
