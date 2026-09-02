package com.cc.behaviordetectionbackend.service;

import com.cc.behaviordetectionbackend.domain.PageInfo;
import com.cc.behaviordetectionbackend.domain.PageResult;
import com.cc.behaviordetectionbackend.dto.conditionDTO.UserConditionDTO;
import com.cc.behaviordetectionbackend.dto.rdto.UserRDTO;
import com.cc.behaviordetectionbackend.result.Result;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * @ClassName UserService
 * @Description 用户 Service接口
 * @Author cc
 * @Date 2025/11/29 11:32
 * @Version 1.0.0
 */
public interface UserService {

    /**
     * 登录
     * @param userConditionDTO
     * @return
     */
    Result<UserRDTO> doLogin(UserConditionDTO userConditionDTO);

    /**
     * 编辑用户
     * @param userConditionDTO
     * @return
     */
    Result<UserRDTO> editUser(UserConditionDTO userConditionDTO);

    /**
     * 分页查询用户
     * @param userConditionDTO
     * @param pageInfo
     * @return
     */
    Result<PageResult<UserRDTO>> queryUserPage(UserConditionDTO userConditionDTO, PageInfo pageInfo);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    Result<Boolean> deleteUserById(Integer id);
}
