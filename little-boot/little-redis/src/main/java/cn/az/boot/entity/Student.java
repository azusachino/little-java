package cn.az.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * The type Student.
 *
 * @author azusachino
 * @version 2019 /12/09
 */
@Data
@RedisHash("Student")
@AllArgsConstructor
public class Student implements Serializable {

    /**
     * The enum Gender.
     */
    public enum Gender {
        /**
         * Male gender.
         */
        MALE,
        /**
         * Female gender.
         */
        FEMALE
    }

    private String id;
    private String name;
    private Gender gender;
    private int grade;
}
