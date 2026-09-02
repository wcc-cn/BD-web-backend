package com.cc.behaviordetectionbackend.dto.rdto;

import com.cc.behaviordetectionbackend.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName UserRDTO
 * @Description 用户 RDTO
 * @Author cc
 * @Date 2025/11/27 21:47
 * @Version 1.0.0
 */
@Data
@Schema(name="UserRDTO", description="用户接口的响应DTO")
public class UserRDTO extends UserDTO {

    @Schema(description = "token")
    private String token;
}
