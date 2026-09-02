package com.cc.behaviordetectionbackend.dto.conditionDTO;

import com.cc.behaviordetectionbackend.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName UserConditionDTO
 * @Description 用户 conditionDTO
 * @Author cc
 * @Date 2025/11/27 21:38
 * @Version 1.0.0
 */
@Data
@Schema(name="UserConditionDTO", description="用户接口的请求DTO")
public class UserConditionDTO extends UserDTO {

}
