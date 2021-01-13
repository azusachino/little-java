package cn.az.boot.aop.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Liz
 * @version 2019/11/27
 */
@Data
@TableName("system_log")
public class SysLog implements Serializable {

    private static final long serialVersionUID = -6309732882044872298L;
    @TableId
    private Integer id;
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    @TableField(typeHandler = org.apache.ibatis.type.LocalDateTimeTypeHandler.class)
    private LocalDateTime createTime;
}
