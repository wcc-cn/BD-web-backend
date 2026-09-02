package com.cc.behaviordetectionbackend.controller;

import com.cc.behaviordetectionbackend.domain.PageInfo;
import com.cc.behaviordetectionbackend.domain.PageResult;
import com.cc.behaviordetectionbackend.dto.conditionDTO.DetectResultConditionDTO;
import com.cc.behaviordetectionbackend.dto.rdto.DetectResultRDTO;
import com.cc.behaviordetectionbackend.result.Result;
import com.cc.behaviordetectionbackend.service.DetectResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DetectResultController
 * @Description 检测结果 Controller层
 * @Author cc
 * @Date 2025/11/30 20:08
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/detect_result")
@Tag(name = "DetectResultController", description = "检测结果接口")
@RequiredArgsConstructor
@Slf4j
public class DetectResultController {

    private final DetectResultService detectResultService;

    @GetMapping("/query_detect_result_page")
    @Operation(summary="分页查询识别结果")
    public Result<PageResult<DetectResultRDTO>> queryDetectResultPage(@ModelAttribute DetectResultConditionDTO detectResultConditionDTO, @ModelAttribute PageInfo pageInfo) {
        return detectResultService.queryDetectResultPage(detectResultConditionDTO, pageInfo);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除视频文件")
    public Result<Boolean> deleteDetectResult(@PathVariable Integer id) {
        return detectResultService.deleteDetectResult(id);
    }
}
