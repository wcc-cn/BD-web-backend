package com.cc.behaviordetectionbackend.controller;

import com.cc.behaviordetectionbackend.domain.PageInfo;
import com.cc.behaviordetectionbackend.domain.PageResult;
import com.cc.behaviordetectionbackend.dto.VideoDTO;
import com.cc.behaviordetectionbackend.dto.conditionDTO.UserConditionDTO;
import com.cc.behaviordetectionbackend.dto.conditionDTO.VideoConditionDTO;
import com.cc.behaviordetectionbackend.dto.rdto.VideoRDTO;
import com.cc.behaviordetectionbackend.result.Result;
import com.cc.behaviordetectionbackend.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName VideoController
 * @Description 视频 Controller层
 * @Author cc
 * @Date 2025/11/29 22:35
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/video")
@Tag(name = "VideoController", description = "视频接口")
@RequiredArgsConstructor
@Slf4j
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/upload")
    @Operation(summary = "上传视频文件")
    public Result<Boolean> upload(@RequestParam("file") MultipartFile file) {
        return videoService.uploadVideo(file);
    }

    @PostMapping("/add")
    @Operation(summary = "新增视频记录")
    public Result<Boolean> addVideo(@RequestBody VideoConditionDTO videoConditionDTO) {
        return videoService.addVideo(videoConditionDTO);
    }

    @GetMapping("/query_video_page")
    @Operation(summary = "分页查询视频文件")
    public Result<PageResult<VideoRDTO>> queryPageVideo(@ModelAttribute VideoConditionDTO videoConditionDTO, @ModelAttribute PageInfo pageInfo) {
        return videoService.queryPageVideo(videoConditionDTO, pageInfo);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除视频文件")
    public Result<Boolean> deleteVideo(@PathVariable Integer id) {
        return videoService.deleteVideo(id);
    }

    @PostMapping("/detect_video")
    @Operation(summary = "检测视频")
    public Result<Boolean> detectVideo(@RequestBody VideoConditionDTO videoConditionDTO) {
        return videoService.detectVideo(videoConditionDTO);
    }



}
