package com.hongyuan.cms.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyuan.cms.domain.CmsCaseCategory;
import com.hongyuan.cms.mapper.CmsCaseCategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsCaseCategoryService extends ServiceImpl<CmsCaseCategoryMapper, CmsCaseCategory> {

    /**
     * 获取所有案例分类（按排序升序）
     */
    public List<CmsCaseCategory> getSortedList() {
        return lambdaQuery()
                .orderByAsc(CmsCaseCategory::getSortOrder)
                .list();
    }
}
