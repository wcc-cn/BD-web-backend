package com.cc.behaviordetectionbackend.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.behaviordetectionbackend.domain.DetectResult;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName DetectResultMapper
 * @Description 检测结果 dao层
 * @Author cc
 * @Date 2025/11/30 19:59
 * @Version 1.0.0
 */
@Mapper
public interface DetectResultMapper extends BaseMapper<DetectResult> {
}
