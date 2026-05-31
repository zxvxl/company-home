package com.hongyuan.cms.controller.admin;

import com.hongyuan.cms.domain.CmsCase;
import com.hongyuan.cms.service.CmsCaseService;
import com.hongyuan.common.PageResult;
import com.hongyuan.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后台-工程案例管理")
@RestController
@RequestMapping("/api/admin/cms/case")
@RequiredArgsConstructor
public class CaseController {

    private final CmsCaseService caseService;

    @Operation(summary = "分页查询案例")
    @GetMapping("/list")
    public Result<PageResult<CmsCase>> list(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Result.success(caseService.adminPageList(categoryId, page, size));
    }

    @Operation(summary = "获取案例详情")
    @GetMapping("/{id}")
    public Result<CmsCase> getById(@PathVariable Long id) {
        return Result.success(caseService.getById(id));
    }

    @Operation(summary = "新增案例")
    @PostMapping
    public Result<Void> add(@Validated @RequestBody CmsCase cmsCase) {
        caseService.save(cmsCase);
        return Result.success();
    }

    @Operation(summary = "修改案例")
    @PutMapping
    public Result<Void> update(@Validated @RequestBody CmsCase cmsCase) {
        caseService.updateById(cmsCase);
        return Result.success();
    }

    @Operation(summary = "删除案例")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        caseService.removeById(id);
        return Result.success();
    }
}
