package cn.az.boot.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum Sex enum.
 *
 * @author azusachino
 * @version 12 /16/2019
 */
@AllArgsConstructor
@Getter
public enum SexEnum implements IEnum<Integer> {

    /**
     * Male sex enum.
     */
    MALE(0, "男"),
    /**
     * Female sex enum.
     */
    FEMALE(1,"女"),
    /**
     * Secret sex enum.
     */
    SECRET(2, "保密")
    ;

    private final Integer value;
    private final String name;

    /**
     * 枚举数据库存储值
     */
    @Override
    public Integer getValue() {
        return this.value;
    }
}
