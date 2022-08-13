package com.example.demo.controller;


import com.example.demo.dto.CommentRequestDto;
import com.example.demo.dto.CommentResponseDto;
import com.example.demo.dto.PostRequestDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;



    public PostController(PostService postService ) {

        this.postService = postService;

    }




    @PostMapping("/api/post") //Create
    public PostResponseDto<String> post(@RequestBody PostRequestDto dto) {return postService.submit(dto);}

    @GetMapping("/api/post") //Read All
    public PostResponseDto<List> findAll() {return postService.findAll();}


    @GetMapping("/api/post/{id}") // Read one by id
    public PostResponseDto<PostResponseDto> findOne(@PathVariable Long id) {
        return postService.findOne(id);
    }

    @PutMapping("/api/post/{id}") // update
    public PostResponseDto<PostResponseDto> update(
            @PathVariable Long id, @RequestBody PostRequestDto dto){

        return postService.update(id, dto);
    }

    @DeleteMapping("/api/post/{id}") //delet
    public PostResponseDto<String> delete(@PathVariable Long id, @RequestBody PostRequestDto dto){
        return postService.delete(id,dto);
    }

    @PostMapping("api/post/{post_id}/comment")
    public CommentResponseDto<String> comment(@PathVariable Long post_id, @RequestBody CommentRequestDto dto){
        dto.setBoardId(post_id);
        return postService.comment(dto);
    }

    @GetMapping("api/post/{post_id}/comment")
    public CommentResponseDto<List> getComment(@PathVariable Long post_id){
        return postService.getComment(post_id);
    }




}
