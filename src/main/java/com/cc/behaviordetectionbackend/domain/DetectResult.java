package com.cc.behaviordetectionbackend.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @ClassName DetectResult
 * @Description 检测结果实体类
 * @Author cc
 * @Date 2025/11/30 19:55
 * @Version 1.0.0
 */
@Data
@Alias(value = "bd_detect_result")
@TableName("bd_detect_result")
public class DetectResult extends BaseDomain{
    //源文件名
    private String videoName;
    //检测结果描述
    private String detectDescription;
    //预测视频文件名
    private String predictVideoName;

    public void init()
    {
        this.setUpdateTime(new Date());
        this.setCreateTime(new Date());
        this.setDelFlag(0);
    }
}
