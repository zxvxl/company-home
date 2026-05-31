package com.ruoyi.contentcore.controller.front;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.domain.R;
import com.ruoyi.common.security.web.BaseRestController;
import com.ruoyi.contentcore.domain.CmsCatalog;
import com.ruoyi.contentcore.domain.CmsContent;
import com.ruoyi.contentcore.domain.CmsSite;
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

    /** 已发布状态 */
    private static final String STATUS_PUBLISHED = "30";

    /**
     * 获取站点信息
     */
    @GetMapping("/site/info")
    public R<?> getSiteInfo() {
        List<CmsSite> sites = siteService.list();
        if (sites == null || sites.isEmpty()) {
            return R.ok();
        }
        return R.ok(sites.get(0));
    }

    /**
     * 获取栏目列表（按站点筛选）
     */
    @GetMapping("/catalogs")
    public R<?> getCatalogs(@RequestParam(required = false) Long siteId) {
        if (siteId != null) {
            return R.ok(catalogService.lambdaQuery()
                    .eq(CmsCatalog::getSiteId, siteId)
                    .orderByAsc(CmsCatalog::getSortFlag)
                    .list());
        }
        return R.ok(catalogService.list());
    }

    /**
     * 获取栏目下的内容列表（仅返回已发布内容）
     */
    @GetMapping("/catalog/{catalogId}/contents")
    public R<?> getContentsByCatalog(
            @PathVariable Long catalogId,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<CmsContent> page = contentService.lambdaQuery()
                .eq(CmsContent::getCatalogId, catalogId)
                .eq(CmsContent::getStatus, STATUS_PUBLISHED)
                .orderByDesc(CmsContent::getTopFlag)
                .orderByDesc(CmsContent::getPublishDate)
                .page(new Page<>(pageNum, pageSize));
        return R.ok(page);
    }

    /**
     * 获取内容详情（仅返回已发布内容）
     */
    @GetMapping("/content/{contentId}")
    public R<?> getContentDetail(@PathVariable Long contentId) {
        CmsContent content = contentService.getById(contentId);
        // 安全检查：只返回已发布的内容
        if (content == null || !STATUS_PUBLISHED.equals(content.getStatus())) {
            return R.fail("内容不存在");
        }
        return R.ok(content);
    }

    /**
     * 获取推荐/置顶内容（首页用）
     */
    @GetMapping("/contents/featured")
    public R<?> getFeaturedContents(
            @RequestParam(required = false) Long siteId,
            @RequestParam(defaultValue = "6") int limit) {
        var query = contentService.lambdaQuery()
                .eq(CmsContent::getStatus, STATUS_PUBLISHED);
        if (siteId != null) {
            query.eq(CmsContent::getSiteId, siteId);
        }
        List<CmsContent> list = query
                .orderByDesc(CmsContent::getSortFlag)
                .last("LIMIT " + limit)
                .list();
        return R.ok(list);
    }

    /**
     * 按栏目别名获取内容（方便前台按别名调用）
     */
    @GetMapping("/catalog/alias/{alias}/contents")
    public R<?> getContentsByCatalogAlias(
            @PathVariable String alias,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        // 先查站点（取第一个）
        List<CmsSite> sites = siteService.list();
        if (sites == null || sites.isEmpty()) {
            return R.ok(new Page<>());
        }
        CmsCatalog catalog = catalogService.getCatalogByAlias(sites.get(0).getSiteId(), alias);
        if (catalog == null) {
            return R.ok(new Page<>());
        }
        Page<CmsContent> page = contentService.lambdaQuery()
                .eq(CmsContent::getCatalogId, catalog.getCatalogId())
                .eq(CmsContent::getStatus, STATUS_PUBLISHED)
                .orderByDesc(CmsContent::getTopFlag)
                .orderByDesc(CmsContent::getPublishDate)
                .page(new Page<>(pageNum, pageSize));
        return R.ok(page);
    }
}
