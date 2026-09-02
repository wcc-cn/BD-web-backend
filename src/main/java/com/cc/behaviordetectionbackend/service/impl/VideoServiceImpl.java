package com.cc.behaviordetectionbackend.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.behaviordetectionbackend.converter.VideoConverter;
import com.cc.behaviordetectionbackend.domain.PageInfo;
import com.cc.behaviordetectionbackend.domain.PageResult;
import com.cc.behaviordetectionbackend.domain.Video;
import com.cc.behaviordetectionbackend.dto.conditionDTO.VideoConditionDTO;
import com.cc.behaviordetectionbackend.dto.rdto.VideoRDTO;
import com.cc.behaviordetectionbackend.repository.VideoMapper;
import com.cc.behaviordetectionbackend.result.Result;
import com.cc.behaviordetectionbackend.service.VideoService;

import com.cc.behaviordetectionbackend.ws.WsModelDetectionServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PrimitiveIterator;

/**
 * @ClassName VideoServiceImpl
 * @Description 视频 Service接口实现类
 * @Author cc
 * @Date 2025/11/29 22:39
 * @Version 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    @Value("${resource.video}")
    private String videoUploadBasePath;

    private final VideoMapper videoMapper;

    private final VideoConverter videoConverter;

    private final WsModelDetectionServer wsModelDetectionServer;

    @Override
    public Result<Boolean> uploadVideo(MultipartFile file) {
        try{
            String fileName = file.getOriginalFilename();
            File dest = new File(videoUploadBasePath + "/" + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            return Result.success(true);
        }catch (Exception e){
            log.error("上传视频失败，出现未知异常, e: " + e.getMessage());
            return Result.error("上传视频失败，出现未知异常");
        }
    }

    @Override
    public Result<Boolean> addVideo(VideoConditionDTO videoConditionDTO) {
        try {
            SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
            videoConditionDTO.getVideoList().stream().forEach(name->{
                Video video = new Video();
                video.setName(name);
                video.init(saTokenInfo.getLoginId().toString());
                videoMapper.insert(video);
            });
            return Result.success(true);
        }catch (Exception e){
            log.error("添加视频失败，出现未知异常, e: " + e.getMessage());
            return Result.error("添加视频失败，出现未知异常");
        }
    }

    @Override
    public Result<PageResult<VideoRDTO>> queryPageVideo(VideoConditionDTO videoConditionDTO,PageInfo pageInfo) {
        try{
            Page<Video> page = new Page<>(pageInfo.getPage(), pageInfo.getSize());
            QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("del_flag", 0)
                    .like(!StringUtils.isEmpty(videoConditionDTO.getName()), "name", videoConditionDTO.getName())
                    .orderByDesc("create_time");
            Page<Video> videoPageList = videoMapper.selectPage(page, queryWrapper);
            PageResult<VideoRDTO> pageResult = new PageResult<>();
            pageResult.setTotal(videoPageList.getTotal());
            pageResult.setData(videoConverter.toRDTOs(videoPageList.getRecords()));
            return Result.success(pageResult);
        }catch (Exception e){
            log.error("查询视频失败，出现未知异常，e:{}", e.getMessage());
            return Result.error("查询视频失败，出现未知异常！");
        }
    }

    @Override
    public Result<Boolean> deleteVideo(Integer videoId) {
        try{
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            Video video = videoMapper.selectById(videoId);
            video.setDelFlag(1);
            video.setUpdateTime(new Date());
            video.setUpdateBy(tokenInfo.getLoginId().toString());
            videoMapper.updateById(video);
            String filePath = videoUploadBasePath+ "/" + video.getName();
            File file = new File(filePath);
            if (file.exists()) {
                if (!file.delete()) {
                    log.info("删除失败：无法删除文件, fileName: " + file.getName());
                }
            } else {
                log.info("文件不存在, fileName: " + file.getName());
            }
            return Result.success(true);
        }catch (Exception e){
            log.error("删除视频失败，出现未知异常，e:{}", e.getMessage());
            return Result.error("删除视频失败，出现未知异常！");
        }
    }

    @Override
    public Result<Boolean> detectVideo(VideoConditionDTO videoConditionDTO) {
        try{
            File videoFile = new File(videoUploadBasePath + "/" + videoConditionDTO.getName());
            String wsl_video_path = "/mnt/d/work/bd/video"; // 由于我这里的python运行在wsl中，所以使用该路径使wsl访问windows文件系统
            if (!videoFile.exists()) {
                return Result.error("检测视频失败，视频文件不存在！");
            }else{
                Map<String, Object> msg = new HashMap<>();
                Map<String, Object> data = new HashMap<>();
                data.put("video_path", videoConditionDTO.getName());
                data.put("sync_id", IdUtil.randomUUID());
                msg.put("cmd", "detect_video");
                msg.put("data", data);
                WsModelDetectionServer.sendInfo(JSON.toJSONString(msg));
//                wsModelDetectionServer.sendMessage(JSON.toJSONString(msg));
            }
            return Result.success(true);
        } catch (Exception e) {
            log.error("检测视频失败，出现未知异常，e:{}", e.getMessage());
            return Result.error("检测视频失败，出现未知异常！");
        }
    }
}
