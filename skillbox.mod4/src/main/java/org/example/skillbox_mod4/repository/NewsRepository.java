package org.example.skillbox_mod4.repository;

import org.example.skillbox_mod4.domain.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity,Long>, JpaSpecificationExecutor<NewsEntity> {
}
