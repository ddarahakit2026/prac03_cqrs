package com.example.reply_query.api.model;

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

        public Reply toEntity(){
            return Reply.builder()
                    .idx(this.idx)
                    .contents(this.contents)
                    .boardIdx(this.boardIdx)
                    .build();
        }
    }
}
