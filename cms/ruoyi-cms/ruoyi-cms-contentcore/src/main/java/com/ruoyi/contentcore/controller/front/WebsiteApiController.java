package com.ruoyi.contentcore.controller.front;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.domain.R;
import com.ruoyi.common.security.web.BaseRestController;
import com.ruoyi.contentcore.domain.CmsContent;
import com.ruoyi.contentcore.service.ICatalogService;
import com.ruoyi.contentcore.service.IContentService;
import com.ruoyi.contentcore.service.ISiteService;

import lombok.RequiredArgsConstructor;

/**
 * 官网前台公开API
 * 供 Nuxt3 前台调用，无需认证
 * 
 * 注意：此 Controller 不添加 @Priv 注解，因此不会触发 SaToken 认证拦截
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/web")
public class WebsiteApiController extends BaseRestController {

    private final ISiteService siteService;
    private final ICatalogService catalogService;
    private final IContentService contentService;

    /**
     * 获取站点信息
     */
    @GetMapping("/site/info")
    public R<?> getSiteInfo() {
        return R.ok(siteService.list().stream().findFirst().orElse(null));
    }

    /**
     * 获取栏目列表
     */
    @GetMapping("/catalogs")
    public R<?> getCatalogs(@RequestParam(required = false) Long parentId) {
        return R.ok(catalogService.list());
    }

    /**
     * 获取栏目下的内容列表
     */
    @GetMapping("/catalog/{catalogId}/contents")
    public R<?> getContentsByCatalog(
            @PathVariable Long catalogId,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        return R.ok(contentService.lambdaQuery()
                .eq(CmsContent::getCatalogId, catalogId)
                .eq(CmsContent::getStatus, "30") // 已发布
                .orderByDesc(CmsContent::getPublishDate)
                .page(new Page<>(pageNum, pageSize)));
    }

    /**
     * 获取内容详情
     */
    @GetMapping("/content/{contentId}")
    public R<?> getContentDetail(@PathVariable Long contentId) {
        return R.ok(contentService.getById(contentId));
    }

    /**
     * 获取推荐内容（首页用）
     */
    @GetMapping("/contents/featured")
    public R<?> getFeaturedContents(@RequestParam(defaultValue = "6") int limit) {
        return R.ok(contentService.lambdaQuery()
                .eq(CmsContent::getStatus, "30")
                .orderByDesc(CmsContent::getSortFlag)
                .last("LIMIT " + limit)
                .list());
    }
}
