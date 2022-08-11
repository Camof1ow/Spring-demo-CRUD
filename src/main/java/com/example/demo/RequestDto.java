package com.example.demo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;


@Setter
@Getter
public class RequestDto {

    public RequestDto(String title, String author, String content, String password) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.password = password;
    }


    private String title;
    private String author;
    private String content;
    private String password;


    public Post toEntity(){
        return Post.builder()
                .title(title)
                .author(author)
                .content(content)
                .password(password)
                .build();

    }

    public void update(RequestDto requestDto){
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }
}
