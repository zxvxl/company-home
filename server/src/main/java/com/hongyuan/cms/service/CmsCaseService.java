package com.hongyuan.cms.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyuan.cms.domain.CmsCase;
import com.hongyuan.cms.mapper.CmsCaseMapper;
import com.hongyuan.common.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsCaseService extends ServiceImpl<CmsCaseMapper, CmsCase> {

    /**
     * 分页查询案例列表（支持分类筛选）
     */
    public PageResult<CmsCase> pageList(Long categoryId, int page, int size) {
        Page<CmsCase> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<CmsCase> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CmsCase::getStatus, 1);
        if (categoryId != null) {
            wrapper.eq(CmsCase::getCategoryId, categoryId);
        }
        wrapper.orderByAsc(CmsCase::getSortOrder);
        Page<CmsCase> result = page(pageParam, wrapper);
        return new PageResult<>(result.getTotal(), result.getRecords(), result.getCurrent(), result.getSize());
    }

    /**
     * 获取首页推荐案例
     */
    public List<CmsCase> getFeaturedCases() {
        return lambdaQuery()
                .eq(CmsCase::getStatus, 1)
                .eq(CmsCase::getIsFeatured, 1)
                .orderByAsc(CmsCase::getSortOrder)
                .list();
    }

    /**
     * 后台分页查询（不过滤状态）
     */
    public PageResult<CmsCase> adminPageList(Long categoryId, int page, int size) {
        Page<CmsCase> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<CmsCase> wrapper = new LambdaQueryWrapper<>();
        if (categoryId != null) {
            wrapper.eq(CmsCase::getCategoryId, categoryId);
        }
        wrapper.orderByDesc(CmsCase::getCreateTime);
        Page<CmsCase> result = page(pageParam, wrapper);
        return new PageResult<>(result.getTotal(), result.getRecords(), result.getCurrent(), result.getSize());
    }
}
