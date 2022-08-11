package com.example.demo;


import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PostService {


    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post submit(Post post) throws Exception {
        if (post.getTitle().length() < 2){
            throw new Exception("제목은 2자 이상이어야 합니다 ");

        }

        if (post.getAuthor().length() < 2){
            throw new Exception("작성자이름은 2자 이상이어야 합니다 ");

        }

        if (post.getContent().length() < 4){
            throw new Exception("본문은 4자 이상이어야 합니다 ");

        }

        Post response = postRepository.save(post);

        return response;
    }

    public List<Post> findAll() {
        List<Post> post = postRepository.findAll();
        return post;
    }

    public Post findOne(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

    }

    public Post update(Long id, RequestDto dto) throws Exception{
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        if(!Objects.equals(dto.getPassword(), post.getPassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
    }
        post.update(dto.getTitle(), dto.getAuthor(), dto.getContent(), dto.getPassword());
        return post;
    }

    public void delete(Long id,RequestDto dto) throws Exception{
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        if(!Objects.equals(dto.getPassword(), post.getPassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");}
        postRepository.deleteById(id);
    }


}
