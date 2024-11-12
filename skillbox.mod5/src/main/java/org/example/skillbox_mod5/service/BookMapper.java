package org.example.skillbox_mod5.service;

import org.example.skillbox_mod5.adapter.web.dto.BookFResponse;
import org.example.skillbox_mod5.adapter.web.dto.BookResponse;
import org.example.skillbox_mod5.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookFResponse bookToFullRs(Book book){
        return new BookFResponse(book.getName(), book.getCategory().getName(), book.getAuthor());
    }

    public BookResponse bookToRs(Book book){
        return new BookResponse(book.getName(), book.getAuthor());
    }
}
