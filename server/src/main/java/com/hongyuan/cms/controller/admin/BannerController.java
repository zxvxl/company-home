package com.hongyuan.cms.controller.admin;

import com.hongyuan.cms.domain.CmsBanner;
import com.hongyuan.cms.service.CmsBannerService;
import com.hongyuan.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "后台-轮播图管理")
@RestController
@RequestMapping("/api/admin/cms/banner")
@RequiredArgsConstructor
public class BannerController {

    private final CmsBannerService bannerService;

    @Operation(summary = "获取轮播图列表")
    @GetMapping("/list")
    public Result<List<CmsBanner>> list() {
        List<CmsBanner> list = bannerService.lambdaQuery()
                .orderByAsc(CmsBanner::getSortOrder)
                .list();
        return Result.success(list);
    }

    @Operation(summary = "获取轮播图详情")
    @GetMapping("/{id}")
    public Result<CmsBanner> getById(@PathVariable Long id) {
        return Result.success(bannerService.getById(id));
    }

    @Operation(summary = "新增轮播图")
    @PostMapping
    public Result<Void> add(@Validated @RequestBody CmsBanner banner) {
        bannerService.save(banner);
        return Result.success();
    }

    @Operation(summary = "修改轮播图")
    @PutMapping
    public Result<Void> update(@Validated @RequestBody CmsBanner banner) {
        bannerService.updateById(banner);
        return Result.success();
    }

    @Operation(summary = "删除轮播图")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        bannerService.removeById(id);
        return Result.success();
    }
}
