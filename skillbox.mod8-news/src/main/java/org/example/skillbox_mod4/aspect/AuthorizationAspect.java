package org.example.skillbox_mod4.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.skillbox_mod4.repository.CommentRepository;
import org.example.skillbox_mod4.repository.NewsRepository;
import org.example.skillbox_mod4.domain.CommentEntity;
import org.example.skillbox_mod4.domain.NewsEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class AuthorizationAspect {

    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;

    public AuthorizationAspect(NewsRepository newsRepository, CommentRepository commentRepository) {
        this.newsRepository = newsRepository;
        this.commentRepository = commentRepository;
    }

    @Before("@annotation(org.example.skillbox_mod4.aspect.NewsRequired)")
    public void checkNewsOwnership(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Long userId = (Long) args[0];
        Long newsId = (Long) args[1];
        Optional<NewsEntity>  news = newsRepository.findById(newsId);
        if (news.isEmpty()){
            throw new RuntimeException("not found news by id");
        }
        if (!news.get().getUserEntity().getId().equals(userId)) {
            throw new RuntimeException("User is not the owner of this news");
        }
    }

    @Before("@annotation(org.example.skillbox_mod4.aspect.CommentRequired)")
    public void checkCommentOwnership(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Long userId = (Long) args[0];
        Long commentId = (Long) args[1];
        Optional<CommentEntity>  comment = commentRepository.findById(commentId);
        if (comment.isEmpty()){
            throw new RuntimeException("not found comment by id");
        }
        if (!comment.get().getUserEntity().getId().equals(userId)) {
            throw new RuntimeException("User is not the owner of this comment");
        }
    }
}
