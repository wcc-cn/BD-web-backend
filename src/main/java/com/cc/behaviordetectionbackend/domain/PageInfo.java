package com.cc.behaviordetectionbackend.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName PageInfo
 * @Description 分页信息实体类
 * @Author cc
 * @Date 2025/11/29 17:14
 * @Version 1.0.0
 */
@Data
@Schema(name = "PageInfo", description = "分页信息")
public class PageInfo {
    /**
     * 页码
     */
    @Schema(description = "页码")
    private Integer page;

    /**
     * 单页条数
     */
    @Schema(description = "单页条数")
    private Integer size;
}
