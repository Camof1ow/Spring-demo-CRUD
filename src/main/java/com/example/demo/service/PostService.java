package com.example.demo.service;


import com.example.demo.dto.CommentRequestDto;
import com.example.demo.dto.CommentResponseDto;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.dto.PostRequestDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.exception.PostExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class PostService {


    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    @Transactional
    public PostResponseDto<String> submit(PostRequestDto dto)  {
        Post post = Post.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .content(dto.getContent())
                .password(dto.getPassword())
                .build();

        if (post.getTitle().length() < 2){
            throw new IllegalArgumentException(PostExceptionHandler.TTITLE_ERROR);
        }

        if (post.getAuthor().length() < 2){
            throw new IllegalArgumentException(PostExceptionHandler.AUTHOR_ERROR);
        }

        if (post.getContent().length() < 4){
            throw new IllegalArgumentException(PostExceptionHandler.CONTENT_ERROR);
        }
        postRepository.save(post);
        return new PostResponseDto<>(HttpStatus.OK,"포스트 등록 완료");
    }

    public PostResponseDto<List > findAll() {
        if(postRepository.findAll().size() == 0) throw new IllegalArgumentException(PostExceptionHandler.NOT_FOUND);
        return new PostResponseDto<>(HttpStatus.OK,postRepository.findAll());}

    public PostResponseDto<PostResponseDto> findOne(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(PostExceptionHandler.NOT_FOUND));
        PostResponseDto dto = new PostResponseDto<>(HttpStatus.OK,post);
        return new PostResponseDto<>(HttpStatus.OK,dto);
    }

    @Transactional
    public PostResponseDto<PostResponseDto> update(Long id, PostRequestDto dto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(PostExceptionHandler.NOT_FOUND));
        if(!Objects.equals(dto.getPassword(), post.getPassword())){
            throw new IllegalArgumentException(PostExceptionHandler.PASSWORD_ERROR);
    }
        post.update(dto);
        PostResponseDto responseDto = new PostResponseDto(HttpStatus.OK,postRepository.save(post));
         // @Transactional 과 .save  ?
        return new PostResponseDto<>(HttpStatus.OK,responseDto);
    }


    @Transactional
    public PostResponseDto<String> delete(Long id, PostRequestDto dto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(PostExceptionHandler.NOT_FOUND));
        if(!Objects.equals(dto.getPassword(), post.getPassword())){
            throw new IllegalArgumentException(PostExceptionHandler.PASSWORD_ERROR);}
        postRepository.deleteById(id);

        return new PostResponseDto<>(HttpStatus.OK,"포스트 삭제완료");
    }

    @Transactional
    public CommentResponseDto<String> comment(CommentRequestDto dto) {
        Post post = postRepository.findById(dto.getBoardId()).orElseThrow(
                () -> new IllegalArgumentException(PostExceptionHandler.NOT_FOUND));
        commentRepository.save(dto.toComment(post));
        return new CommentResponseDto<>(HttpStatus.OK,"코멘트 등록 완료");

    }

    public CommentResponseDto<List> getComment(Long boardId){
        List<Comment>comment = commentRepository.findAllByPostId(boardId);
        return new CommentResponseDto<> (HttpStatus.OK, comment);

    }

}
