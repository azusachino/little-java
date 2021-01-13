package cn.az.boot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author az
 * @since 11/14/20
 */
@Data
@TableName("tb_order_info")
public class OrderInfo {
    private Integer id;
    private Integer shopItemId;
    private String userId;

    private Integer totalPrice;

    @JsonProperty("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonProperty("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Character delFlag;

}
