package com.hongyuan.cms.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyuan.cms.domain.CmsNews;
import com.hongyuan.cms.mapper.CmsNewsMapper;
import com.hongyuan.common.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsNewsService extends ServiceImpl<CmsNewsMapper, CmsNews> {

    /**
     * 前台分页查询新闻（只显示已发布的）
     */
    public PageResult<CmsNews> pageList(Long categoryId, int page, int size) {
        Page<CmsNews> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<CmsNews> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CmsNews::getStatus, 1);
        if (categoryId != null) {
            wrapper.eq(CmsNews::getCategoryId, categoryId);
        }
        wrapper.orderByDesc(CmsNews::getIsTop)
               .orderByDesc(CmsNews::getPublishTime);
        Page<CmsNews> result = page(pageParam, wrapper);
        return new PageResult<>(result.getTotal(), result.getRecords(), result.getCurrent(), result.getSize());
    }

    /**
     * 获取最新N条新闻
     */
    public List<CmsNews> getLatestNews(int limit) {
        return lambdaQuery()
                .eq(CmsNews::getStatus, 1)
                .orderByDesc(CmsNews::getPublishTime)
                .last("LIMIT " + limit)
                .list();
    }

    /**
     * 后台分页查询（不过滤状态）
     */
    public PageResult<CmsNews> adminPageList(Long categoryId, int page, int size) {
        Page<CmsNews> pageParam = new Page<>(page, size);
        LambdaQueryWrapper<CmsNews> wrapper = new LambdaQueryWrapper<>();
        if (categoryId != null) {
            wrapper.eq(CmsNews::getCategoryId, categoryId);
        }
        wrapper.orderByDesc(CmsNews::getCreateTime);
        Page<CmsNews> result = page(pageParam, wrapper);
        return new PageResult<>(result.getTotal(), result.getRecords(), result.getCurrent(), result.getSize());
    }
}
