package com.hongyuan.cms.controller.admin;

import com.hongyuan.cms.domain.CmsPartner;
import com.hongyuan.cms.service.CmsPartnerService;
import com.hongyuan.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "后台-合作伙伴管理")
@RestController
@RequestMapping("/api/admin/cms/partner")
@RequiredArgsConstructor
public class PartnerController {

    private final CmsPartnerService partnerService;

    @Operation(summary = "获取合作伙伴列表")
    @GetMapping("/list")
    public Result<List<CmsPartner>> list() {
        List<CmsPartner> list = partnerService.lambdaQuery()
                .orderByAsc(CmsPartner::getSortOrder)
                .list();
        return Result.success(list);
    }

    @Operation(summary = "获取合作伙伴详情")
    @GetMapping("/{id}")
    public Result<CmsPartner> getById(@PathVariable Long id) {
        return Result.success(partnerService.getById(id));
    }

    @Operation(summary = "新增合作伙伴")
    @PostMapping
    public Result<Void> add(@Validated @RequestBody CmsPartner partner) {
        partnerService.save(partner);
        return Result.success();
    }

    @Operation(summary = "修改合作伙伴")
    @PutMapping
    public Result<Void> update(@Validated @RequestBody CmsPartner partner) {
        partnerService.updateById(partner);
        return Result.success();
    }

    @Operation(summary = "删除合作伙伴")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        partnerService.removeById(id);
        return Result.success();
    }
}
