package cn.az.boot.es.controller;

import cn.az.boot.es.entity.Book;
import cn.az.boot.es.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author az
 */
@RestController
public class BookController {

    @Autowired
    @Qualifier("bookRepository")
    private PagingAndSortingRepository<Book, String> repository;


    @Autowired
    @Qualifier("bookRepository2")
    private BookRepository bookRepository;

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") String id) {
        return repository.findById(id).orElse(new Book());
    }

    @GetMapping("/books")
    public List<Book> books(String name) {
        return bookRepository.findAllByName(name);
    }

}
