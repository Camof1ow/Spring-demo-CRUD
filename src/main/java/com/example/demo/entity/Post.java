package com.example.demo.entity;

import com.example.demo.dto.PostRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@SuperBuilder
@Getter
@Setter
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    private String author;

    private String content;
    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();


    public Post() { //기본 생성자

    }


    public void update(PostRequestDto dto) {
        this.title = dto.getTitle();
        this.author = dto.getAuthor();
        this.content = dto.getContent();
        this.password = dto.getPassword();
    }

}
