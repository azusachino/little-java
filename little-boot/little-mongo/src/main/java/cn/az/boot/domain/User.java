package cn.az.boot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author azusachino
 * @version 2019/12/09
 */
@Data
@Document(collection = "user")
@AllArgsConstructor
public class User {

    @Id
    private String id;

    private String name;

    private Integer age;

    private String description;


}
