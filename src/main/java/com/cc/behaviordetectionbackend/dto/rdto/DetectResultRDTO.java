package com.cc.behaviordetectionbackend.dto.rdto;

import com.cc.behaviordetectionbackend.dto.DetectResultDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName DetectResultRDTO
 * @Description 检测结果 RDTO
 * @Author cc
 * @Date 2025/11/30 20:03
 * @Version 1.0.0
 */
@Data
@Schema(name="DetectResultRDTO", description="检测结果接口的响应DTO")
public class DetectResultRDTO extends DetectResultDTO {
}
