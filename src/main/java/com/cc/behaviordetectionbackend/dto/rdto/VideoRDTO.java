package com.cc.behaviordetectionbackend.dto.rdto;

import com.cc.behaviordetectionbackend.dto.VideoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName VideoRDTO
 * @Description 视频 RDTO
 * @Author cc
 * @Date 2025/11/29 23:23
 * @Version 1.0.0
 */
@Data
@Schema(name="VideoRDTO", description="视频接口的响应DTO")
public class VideoRDTO extends VideoDTO {
}
