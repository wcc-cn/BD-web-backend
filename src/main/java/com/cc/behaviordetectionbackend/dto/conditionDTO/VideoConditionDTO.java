package com.cc.behaviordetectionbackend.dto.conditionDTO;

import com.cc.behaviordetectionbackend.dto.VideoDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @ClassName VideoConditionDTO
 * @Description 视频 ConditionDTO
 * @Author cc
 * @Date 2025/11/29 23:23
 * @Version 1.0.0
 */
@Data
@Schema(name="VideoConditionDTO", description="视频接口的请求DTO")
public class VideoConditionDTO extends VideoDTO {

    @Schema(description = "视频文件列表")
    private List<String> videoList;
}
