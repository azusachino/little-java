package cn.az.boot.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * The enum Age enum.
 *
 * @author azusachino
 * @version 12 /16/2019
 */
@AllArgsConstructor
public enum  AgeEnum implements IEnum<Integer> {
    /**
     * Twentity age enum.
     */
    TWENTY(20, "二十岁"),
    /**
     * Thirty age enum.
     */
    THIRTY(30,"三十岁"),
    /**
     * Forty age enum.
     */
    FORTY(40, "四十岁")
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
