package com.cc.behaviordetectionbackend.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.cc.behaviordetectionbackend.domain.PageInfo;
import com.cc.behaviordetectionbackend.domain.PageResult;
import com.cc.behaviordetectionbackend.dto.conditionDTO.UserConditionDTO;
import com.cc.behaviordetectionbackend.dto.rdto.UserRDTO;
import com.cc.behaviordetectionbackend.result.Result;
import com.cc.behaviordetectionbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Description 用户接口 controller层
 * @Author cc
 * @Date 2025/11/27 21:12
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "用户接口")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "登录")
    public Result<UserRDTO> doLogin(@RequestBody UserConditionDTO userConditionDTO) {
        return userService.doLogin(userConditionDTO);
    }

    @PostMapping("/edit_user")
    @Operation(summary = "编辑用户")
    public Result<UserRDTO> editUser(@RequestBody UserConditionDTO userConditionDTO) {
        return userService.editUser(userConditionDTO);
    }

    @GetMapping("/query_user_page")
    @Operation(summary = "分页查询用户")
    public Result<PageResult<UserRDTO>> editUser(@ModelAttribute UserConditionDTO userConditionDTO, @ModelAttribute PageInfo pageInfo) {
        return userService.queryUserPage(userConditionDTO,pageInfo);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除用户")
    public Result<Boolean> deleteUserById(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }

}
