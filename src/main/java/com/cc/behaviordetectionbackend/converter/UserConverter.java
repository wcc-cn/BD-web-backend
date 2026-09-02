package com.cc.behaviordetectionbackend.converter;

import com.cc.behaviordetectionbackend.domain.User;
import com.cc.behaviordetectionbackend.dto.conditionDTO.UserConditionDTO;
import com.cc.behaviordetectionbackend.dto.rdto.UserRDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName UserConverter
 * @Description User 转换器
 * @Author cc
 * @Date 2025/11/29 10:55
 * @Version 1.0.0
 */
@Mapper(componentModel = "spring")
public interface UserConverter {
    UserRDTO toUserRDTO(User user);

    List<UserRDTO> toRDTOs(List<User> users);

    User toEntity(UserConditionDTO userConditionDTO);
}
