package org.example.skillbox_mod4.repository;

import org.example.skillbox_mod4.domain.NewsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsCategoryRepository extends JpaRepository<NewsCategoryEntity,Long> {

}
