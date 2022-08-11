package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.persistence.*;


@Entity
@SuperBuilder
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String title;

    private String author;

    private String content;
    @JsonIgnore
    private String password;



    public Post() { //기본 생성자

    }


    public Post(String title, String author, String content, String password) {
        //변수초기화 생성자
        this.title = title;
        this.author = author;
        this.content = content;
        this.password = password;
    }

    public void update(String title, String author, String content, String password){
        this.title = title;
        this.author = author;
        this.content = content;
        this.password = password;
    }





}
