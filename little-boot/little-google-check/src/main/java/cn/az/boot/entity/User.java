package cn.az.boot.entity;

import cn.az.boot.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author azusachino
 * @version 12/16/2019
 */
@Data
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 8791131931015670834L;
    @TableId(type = IdType.UUID)
    private String id;
    private String username;
    private String password;
    private SexEnum sex;
    private String email;
    private String secretKey;
    private Integer validationCode;
    private String scratchCodes;
}
