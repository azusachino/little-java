package cn.az.boot.es.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author az
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 3648988039978763594L;

    private String id;

    private String name;
    private String isbn;
    private LocalDate publishDate;
}
