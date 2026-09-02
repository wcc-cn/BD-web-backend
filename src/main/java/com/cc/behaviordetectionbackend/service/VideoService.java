package com.cc.behaviordetectionbackend.service;

import com.cc.behaviordetectionbackend.domain.PageInfo;
import com.cc.behaviordetectionbackend.domain.PageResult;
import com.cc.behaviordetectionbackend.dto.conditionDTO.VideoConditionDTO;
import com.cc.behaviordetectionbackend.dto.rdto.VideoRDTO;
import com.cc.behaviordetectionbackend.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName VideoService
 * @Description 视频 Service接口
 * @Author cc
 * @Date 2025/11/29 22:37
 * @Version 1.0.0
 */
public interface VideoService {
    /**
     * 上传视频文件
     * @param file
     * @return
     */
    Result<Boolean> uploadVideo(MultipartFile file);

    /**
     * 新增视频记录
     * @param videoConditionDTO
     * @return
     */
    Result<Boolean> addVideo(VideoConditionDTO videoConditionDTO);

    /**
     * 分页查询视频文件
     * @param videoConditionDTO
     * @return
     */
    Result<PageResult<VideoRDTO>> queryPageVideo(VideoConditionDTO videoConditionDTO, PageInfo pageInfo);

    /**
     * 删除视频文件
     * @param videoId
     * @return
     */
    Result<Boolean> deleteVideo(Integer videoId);

    /**
     * 对视频文件进行模型检测
     * @param videoConditionDTO
     * @return
     */
    Result<Boolean> detectVideo(VideoConditionDTO videoConditionDTO);
}
