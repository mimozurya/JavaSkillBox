package org.example.skillbox_mod4.service.mapper;

import org.example.skillbox_mod4.adapter.web.dto.rs.NewsFullResponse;
import org.example.skillbox_mod4.adapter.web.dto.rs.NewsSmallResponse;
import org.example.skillbox_mod4.domain.CommentEntity;
import org.example.skillbox_mod4.domain.NewsEntity;
import org.example.skillbox_mod4.domain.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsMapper {
    public NewsFullResponse newsToFullResponse(NewsEntity newsEntity){
        List<String> comments = new ArrayList<>();
        for (CommentEntity commentEntity :newsEntity.getCommentEntities()){
            comments.add(commentEntity.getContent());
        }
        return new NewsFullResponse(
                newsEntity.getId(),
                newsEntity.getContent(),
                newsEntity.getUserEntity().getName(),
                comments,
                newsEntity.getNewsCategoryEntity().getCategoryName());
    }

    public NewsSmallResponse newsToSmallResponse(NewsEntity newsEntity){
        return new NewsSmallResponse(
                newsEntity.getId(),
                newsEntity.getContent(),
                newsEntity.getUserEntity().getName(),
                newsEntity.getNewsCategoryEntity().getCategoryName());
    }
}
