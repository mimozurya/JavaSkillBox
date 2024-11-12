package org.example.skillbox_mod4.service.mapper;

import org.example.skillbox_mod4.adapter.web.dto.rs.CategoryResponse;
import org.example.skillbox_mod4.domain.NewsCategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse categoryToResponse(NewsCategoryEntity category) {
        return new CategoryResponse(category.getId(), category.getCategoryName());
    }
}
