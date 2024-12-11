package org.example.skillbox_mod4.service.mapper;

import org.example.skillbox_mod4.adapter.web.dto.rs.CommentResponse;
import org.example.skillbox_mod4.domain.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentResponse commentToResponse(CommentEntity comment){
        return new CommentResponse(
                comment.getContent(),
                comment.getUserEntity().getName(),
                comment.getNewsEntity().getId()
        );
    }
}
