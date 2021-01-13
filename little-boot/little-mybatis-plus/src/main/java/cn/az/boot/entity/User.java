package cn.az.boot.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * The type User.
 *
 * @author azusachino
 * @version 12 /13/2019
 */
@Data
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends Model<User> implements Serializable {

    private static final long serialVersionUID = 3493072180161663087L;

    @TableId
    private Long id;
    private String name;
    /**
     * AgeEnum
     */
    private Integer age;
    private String email;
    /**
     * metaObject auto fill
     * when insert & update
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String operator;

    /**
     * tableField can use as logic delete
     */
    @TableField(exist = false)
    private Integer count;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
