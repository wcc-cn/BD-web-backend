package com.cc.behaviordetectionbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

/**
 * @ClassName DetectResultDTO
 * @Description 检测结果 DTO基类
 * @Author cc
 * @Date 2025/11/30 20:01
 * @Version 1.0.0
 */
@Data
@Schema(name="DetectResultDTO", description="检测结果的基础DTO")
public class DetectResultDTO {
    @Schema(description="id")
    private Integer id;

    @Schema(description="源文件名")
    private String videoName;

    @Schema(description="检测结果描述")
    private String detectDescription;

    @Schema(description="预测视频文件名")
    private String predictVideoName;
}
