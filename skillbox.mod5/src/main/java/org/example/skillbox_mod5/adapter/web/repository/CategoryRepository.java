package org.example.skillbox_mod5.adapter.web.repository;

import org.example.skillbox_mod5.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
