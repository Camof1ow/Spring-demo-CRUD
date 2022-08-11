package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/post") //Create
    public ResponseDto<Post> post(@RequestBody RequestDto dto) throws Exception {
        Post post = postService.submit(dto.toEntity());

        return new ResponseDto<>(HttpStatus.NOT_FOUND,OK,post);
    }

    @GetMapping("/api/post") //Read
    public ResponseEntity<List> findAll() {
        return new ResponseEntity<>(postService.findAll(), OK);
    }


    @GetMapping("/api/post/{id}")
    public ResponseEntity<Post> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findOne(id), OK);
    }

    @PutMapping("/api/post/{id}")
    public ResponseEntity<Post> update(
            @PathVariable Long id, @RequestBody RequestDto dto) throws Exception {

        postService.update(id, dto);
        Post post = postService.findOne(id);
        return new ResponseEntity<>(post, OK);
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,@RequestBody RequestDto dto) throws Exception {
        postService.delete(id,dto);
        return new ResponseEntity<>("삭제완료", OK);
    }


}
