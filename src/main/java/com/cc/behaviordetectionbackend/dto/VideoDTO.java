package com.cc.behaviordetectionbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName VideoDTO
 * @Description 视频 DTO基类
 * @Author cc
 * @Date 2025/11/29 23:22
 * @Version 1.0.0
 */
@Data
@Schema(name="VideoDTO", description="视频接口的基础DTO")
public class VideoDTO {

    @Schema(description = "视频id")
    private Integer id;

    @Schema(description = "视频文件名称")
    private String name;
}
