package com.cc.behaviordetectionbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName UserDTO
 * @Description 用户 DTO
 * @Author cc
 * @Date 2025/11/27 21:39
 * @Version 1.0.0
 */
@Data
@Schema(name="UserDTO", description="用户接口的基础DTO")
public class UserDTO  {

    @Schema(description = "用户id")
    private Integer id;

    @Schema(description = "账号")
    private String account;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "角色")
    private Integer role;
}
