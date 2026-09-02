package com.cc.behaviordetectionbackend.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BaseDomain
 * @Description 实体基类
 * @Author cc
 * @Date 2025/11/29 10:52
 * @Version 1.0.0
 */
@Data
public class BaseDomain implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 创建人
     */
    @TableField(value = "create_by", jdbcType = JdbcType.VARCHAR, fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", jdbcType = JdbcType.TIMESTAMP, fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改人
     */
    @TableField(value = "update_by", jdbcType = JdbcType.VARCHAR, fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /**
     * 修改时间
     */
    @TableField(value = "update_time", jdbcType = JdbcType.TIMESTAMP, fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 备注
     */
    @TableField(value = "remark", jdbcType = JdbcType.VARCHAR)
    private String remark;
    /**
     * 是否删除（0：未删除；1：删除；）
     */
    @TableField(value = "del_flag", jdbcType = JdbcType.INTEGER, fill = FieldFill.INSERT)
    private Integer delFlag;
}
