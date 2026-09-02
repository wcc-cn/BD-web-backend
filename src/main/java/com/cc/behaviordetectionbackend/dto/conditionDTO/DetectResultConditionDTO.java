package com.cc.behaviordetectionbackend.dto.conditionDTO;

import com.cc.behaviordetectionbackend.dto.DetectResultDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName DetectResultConditionDTO
 * @Description 检测结果 ConditionDTO
 * @Author cc
 * @Date 2025/11/30 20:04
 * @Version 1.0.0
 */
@Data
@Schema(name="DetectResultConditionDTO", description="检测结果接口的请求DTO")
public class DetectResultConditionDTO extends DetectResultDTO {
}
