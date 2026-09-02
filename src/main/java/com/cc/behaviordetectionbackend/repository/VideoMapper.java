package com.cc.behaviordetectionbackend.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.behaviordetectionbackend.domain.Video;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName VideoMapper
 * @Description 视频 dao层
 * @Author cc
 * @Date 2025/11/29 23:31
 * @Version 1.0.0
 */
@Mapper
public interface VideoMapper extends BaseMapper<Video> {
}
