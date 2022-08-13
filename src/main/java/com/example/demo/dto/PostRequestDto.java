package com.example.demo.dto;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PostRequestDto {

    public PostRequestDto(String title, String author, String content, String password) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.password = password;
    }


    private String title;
    private String author;
    private String content;
    private String password;




}
