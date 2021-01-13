package cn.az.boot.es.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author az
 */
@Data
@Document(indexName = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 3648988039978763594L;

    private String id;

    private String name;
    private String isbn;
    private LocalDate publishDate;
}
