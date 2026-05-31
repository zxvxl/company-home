package com.hongyuan.cms.controller.admin;

import com.hongyuan.cms.domain.CmsConfig;
import com.hongyuan.cms.service.CmsConfigService;
import com.hongyuan.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "后台-站点配置管理")
@RestController
@RequestMapping("/api/admin/cms/config")
@RequiredArgsConstructor
public class ConfigController {

    private final CmsConfigService configService;

    @Operation(summary = "获取所有配置")
    @GetMapping("/list")
    public Result<List<CmsConfig>> list() {
        return Result.success(configService.list());
    }

    @Operation(summary = "获取配置详情")
    @GetMapping("/{id}")
    public Result<CmsConfig> getById(@PathVariable Long id) {
        return Result.success(configService.getById(id));
    }

    @Operation(summary = "新增配置")
    @PostMapping
    public Result<Void> add(@Validated @RequestBody CmsConfig config) {
        configService.save(config);
        return Result.success();
    }

    @Operation(summary = "修改配置")
    @PutMapping
    public Result<Void> update(@Validated @RequestBody CmsConfig config) {
        configService.updateById(config);
        return Result.success();
    }

    @Operation(summary = "删除配置")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        configService.removeById(id);
        return Result.success();
    }
}
