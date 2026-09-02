package com.cc.behaviordetectionbackend.service;

import com.cc.behaviordetectionbackend.domain.PageInfo;
import com.cc.behaviordetectionbackend.domain.PageResult;
import com.cc.behaviordetectionbackend.dto.conditionDTO.DetectResultConditionDTO;
import com.cc.behaviordetectionbackend.dto.rdto.DetectResultRDTO;
import com.cc.behaviordetectionbackend.result.Result;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @ClassName DetectResultService
 * @Description 检测结果 Service接口层
 * @Author cc
 * @Date 2025/11/30 20:09
 * @Version 1.0.0
 */
public interface DetectResultService {

    /**
     * 分页查询识别结果
     * @param detectResultConditionDTO
     * @param pageInfo
     * @return
     */
    Result<PageResult<DetectResultRDTO>> queryDetectResultPage(DetectResultConditionDTO detectResultConditionDTO, PageInfo pageInfo);

    /**
     * 删除检测结果
     * @param id
     * @return
     */
    Result<Boolean> deleteDetectResult(Integer id);
}
