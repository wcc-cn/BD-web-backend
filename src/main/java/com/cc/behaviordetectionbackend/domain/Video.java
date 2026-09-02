package com.cc.behaviordetectionbackend.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @ClassName Video
 * @Description 视频实体类
 * @Author cc
 * @Date 2025/11/29 23:17
 * @Version 1.0.0
 */
@Data
@Alias(value = "bd_video")
@TableName("bd_video")
public class Video extends BaseDomain{

    //视频文件名称
    private String name;

    public void init(String userId)
    {
        this.setUpdateTime(new Date());
        this.setUpdateBy(userId);
        this.setCreateTime(new Date());
        this.setCreateBy(userId);
        this.setDelFlag(0);
    }
}
