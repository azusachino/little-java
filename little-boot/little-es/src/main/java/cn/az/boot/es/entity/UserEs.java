package cn.az.boot.es.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

/**
 * The type User es.
 *
 * @author Liz
 * @version 2019 /11/29
 */
@Data
@Document(indexName = "user")
public class UserEs {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String username;

    private String phone;

}
