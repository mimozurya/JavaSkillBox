package org.example.skillbox_mod4.adapter.web;

import lombok.RequiredArgsConstructor;
import org.example.skillbox_mod4.adapter.web.dto.rs.CategoryResponse;
import org.example.skillbox_mod4.service.NewsCategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/")
@RequiredArgsConstructor
public class CategoryController {
    private final NewsCategoryService categoryService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER', 'ROLE_MODERATOR')")
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll(Pageable pageable) {
        List<CategoryResponse> categories = categoryService.getAll(pageable);
        return ResponseEntity.ok(categories);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @PostMapping("/{name}")
    public ResponseEntity<CategoryResponse> add(@PathVariable String name) {
        return ResponseEntity.ok(categoryService.add(name));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
