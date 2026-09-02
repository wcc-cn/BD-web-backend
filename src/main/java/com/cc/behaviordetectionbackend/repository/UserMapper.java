package com.cc.behaviordetectionbackend.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.behaviordetectionbackend.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description 用户 dao层
 * @Author cc
 * @Date 2025/11/29 11:25
 * @Version 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
