package com.hongyuan.cms.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongyuan.cms.domain.CmsConfig;
import com.hongyuan.cms.mapper.CmsConfigMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CmsConfigService extends ServiceImpl<CmsConfigMapper, CmsConfig> {

    /**
     * 根据key获取配置值
     */
    public String getValueByKey(String key) {
        CmsConfig config = lambdaQuery()
                .eq(CmsConfig::getConfigKey, key)
                .one();
        return config != null ? config.getConfigValue() : null;
    }

    /**
     * 获取所有配置（以Map形式返回）
     */
    public Map<String, String> getAllConfigMap() {
        List<CmsConfig> list = list();
        Map<String, String> map = new HashMap<>();
        for (CmsConfig config : list) {
            map.put(config.getConfigKey(), config.getConfigValue());
        }
        return map;
    }
}
