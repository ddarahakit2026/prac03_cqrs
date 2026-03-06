package com.example.board_query.api.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReplyEvent {
    @ToString
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create{
        private String uuid;
        private String eventType;
        private LocalDateTime issueAt;
        private Long idx;
        private String contents;
        private Long boardIdx;

    }
}
