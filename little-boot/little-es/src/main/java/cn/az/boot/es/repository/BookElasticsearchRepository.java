package cn.az.boot.es.repository;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;
import org.springframework.stereotype.Repository;

import cn.az.boot.es.entity.Book;

/**
 * @author az
 */
@Repository(value = "bookRepository")
public class BookElasticsearchRepository extends SimpleElasticsearchRepository<Book, String> {

    public BookElasticsearchRepository(ElasticsearchEntityInformation<Book, String> metadata,
            ElasticsearchOperations operations) {
        super(metadata, operations);
    }

}
