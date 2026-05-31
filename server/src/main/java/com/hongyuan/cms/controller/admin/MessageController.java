package com.hongyuan.cms.controller.admin;

import com.hongyuan.cms.domain.CmsMessage;
import com.hongyuan.cms.service.CmsMessageService;
import com.hongyuan.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "后台-留言管理")
@RestController
@RequestMapping("/api/admin/cms/message")
@RequiredArgsConstructor
public class MessageController {

    private final CmsMessageService messageService;

    @Operation(summary = "获取留言列表")
    @GetMapping("/list")
    public Result<List<CmsMessage>> list(@RequestParam(required = false) Integer isRead) {
        List<CmsMessage> list;
        if (isRead != null) {
            list = messageService.lambdaQuery()
                    .eq(CmsMessage::getIsRead, isRead)
                    .orderByDesc(CmsMessage::getCreateTime)
                    .list();
        } else {
            list = messageService.lambdaQuery()
                    .orderByDesc(CmsMessage::getCreateTime)
                    .list();
        }
        return Result.success(list);
    }

    @Operation(summary = "获取留言详情")
    @GetMapping("/{id}")
    public Result<CmsMessage> getById(@PathVariable Long id) {
        return Result.success(messageService.getById(id));
    }

    @Operation(summary = "标记为已读")
    @PutMapping("/{id}/read")
    public Result<Void> markRead(@PathVariable Long id) {
        CmsMessage message = new CmsMessage();
        message.setId(id);
        message.setIsRead(1);
        messageService.updateById(message);
        return Result.success();
    }

    @Operation(summary = "回复留言")
    @PutMapping("/{id}/reply")
    public Result<Void> reply(@PathVariable Long id, @RequestBody CmsMessage message) {
        CmsMessage update = new CmsMessage();
        update.setId(id);
        update.setReplyContent(message.getReplyContent());
        update.setIsRead(1);
        messageService.updateById(update);
        return Result.success();
    }

    @Operation(summary = "删除留言")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        messageService.removeById(id);
        return Result.success();
    }
}
