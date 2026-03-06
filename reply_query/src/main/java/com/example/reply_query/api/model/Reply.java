package com.example.reply_query.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reply {
    @Id
    private Long idx;
    @Column(nullable=false, length = 100)
    private String contents;
    private Long boardIdx;

    Long userIdx;
    String userName;
}