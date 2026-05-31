package com.hongyuan.cms.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyuan.cms.domain.CmsCompany;
import com.hongyuan.cms.mapper.CmsCompanyMapper;
import org.springframework.stereotype.Service;

@Service
public class CmsCompanyService extends ServiceImpl<CmsCompanyMapper, CmsCompany> {

    /**
     * 获取公司信息（取第一条记录）
     */
    public CmsCompany getCompanyInfo() {
        return lambdaQuery().last("LIMIT 1").one();
    }
}
