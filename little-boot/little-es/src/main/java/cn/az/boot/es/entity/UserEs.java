package cn.az.boot.es.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * The type User es.
 *
 * @author Liz
 * @version 2019 /11/29
 */
@Data
@Document(indexName = "user", type = "docs", shards = 1, replicas = 0)
public class UserEs {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String username;

    private String phone;

}
