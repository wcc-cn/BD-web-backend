package com.cc.behaviordetectionbackend.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName PageResult
 * @Description 分页结果实体类
 * @Author cc
 * @Date 2025/11/29 17:26
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "PageResult", description = "分页结果实体类")
public class PageResult<T> implements Serializable {
    /**
     * 总条数
     */
    @Schema(description = "总条数")
    private Long total;
    /**
     * 分页详细数据
     */
    @Schema(description = "分页详细数据")
    private List<T> data;
}
