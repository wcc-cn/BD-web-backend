package com.cc.behaviordetectionbackend.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.behaviordetectionbackend.converter.UserConverter;
import com.cc.behaviordetectionbackend.domain.PageInfo;
import com.cc.behaviordetectionbackend.domain.PageResult;
import com.cc.behaviordetectionbackend.domain.User;
import com.cc.behaviordetectionbackend.dto.conditionDTO.UserConditionDTO;
import com.cc.behaviordetectionbackend.dto.rdto.UserRDTO;
import com.cc.behaviordetectionbackend.repository.UserMapper;
import com.cc.behaviordetectionbackend.result.Result;
import com.cc.behaviordetectionbackend.service.UserService;
import com.cc.behaviordetectionbackend.utils.AesUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.ap.internal.model.assignment.UpdateWrapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description 用户 Service接口实现
 * @Author cc
 * @Date 2025/11/29 11:34
 * @Version 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserConverter userConverter;

    @Override
    public Result<UserRDTO> doLogin(UserConditionDTO userConditionDTO) {
        try{
            String pwd_encode =  AesUtil.Encrypt(userConditionDTO.getPassword());
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account", userConditionDTO.getAccount())
                    .eq("password", pwd_encode);
            User user = userMapper.selectOne(queryWrapper);
            if(ObjectUtil.isEmpty(user)){
                log.info("登录失败，未找到对应用户 account：{}, pwd: {}", userConditionDTO.getAccount(), userConditionDTO.getPassword());
                return Result.error("登陆失败，账号或密码错误!");
            }
            UserRDTO userRDTO = userConverter.toUserRDTO(user);
            StpUtil.login(user.getId());
            SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
            userRDTO.setToken(saTokenInfo.getTokenValue());
            return Result.success(userRDTO);
        } catch (Exception e) {
            log.error("登录失败，出现未知异常，e:{}", e.getMessage());
            return Result.error("登录失败，服务器发生未知异常！");
        }
    }

    @Override
    public Result<UserRDTO> editUser(UserConditionDTO userConditionDTO) {
        try{
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            if(ObjectUtil.isEmpty(userConditionDTO.getId())){
                //add user
                User user = userConverter.toEntity(userConditionDTO);
                user.setPassword(AesUtil.Encrypt(userConditionDTO.getPassword()));
                user.init(tokenInfo.getLoginId().toString());
                userMapper.insert(user);
            }else{
                //update user
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("account", userConditionDTO.getAccount()).eq("id", userConditionDTO.getId());
                User origin_user = userMapper.selectOne(queryWrapper);
                if(ObjectUtil.isEmpty(origin_user)){
                    log.info("编辑用户失败，未找到该用户，userId:{}, account：{}", userConditionDTO.getId(), userConditionDTO.getAccount());
                    return Result.error("编辑失败，未找到对应用户");
                }
                origin_user.setUpdateBy(tokenInfo.getLoginId().toString());
                origin_user.setUpdateTime(new Date());
                origin_user.setPassword(AesUtil.Encrypt(userConditionDTO.getPassword()));
                origin_user.setRole(userConditionDTO.getRole());
                origin_user.setUsername(userConditionDTO.getUsername());
                userMapper.updateById(origin_user);
            }
            return Result.success();
        }catch (Exception e){
            log.error("编辑用户失败，出现未知异常，e:{}", e.getMessage());
            return Result.error("编辑用户失败，出现未知异常！");
        }
    }

    @Override
    public Result<PageResult<UserRDTO>> queryUserPage(UserConditionDTO userConditionDTO, PageInfo pageInfo) {
        try{
            Page<User> page = new Page<>(pageInfo.getPage(), pageInfo.getSize());
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("del_flag", 0)
                    .like(!StringUtils.isEmpty(userConditionDTO.getAccount()), "account", userConditionDTO.getAccount())
                    .like(!StringUtils.isEmpty(userConditionDTO.getUsername()), "username", userConditionDTO.getUsername())
                    .orderByDesc("create_time");
            Page<User> userPageList = userMapper.selectPage(page, queryWrapper);
            PageResult<UserRDTO> pageResult = new PageResult<>();
            pageResult.setTotal(userPageList.getTotal());
            pageResult.setData(userConverter.toRDTOs(userPageList.getRecords()));
            return Result.success(pageResult);
        }catch (Exception e){
            log.error("查询用户失败，出现未知异常，e:{}", e.getMessage());
            return Result.error("查询用户失败，出现未知异常！");
        }
    }

    @Override
    public Result<Boolean> deleteUserById(Integer id) {
        try{
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            User user = userMapper.selectById(id);
            user.setDelFlag(1);
            user.setUpdateTime(new Date());
            user.setUpdateBy(tokenInfo.getLoginId().toString());
            userMapper.updateById(user);
            return Result.success(true);
        }catch (Exception e){
            log.error("删除用户失败，出现未知异常，e:{}", e.getMessage());
            return Result.error("删除用户失败，出现未知异常！");
        }
    }
}
