package cn.az.boot.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

/**
 * The type Age enum.
 *
 * @author azusachino
 * @version 12 /13/2019
 */
@Getter
public enum  AgeEnum implements IEnum<Integer> {

    /**
     * Instantiates a new One.
     */
    ONE(1, "一岁"),

    /**
     * Instantiates a new Two.
     */
    TWO(2, "二岁"),

    /**
     * Instantiates a new Three.
     */
    THREE(3, "三岁");

    private final int value;
    private final String desc;

    /**
     * Instantiates a new Age enum.
     *
     * @param value the value
     * @param desc  the desc
     */
    AgeEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 枚举数据库存储值
     */
    @Override
    public Integer getValue() {
        return value;
    }
}
