package cn.az.boot.es.repository;

import cn.az.boot.es.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.repository.support.ElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.MappingElasticsearchEntityInformation;
import org.springframework.data.elasticsearch.repository.support.SimpleElasticsearchRepository;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.TypeInformation;
import org.springframework.stereotype.Repository;

/**
 * @author az
 */
@Repository(value = "bookRepository")
public class BookElasticsearchRepository extends SimpleElasticsearchRepository<Book, String> {

    @Autowired
    public BookElasticsearchRepository(@Qualifier("elasticsearchTemplate") ElasticsearchOperations elasticsearchOperations) {
        super(createElasticsearchEntityInformation(), elasticsearchOperations);
    }

    private static ElasticsearchEntityInformation<Book, String> createElasticsearchEntityInformation() {

        TypeInformation<Book> typeInformation = ClassTypeInformation.from(Book.class);

        ElasticsearchPersistentEntity<Book> entity = new SimpleElasticsearchPersistentEntity<>(typeInformation);

        return (ElasticsearchEntityInformation<Book, String>) new MappingElasticsearchEntityInformation(entity);

    }

    @Override
    protected String stringIdRepresentation(String s) {
        return null;
    }
}
