package com.example.reply_command.api.model;

import lombok.Builder;
import lombok.Getter;

public class ReplyDto {
    @Getter
    public static class RegReq {
        private String contents;

        public Reply toEntity(Long userIdx, String userName, Long boardIdx) {
            return Reply.builder()
                    .contents(this.contents)
                    .boardIdx(boardIdx)
                    .userIdx(userIdx)
                    .userName(userName)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class RegRes {
        private Long idx;
        private String contents;
        private Long boardIdx;

        public static RegRes from(Reply entity) {
            return RegRes.builder()
                    .idx(entity.getBoardIdx())
                    .contents(entity.getContents())
                    .boardIdx(entity.getBoardIdx())
                    .build();
        }
    }
}
