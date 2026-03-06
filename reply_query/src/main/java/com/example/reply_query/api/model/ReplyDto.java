package com.example.reply_query.api.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReplyDto {
    @Getter
    @Builder
    public static class PageRes {
        private List<ListRes> replyList;
        private int totalPage;
        private long totalCount;
        private int currentPage;
        private int currentSize;

        public static PageRes from(Page<Reply> result) {
            return PageRes.builder()
                    .replyList(result.get().map(ListRes::from).toList())
                    .totalPage(result.getTotalPages())
                    .totalCount(result.getTotalElements())
                    .currentPage(result.getPageable().getPageNumber())
                    .currentSize(result.getPageable().getPageSize())
                    .build();
        }
    }


    @Builder
    @Getter
    public static class ListRes {
        private Long idx;
        private String contents;
        private String writer;
        private int replyCount;
        private int likesCount;

        public static ListRes from(Reply entity) {
            return ListRes.builder()
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .writer(entity.getUserName())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ReadRes {
        private Long idx;
        private String title;
        private String contents;
        private String writer;
        private int likesCount;

        public static ReadRes from(Reply entity) {
            return ReadRes.builder()
                    .idx(entity.getIdx())
                    .contents(entity.getContents())
                    .writer(entity.getUserName())
                    .build();
        }
    }
}
