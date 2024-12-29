package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        /*
        // 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 2. 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i=0;i<comments.size();i++) {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }
        // 3. 결과 반환
        return dtos;
        */
        // for() 문을 스트림 문법으로 개선하기
        // 3. 결과 반환
        return commentRepository.findByArticleId(articleId) // 댓글 엔티티 목록 바로 가져오기
                .stream()   // 스트림: 컬렉션이나 리스트에 저장된 요소들을 하나씩 참조하며 반복 처리할 때 사용
                .map(comment -> CommentDto.createCommentDto(comment))   // 스트림화 한 댓글 엔티티 목록을 DTO로 변환
                .collect(Collectors.toList());  // List<CommentDto> 타입을 반환해야 함, Stream<CommentDto> 타입 -> 리스트 자료형
    }
}
