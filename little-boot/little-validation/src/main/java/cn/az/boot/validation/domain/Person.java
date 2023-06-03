package cn.az.boot.validation.domain;

import cn.az.boot.validation.bean.constraints.PersonNamePrefix;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotNull;

/**
 * Person
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see cn.az.boot.validation.domain
 * @since 2020-03-12
 */
@Data
public class Person {

    @NotNull(message = "名称不能为空")
    @PersonNamePrefix(value = "az", message = "前缀必须是az")
    private String name;
    @Range(min = 0, max = 200, message = "最小0岁, 最大200岁")
    @NotNull
    private Long age;

    public interface Name {
    }

    public interface Age {
    }

    /**
     * 保证校验的顺序
     */
    @GroupSequence({Name.class, Age.class})
    public interface GroupOrder {
    }

}
