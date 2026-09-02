package com.cc.behaviordetectionbackend.converter;

import com.cc.behaviordetectionbackend.domain.User;
import com.cc.behaviordetectionbackend.domain.Video;
import com.cc.behaviordetectionbackend.dto.conditionDTO.UserConditionDTO;
import com.cc.behaviordetectionbackend.dto.conditionDTO.VideoConditionDTO;
import com.cc.behaviordetectionbackend.dto.rdto.UserRDTO;
import com.cc.behaviordetectionbackend.dto.rdto.VideoRDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName VideoConverter
 * @Description Video 转换器
 * @Author cc
 * @Date 2025/11/29 23:21
 * @Version 1.0.0
 */
@Mapper(componentModel = "spring")
public interface VideoConverter {

    VideoRDTO toVideoRDTO(Video video);

    List<VideoRDTO> toRDTOs(List<Video> video);

    Video toEntity(VideoConditionDTO videoConditionDTO);
}
