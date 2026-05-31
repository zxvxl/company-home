package com.hongyuan.cms.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyuan.cms.domain.CmsMessage;
import com.hongyuan.cms.mapper.CmsMessageMapper;
import org.springframework.stereotype.Service;

@Service
public class CmsMessageService extends ServiceImpl<CmsMessageMapper, CmsMessage> {
}
