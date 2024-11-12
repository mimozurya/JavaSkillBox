package org.example.skillbox_mod5.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.skillbox_mod5.adapter.web.dto.BookFResponse;
import org.example.skillbox_mod5.adapter.web.dto.BookRequest;
import org.example.skillbox_mod5.adapter.web.dto.BookResponse;
import org.example.skillbox_mod5.adapter.web.dto.FindRequest;
import org.example.skillbox_mod5.adapter.web.repository.BookRepository;
import org.example.skillbox_mod5.adapter.web.repository.CategoryRepository;
import org.example.skillbox_mod5.model.Book;
import org.example.skillbox_mod5.model.Category;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper mapper;

    @Cacheable(value = "books", key = "#searchRq.name + #searchRq.author")
    public BookFResponse findByNameAndAuthor(FindRequest findRequest) {
        return mapper.bookToFullRs(
                bookRepository
                        .findByNameAndAuthor(findRequest.bookName(), findRequest.author())
                        .orElseThrow());
    }

    @CacheEvict(value = {"books", "booksByCategory"}, allEntries = true)
    public BookFResponse create(BookRequest bookRequest) {
        Category category = categoryRepository.findById(bookRequest.categoryId()).orElseThrow();
        Book book = new Book(bookRequest.bookName(), bookRequest.author(), category);
        bookRepository.save(book);
        return mapper.bookToFullRs(book);
    }

    @CacheEvict(value = {"books", "booksByCategory"}, key = "#id", beforeInvocation = true)
    public void updateById(Long id, Book book) {
        Book repoBook = bookRepository.findById(id).orElseThrow();
        book.setId(repoBook.getId());
        bookRepository.save(book);
    }

    @CacheEvict(value = {"books", "booksByCategory"}, key = "#id", beforeInvocation = true)
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        bookRepository.deleteById(id);
    }

    @Cacheable(value = "booksByCategory", key = "#category")
    public List<BookResponse> findAllByCategory(String category) {
        return categoryRepository.findAll()
                .stream().filter(it -> it.getName().equals(category))
                .flatMap(e -> e.getBooks().stream()).map(mapper::bookToRs)
                .toList();
    }
}
