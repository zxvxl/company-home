package com.hongyuan.cms.controller.admin;

import com.hongyuan.cms.domain.CmsNews;
import com.hongyuan.cms.service.CmsNewsService;
import com.hongyuan.common.PageResult;
import com.hongyuan.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后台-新闻管理")
@RestController
@RequestMapping("/api/admin/cms/news")
@RequiredArgsConstructor
public class NewsController {

    private final CmsNewsService newsService;

    @Operation(summary = "分页查询新闻")
    @GetMapping("/list")
    public Result<PageResult<CmsNews>> list(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Result.success(newsService.adminPageList(categoryId, page, size));
    }

    @Operation(summary = "获取新闻详情")
    @GetMapping("/{id}")
    public Result<CmsNews> getById(@PathVariable Long id) {
        return Result.success(newsService.getById(id));
    }

    @Operation(summary = "新增新闻")
    @PostMapping
    public Result<Void> add(@Validated @RequestBody CmsNews news) {
        newsService.save(news);
        return Result.success();
    }

    @Operation(summary = "修改新闻")
    @PutMapping
    public Result<Void> update(@Validated @RequestBody CmsNews news) {
        newsService.updateById(news);
        return Result.success();
    }

    @Operation(summary = "删除新闻")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        newsService.removeById(id);
        return Result.success();
    }
}
