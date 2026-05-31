package com.hongyuan.cms.controller.admin;

import com.hongyuan.cms.domain.CmsBusiness;
import com.hongyuan.cms.service.CmsBusinessService;
import com.hongyuan.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "后台-业务领域管理")
@RestController
@RequestMapping("/api/admin/cms/business")
@RequiredArgsConstructor
public class BusinessController {

    private final CmsBusinessService businessService;

    @Operation(summary = "获取业务领域列表")
    @GetMapping("/list")
    public Result<List<CmsBusiness>> list() {
        List<CmsBusiness> list = businessService.lambdaQuery()
                .orderByAsc(CmsBusiness::getSortOrder)
                .list();
        return Result.success(list);
    }

    @Operation(summary = "获取业务领域详情")
    @GetMapping("/{id}")
    public Result<CmsBusiness> getById(@PathVariable Long id) {
        return Result.success(businessService.getById(id));
    }

    @Operation(summary = "新增业务领域")
    @PostMapping
    public Result<Void> add(@Validated @RequestBody CmsBusiness business) {
        businessService.save(business);
        return Result.success();
    }

    @Operation(summary = "修改业务领域")
    @PutMapping
    public Result<Void> update(@Validated @RequestBody CmsBusiness business) {
        businessService.updateById(business);
        return Result.success();
    }

    @Operation(summary = "删除业务领域")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        businessService.removeById(id);
        return Result.success();
    }
}
