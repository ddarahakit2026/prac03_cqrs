package com.example.reply_command.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReplyEvent {
    @ToString
    @Getter
    @Builder
    public static class Create{
        private String uuid;
        private String eventType;
        private LocalDateTime issueAt;
        private Long idx;
        private String contents;
        private Long boardIdx;

        public static Create from(Reply entity) {
            return Create.builder()
                    .uuid(UUID.randomUUID().toString())
                    .eventType("CREATE")
                    .issueAt(LocalDateTime.now())
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .boardIdx(entity.getBoardIdx())
                    .build();
        }
    }
}
