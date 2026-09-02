package com.cc.behaviordetectionbackend.converter;

import com.cc.behaviordetectionbackend.domain.DetectResult;
import com.cc.behaviordetectionbackend.dto.conditionDTO.DetectResultConditionDTO;
import com.cc.behaviordetectionbackend.dto.rdto.DetectResultRDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName DetectResultConverter
 * @Description 检测结果转换器
 * @Author cc
 * @Date 2025/11/30 20:00
 * @Version 1.0.0
 */
@Mapper(componentModel = "spring")
public interface DetectResultConverter {
    DetectResultRDTO toDetectResultRDTO(DetectResult detectResult);

    List<DetectResultRDTO> toRDTOs(List<DetectResult> detectResult);

    DetectResult toEntity(DetectResultConditionDTO detectResultConditionDTO);
}
