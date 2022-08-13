package com.example.demo.dto;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommentRequestDto {
  private String name;
  private String content;

  private Long boardId;

  public Comment toComment(Post post){
      return Comment.builder()
              .name(this.name)
              .content(this.content)
              .post(post)
              .build();
  }
}
