package pl.bykowski.backendjwt.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookApi {

    @GetMapping
    public List<Book> get(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Spring Boot:LiveBook", "Przymysław Adam Bykowski"));
        bookList.add(new Book("Spring in Action", "Walls"));
        return bookList;
    }

}
