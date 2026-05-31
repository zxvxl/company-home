package com.hongyuan.cms.controller.admin;

import com.hongyuan.cms.domain.CmsCompany;
import com.hongyuan.cms.service.CmsCompanyService;
import com.hongyuan.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后台-公司信息管理")
@RestController
@RequestMapping("/api/admin/cms/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CmsCompanyService companyService;

    @Operation(summary = "获取公司信息")
    @GetMapping("/info")
    public Result<CmsCompany> getInfo() {
        return Result.success(companyService.getCompanyInfo());
    }

    @Operation(summary = "更新公司信息")
    @PutMapping
    public Result<Void> update(@Validated @RequestBody CmsCompany company) {
        if (company.getId() == null) {
            companyService.save(company);
        } else {
            companyService.updateById(company);
        }
        return Result.success();
    }
}
