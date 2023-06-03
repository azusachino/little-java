package cn.az.boot.es;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import cn.az.boot.es.entity.Book;

/**
 * @author ycpang
 * @since 2021-04-14 15:22
 */
@SpringBootTest(classes = LittleEsApplication.class)
public class SimpleEsTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEsTests.class);

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    private final static String ID = UUID.randomUUID().toString();

    @Test
    void contextLoad() {
        // create new index in es
        boolean result = elasticsearchOperations.indexOps(IndexCoordinates.of("index_test")).create();
        LOGGER.info(String.valueOf(result));
    }

    @Test
    public void add() {
        Book book = Book.builder()
                .id(ID)
                .isbn("12345")
                .name("The Stupid Book")
                .publishDate(LocalDate.of(2008, 1, 2))
                .build();
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(ID)
                .withObject(book)
                .build();
        String doc = elasticsearchOperations.index(indexQuery, IndexCoordinates.of("index_test"));
        LOGGER.info("docId: {}", doc);
    }

    @Test
    public void search() {

    }
}
