package com.cc.behaviordetectionbackend.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @ClassName User
 * @Description 用户实体类
 * @Author cc
 * @Date 2025/11/29 10:56
 * @Version 1.0.0
 */
@Data
@Alias(value = "bd_user")
@TableName("bd_user")
public class User extends BaseDomain{

    //账号
    private String account;

    //用户名
    private String username;

    //密码
    private String password;

    //角色（0：管理员，1：平台使用人员）
    private Integer role;

    public void init(String userId)
    {
        this.setUpdateTime(new Date());
        this.setUpdateBy(userId);
        this.setCreateTime(new Date());
        this.setCreateBy(userId);
        this.setDelFlag(0);
    }

}
