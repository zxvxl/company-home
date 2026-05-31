package com.hongyuan.cms.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyuan.cms.domain.CmsNewsCategory;
import com.hongyuan.cms.mapper.CmsNewsCategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsNewsCategoryService extends ServiceImpl<CmsNewsCategoryMapper, CmsNewsCategory> {

    /**
     * 获取所有新闻分类（按排序升序）
     */
    public List<CmsNewsCategory> getSortedList() {
        return lambdaQuery()
                .orderByAsc(CmsNewsCategory::getSortOrder)
                .list();
    }
}
