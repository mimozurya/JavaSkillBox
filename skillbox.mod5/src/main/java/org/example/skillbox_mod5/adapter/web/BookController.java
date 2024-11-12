package org.example.skillbox_mod5.adapter.web;

import lombok.RequiredArgsConstructor;
import org.example.skillbox_mod5.adapter.web.dto.BookFResponse;
import org.example.skillbox_mod5.adapter.web.dto.BookRequest;
import org.example.skillbox_mod5.adapter.web.dto.BookResponse;
import org.example.skillbox_mod5.adapter.web.dto.FindRequest;
import org.example.skillbox_mod5.model.Book;
import org.example.skillbox_mod5.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public BookFResponse getByNameAndAuthor(@RequestBody FindRequest searchRequest) {
        return bookService.findByNameAndAuthor(searchRequest);
    }

    @GetMapping("/{category}")
    public List<BookResponse> getAllByCategory(@PathVariable("category") String category) {
        return bookService.findAllByCategory(category);
    }

    @PostMapping
    public BookFResponse create(@RequestBody BookRequest book) {
        return bookService.create(book);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Book book) {
        bookService.updateById(id, book);
    }
}
