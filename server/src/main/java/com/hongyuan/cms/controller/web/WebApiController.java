package com.hongyuan.cms.controller.web;

import com.hongyuan.cms.domain.*;
import com.hongyuan.cms.service.*;
import com.hongyuan.common.PageResult;
import com.hongyuan.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "官网公开API")
@RestController
@RequestMapping("/api/web")
@RequiredArgsConstructor
public class WebApiController {

    private final CmsBannerService bannerService;
    private final CmsCompanyService companyService;
    private final CmsBusinessService businessService;
    private final CmsCaseService caseService;
    private final CmsCaseCategoryService caseCategoryService;
    private final CmsNewsService newsService;
    private final CmsNewsCategoryService newsCategoryService;
    private final CmsPartnerService partnerService;
    private final CmsMessageService messageService;
    private final CmsConfigService configService;

    // ==================== 轮播图 ====================

    @Operation(summary = "获取启用的轮播图列表")
    @GetMapping("/banner/list")
    public Result<List<CmsBanner>> bannerList() {
        return Result.success(bannerService.getEnabledBanners());
    }

    // ==================== 公司信息 ====================

    @Operation(summary = "获取公司信息")
    @GetMapping("/company/info")
    public Result<CmsCompany> companyInfo() {
        return Result.success(companyService.getCompanyInfo());
    }

    // ==================== 业务领域 ====================

    @Operation(summary = "获取业务领域列表")
    @GetMapping("/business/list")
    public Result<List<CmsBusiness>> businessList() {
        return Result.success(businessService.getEnabledList());
    }

    @Operation(summary = "获取业务详情")
    @GetMapping("/business/{id}")
    public Result<CmsBusiness> businessDetail(@PathVariable Long id) {
        return Result.success(businessService.getById(id));
    }

    // ==================== 工程案例 ====================

    @Operation(summary = "获取案例列表（分页+分类筛选）")
    @GetMapping("/case/list")
    public Result<PageResult<CmsCase>> caseList(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Result.success(caseService.pageList(categoryId, page, size));
    }

    @Operation(summary = "获取案例详情")
    @GetMapping("/case/{id}")
    public Result<CmsCase> caseDetail(@PathVariable Long id) {
        return Result.success(caseService.getById(id));
    }

    @Operation(summary = "获取首页推荐案例")
    @GetMapping("/case/featured")
    public Result<List<CmsCase>> caseFeatured() {
        return Result.success(caseService.getFeaturedCases());
    }

    @Operation(summary = "获取案例分类列表")
    @GetMapping("/case/categories")
    public Result<List<CmsCaseCategory>> caseCategories() {
        return Result.success(caseCategoryService.getSortedList());
    }

    // ==================== 新闻动态 ====================

    @Operation(summary = "获取新闻列表（分页+分类）")
    @GetMapping("/news/list")
    public Result<PageResult<CmsNews>> newsList(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Result.success(newsService.pageList(categoryId, page, size));
    }

    @Operation(summary = "获取新闻详情")
    @GetMapping("/news/{id}")
    public Result<CmsNews> newsDetail(@PathVariable Long id) {
        return Result.success(newsService.getById(id));
    }

    @Operation(summary = "获取最新N条新闻")
    @GetMapping("/news/latest")
    public Result<List<CmsNews>> newsLatest(@RequestParam(defaultValue = "6") int limit) {
        return Result.success(newsService.getLatestNews(limit));
    }

    @Operation(summary = "获取新闻分类列表")
    @GetMapping("/news/categories")
    public Result<List<CmsNewsCategory>> newsCategories() {
        return Result.success(newsCategoryService.getSortedList());
    }

    // ==================== 合作伙伴 ====================

    @Operation(summary = "获取合作伙伴列表")
    @GetMapping("/partner/list")
    public Result<List<CmsPartner>> partnerList() {
        return Result.success(partnerService.getEnabledList());
    }

    // ==================== 留言 ====================

    @Operation(summary = "提交留言")
    @PostMapping("/message/submit")
    public Result<Void> submitMessage(@Validated @RequestBody CmsMessage message) {
        message.setIsRead(0);
        messageService.save(message);
        return Result.success();
    }

    // ==================== 站点配置 ====================

    @Operation(summary = "获取首页数字展示配置")
    @GetMapping("/config/stats")
    public Result<Map<String, String>> configStats() {
        return Result.success(configService.getAllConfigMap());
    }
}
