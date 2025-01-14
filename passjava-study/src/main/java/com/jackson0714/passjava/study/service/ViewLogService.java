package com.jackson0714.passjava.study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.study.entity.ViewLogEntity;

import java.util.Map;

/**
 * 学习-用户学习浏览记录表
 *
 * @author jackson0714
 * @email jackson0585@163.com
 * @date 2020-04-15 17:50:49
 */
public interface ViewLogService extends IService<ViewLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

