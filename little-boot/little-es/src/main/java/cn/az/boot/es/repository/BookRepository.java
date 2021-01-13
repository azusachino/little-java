package cn.az.boot.es.repository;

import cn.az.boot.es.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author az
 */
@Repository("bookRepository2")
public interface BookRepository extends CrudRepository<Book, String> {

    List<Book> findAllByName(String name);
}
