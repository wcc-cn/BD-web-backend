package com.cc.behaviordetectionbackend.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.behaviordetectionbackend.converter.DetectResultConverter;
import com.cc.behaviordetectionbackend.domain.DetectResult;
import com.cc.behaviordetectionbackend.domain.PageInfo;
import com.cc.behaviordetectionbackend.domain.PageResult;
import com.cc.behaviordetectionbackend.domain.Video;
import com.cc.behaviordetectionbackend.dto.conditionDTO.DetectResultConditionDTO;
import com.cc.behaviordetectionbackend.dto.rdto.DetectResultRDTO;
import com.cc.behaviordetectionbackend.dto.rdto.UserRDTO;
import com.cc.behaviordetectionbackend.repository.DetectResultMapper;
import com.cc.behaviordetectionbackend.result.Result;
import com.cc.behaviordetectionbackend.service.DetectResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * @ClassName DetectResultServiceImpl
 * @Description 检测结果 Service接口实现层
 * @Author cc
 * @Date 2025/11/30 20:10
 * @Version 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DetectResultServiceImpl implements DetectResultService {

    private final DetectResultMapper detectResultMapper;

    private final DetectResultConverter detectResultConverter;

    @Value("${resource.predict_video}")
    private String predictVideoSavePath;

    @Override
    public Result<PageResult<DetectResultRDTO>> queryDetectResultPage(DetectResultConditionDTO detectResultConditionDTO, PageInfo pageInfo) {
        try{
            Page<DetectResult> page = new Page<>(pageInfo.getPage(), pageInfo.getSize());
            QueryWrapper<DetectResult> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("del_flag", 0)
                    .like(!StringUtils.isEmpty(detectResultConditionDTO.getVideoName()), "video_name", detectResultConditionDTO.getVideoName())
                    .like(!StringUtils.isEmpty(detectResultConditionDTO.getDetectDescription()), "detect_description", detectResultConditionDTO.getDetectDescription())
                    .like(!StringUtils.isEmpty(detectResultConditionDTO.getPredictVideoName()), "predict_video_name", detectResultConditionDTO.getPredictVideoName())
                    .orderByDesc("create_time");
            Page<DetectResult> detectResultPageList = detectResultMapper.selectPage(page, queryWrapper);
            PageResult<DetectResultRDTO> pageResult = new PageResult<>();
            pageResult.setTotal(detectResultPageList.getTotal());
            pageResult.setData(detectResultConverter.toRDTOs(detectResultPageList.getRecords()));
            return Result.success(pageResult);
        } catch (Exception e) {
            log.error("查询识别结果失败，出现未知异常，e:{}", e.getMessage());
            return Result.error("查询识别结果失败，出现未知异常！");
        }
    }

    @Override
    public Result<Boolean> deleteDetectResult(Integer id) {
        try{
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            DetectResult detectResult = detectResultMapper.selectById(id);
            detectResult.setDelFlag(1);
            detectResult.setUpdateTime(new Date());
            detectResult.setUpdateBy(tokenInfo.getLoginId().toString());
            detectResultMapper.updateById(detectResult);
            String filePath = predictVideoSavePath+ "/" + detectResult.getPredictVideoName();
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
}
